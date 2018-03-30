package com.lzh.account.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lzh.account.model.entity.TAccountUser;
import com.lzh.common.annotation.MyBatisRepository;
import com.lzh.common.persistence.CrudMapper;


@MyBatisRepository
public interface TAccountUserMapper extends CrudMapper<TAccountUser> {
    int deleteByPrimaryKey(Long id);

    int insert(TAccountUser record);

    int insertSelective(TAccountUser record);

    TAccountUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAccountUser record);

    int updateByPrimaryKey(TAccountUser record);
    
    List<TAccountUser> selectAll(@Param("offset") int offset, @Param("limited") int limited);
}