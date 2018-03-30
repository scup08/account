package com.lzh.account.generator;

import com.lzh.account.generator.TAccountUser;
import com.lzh.common.persistence.CrudMapper;

public interface TAccountUserMapper extends CrudMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TAccountUser record);

    int insertSelective(TAccountUser record);

    TAccountUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAccountUser record);

    int updateByPrimaryKey(TAccountUser record);
}