package com.lzh.account.persistence;

import com.lzh.account.model.entity.generator.TAccountUserBalanceTcc;
import com.lzh.common.annotation.MyBatisRepository;
import com.lzh.common.persistence.CrudMapper;

@SuppressWarnings("InterfaceNeverImplemented")
@MyBatisRepository
public interface TAccountUserBalanceTccMapper extends CrudMapper<TAccountUserBalanceTcc> {
    int deleteByPrimaryKey(Long id);

    int insert(TAccountUserBalanceTcc record);

    int insertSelective(TAccountUserBalanceTcc record);

    TAccountUserBalanceTcc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAccountUserBalanceTcc record);

    int updateByPrimaryKey(TAccountUserBalanceTcc record);
}