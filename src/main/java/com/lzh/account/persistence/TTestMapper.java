package com.lzh.account.persistence;

import com.lzh.account.model.entity.TTest;
import com.lzh.common.annotation.MyBatisRepository;
import com.lzh.common.persistence.CrudMapper;

@MyBatisRepository
public interface TTestMapper extends CrudMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTest record);

    int insertSelective(TTest record);

    TTest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTest record);

    int updateByPrimaryKey(TTest record);
}