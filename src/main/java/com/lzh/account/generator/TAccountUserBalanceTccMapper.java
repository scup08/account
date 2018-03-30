package com.lzh.account.generator;

import com.lzh.account.generator.TAccountUserBalanceTcc;
import com.lzh.common.persistence.CrudMapper;

public interface TAccountUserBalanceTccMapper extends CrudMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TAccountUserBalanceTcc record);

    int insertSelective(TAccountUserBalanceTcc record);

    TAccountUserBalanceTcc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAccountUserBalanceTcc record);

    int updateByPrimaryKey(TAccountUserBalanceTcc record);
}