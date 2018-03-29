package com.lzh.account.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.lzh.account.event.ReservedBalanceCancellationEvent;
import com.lzh.account.model.entity.generator.TAccountUserBalanceTcc;
import com.lzh.account.service.impl.UserBalanceTccService;

/**
 * @author 
 */
@Component
public class ReservedBalanceCancellationEventListener implements ApplicationListener<ReservedBalanceCancellationEvent> {

    private final UserBalanceTccService tccService;

    @Autowired
    public ReservedBalanceCancellationEventListener(UserBalanceTccService tccService) {
        this.tccService = tccService;
    }

    @Async
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onApplicationEvent(ReservedBalanceCancellationEvent event) {
        Preconditions.checkNotNull(event);
        final TAccountUserBalanceTcc res = (TAccountUserBalanceTcc) event.getSource();
        Preconditions.checkNotNull(res);
//        tccService.cancelReservation(res);
    }

}
