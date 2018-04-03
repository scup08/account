package com.lzh.account.service.impl;

import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
//import com.lzh.account.event.ReservedBalanceCancellationEvent;
import com.lzh.account.model.entity.TAccountUser;
import com.lzh.account.model.entity.TAccountUserBalanceTcc;
import com.lzh.account.model.entity.type.TccStatus;
import com.lzh.account.persistence.TAccountUserBalanceTccMapper;
import com.lzh.account.persistence.TAccountUserMapper;
import com.lzh.common.Shift;
import com.lzh.common.StatusCode;
import com.lzh.common.exception.ReservationExpireException;
import com.lzh.common.persistence.CrudMapper;
import com.lzh.common.service.impl.CrudServiceImpl;

/**
 * @author 
 */
@Service
public class UserBalanceTccService extends CrudServiceImpl<TAccountUserBalanceTcc> implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserBalanceTccService.class);

    @Autowired
    private UserService userService;
    @Autowired
    private TAccountUserMapper userMapper;
    @Autowired
    private TAccountUserBalanceTccMapper balanceTccMapper;
    // Autowired
    private ApplicationContext context;

    @Autowired
    public UserBalanceTccService(CrudMapper<TAccountUserBalanceTcc> mapper) {
        super(mapper);
    }

    public TAccountUserBalanceTcc trying(long userId, long amount) {
        return trying(userId, amount, 15);
    }

    public TAccountUserBalanceTcc trying(long userId, long amount, long expireSeconds) {
        Preconditions.checkArgument(userId > 0);
        Preconditions.checkArgument(amount > 0);
        Preconditions.checkArgument(expireSeconds > 0);
        final TAccountUser user = userService.find(userId);
        if (user == null) {
            Shift.fatal(StatusCode.USER_NOT_EXISTS);
        }
        return trying(user, amount, expireSeconds);
    }

    @Transactional(rollbackFor = Exception.class)
    public TAccountUserBalanceTcc trying(TAccountUser user, long amount, long expireSeconds) {
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(user.getId());
        Preconditions.checkArgument(amount > 0);
//        final int isLock = userMapper.consumeBalance(user.getId(), amount);
        final int isLock = 0;
        if (isLock == 0) {
            Shift.fatal(StatusCode.INSUFFICIENT_BALANCE);
        }
        final TAccountUserBalanceTcc tcc = new TAccountUserBalanceTcc();
        tcc.setAmount(amount);
        tcc.setStatus(TccStatus.TRY.getStatus());
        tcc.settAccountUserId(user.getId());
        tcc.setExpireTime(new Date(expireSeconds));
        persistNonNullProperties(tcc);
        return tcc;
    }

    /**
     * 本资源回收策略为定时轮询数据库, 存在资源竞争与重复计算的嫌疑, 待后续版本优化
     */
    @Scheduled(fixedRate = 1000)
    public void autoCancelTrying() {
        // 获取过期的资源
//        final Set<TAccountUserBalanceTcc> reservations = balanceTccMapper.selectExpireReservation(100);
    	final Set<TAccountUserBalanceTcc> reservations = null;
        for (TAccountUserBalanceTcc res : reservations) {
//            context.publishEvent(new ReservedBalanceCancellationEvent(res));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelReservation(Long id) {
        Preconditions.checkNotNull(id);
        final TAccountUserBalanceTcc balanceTcc = find(id);
        // 无法获取说明不存在或者是已经被补偿
        if (balanceTcc == null) {
            throw new ReservationExpireException("resource " + id + " has been cancelled or does not exist at all");
        }
        cancelReservation(balanceTcc);
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelReservation(TAccountUserBalanceTcc res) {
        Preconditions.checkNotNull(res);
        Preconditions.checkNotNull(res.getId());
        Preconditions.checkNotNull(res.getAmount());
        Preconditions.checkNotNull(res.gettAccountUserId());
        Preconditions.checkNotNull(res.getStatus());
        // 只能补偿在TRY阶段
        if (res.getStatus() == TccStatus.TRY.getStatus()) {
            // 依赖行锁, 必须开启事务
//            final int isSucceedInDeleting = balanceTccMapper.deleteTryingById(res.getId());
        	final int isSucceedInDeleting = 1;
            // 删除成功后才能进行补偿
            if (isSucceedInDeleting == 1) {
//                final int isSuccessful = userMapper.returnReservedBalance(res.gettAccountUserId(), res.getAmount());
            	final int isSuccessful = 0 ;
                if (isSuccessful == 0) {
                    throw new IllegalStateException("balance reservation id " + res.getId() + " was succeeded in deleting, but failed to make compensation for user id " + res.gettAccountUserId());
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void confirmReservation(Long id) {
        Preconditions.checkNotNull(id);
        final TAccountUserBalanceTcc balanceTcc = find(id);
        // 无法获取说明不存在或者是已经被补偿
        if (balanceTcc == null) {
            throw new ReservationExpireException("resource " + id + " has been cancelled or does not exist at all");
        }
        // 如果为Try阶段则进行确认
        if (balanceTcc.getStatus() == TccStatus.TRY.getStatus()) {
//            final int isSuccessful = balanceTccMapper.updateToConfirmationById(id);
        	final int isSuccessful = 0 ;
            // 如果返回0则说明已经被撤销
            if (isSuccessful == 0) {
                throw new ReservationExpireException("resource " + id + " has been cancelled");
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

}
