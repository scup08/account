package com.lzh.account.persistence;

import com.lzh.account.model.entity.generator.TAccountUser;
import com.lzh.common.annotation.MyBatisRepository;
import com.lzh.common.persistence.CrudMapper;

@SuppressWarnings("InterfaceNeverImplemented")
@MyBatisRepository
public interface TAccountUserMapper extends CrudMapper<TAccountUser> {
    int deleteByPrimaryKey(Long id);

    int insert(TAccountUser record);

    int insertSelective(TAccountUser record);

    TAccountUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAccountUser record);

    int updateByPrimaryKey(TAccountUser record);
}