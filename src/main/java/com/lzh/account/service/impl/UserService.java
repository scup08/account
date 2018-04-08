package com.lzh.account.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.html.HtmlEscapers;
import com.lzh.account.model.dto.request.LoginRequest;
import com.lzh.account.model.dto.request.RegisterRequest;
import com.lzh.account.model.dto.response.LoginResponse;
import com.lzh.account.model.dto.response.RegisterResponse;
import com.lzh.account.persistence.TAccountUserMapper;
import com.lzh.common.Shift;
import com.lzh.common.StatusCode;
import com.lzh.common.model.entity.account.TAccountUser;
import com.lzh.common.persistence.CrudMapper;
import com.lzh.common.service.impl.CrudServiceImpl;
import com.lzh.common.util.OrikaMapper;

/**
 * @author 
 */
@Service
public class UserService extends CrudServiceImpl<TAccountUser> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private TAccountUserMapper mapper;

    @Autowired
    public UserService(CrudMapper<TAccountUser> mapper) {
        super(mapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public RegisterResponse register(RegisterRequest request) {
        Preconditions.checkNotNull(request);
        final TAccountUser dbUser = find(request.getMobile());
        if (dbUser != null) {
            Shift.fatal(StatusCode.USER_EXISTS);
        }
        // 重新计算密码
        final TAccountUser transientUser = OrikaMapper.map(request, TAccountUser.class);
        final String salt = generateRandomPasswordSalt();
        final String loginPassword = digestWithSalt(transientUser.getLoginPwd(), salt);
        transientUser.setPwdSalt(salt);
        transientUser.setLoginPwd(loginPassword);
        // 混合盐后入库
        persistNonNullProperties(transientUser);
        return OrikaMapper.map(transientUser, RegisterResponse.class);
    }

    @Deprecated
    public LoginResponse login(LoginRequest request) {
        Preconditions.checkNotNull(request);
        final TAccountUser user = find(request.getMobile());
        if (user == null) {
            Shift.fatal(StatusCode.USER_NOT_EXISTS);
        }
        // 登录用户的密码摘要
        final String requestLoginPWd = digestWithSalt(request.getLoginPwd(), user.getPwdSalt());
        if (!Objects.equal(requestLoginPWd, user.getLoginPwd())) {
            Shift.fatal(StatusCode.INVALID_CREDENTIAL);
        }
        final LoginResponse response = new LoginResponse();
        response.setMobile(user.getMobile());
        response.setBalance(user.getBalance());
        return response;
    }

    public TAccountUser find(String mobile) {
        Preconditions.checkNotNull(mobile);
        TAccountUser result = null;
        if (!mobile.isEmpty()) {
            final String escapeMobile = HtmlEscapers.htmlEscaper().escape(mobile);
            result = mapper.selectByPrimaryKey(new Long(2));
        }
        return result;
    }

    public List<TAccountUser> findAll(int offset, int limited) {
        Preconditions.checkArgument(offset > -1);
        Preconditions.checkArgument(limited > -1);
        return mapper.selectAll(offset, limited);
    }

    private String digestWithSalt(String content, String key) {
        String result = content;
        for (int i = 0; i < 5; i++) {
            result = DigestUtils.sha256Hex(result + key);
        }
        return result;
    }

    private String generateRandomPasswordSalt() {
        return DigestUtils.sha256Hex(String.valueOf(System.nanoTime()));
    }

}
