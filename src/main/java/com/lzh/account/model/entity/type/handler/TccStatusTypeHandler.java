package com.lzh.account.model.entity.type.handler;

import com.lzh.account.model.entity.type.TccStatus;
import com.lzh.common.model.entity.type.handler.GenericTypeHandler;

/**
 * @author Zhao Junjian
 */
public class TccStatusTypeHandler extends GenericTypeHandler<TccStatus> {
    @Override
    public int getEnumIntegerValue(TccStatus parameter) {
        return parameter.getStatus();
    }
}
