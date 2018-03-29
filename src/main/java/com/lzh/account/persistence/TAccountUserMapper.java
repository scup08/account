package com.lzh.account.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lzh.account.model.entity.generator.TAccountUser;

public interface TAccountUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TAccountUser record);

    int insertSelective(TAccountUser record);

    TAccountUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAccountUser record);

    int updateByPrimaryKey(TAccountUser record);
    
//    User selectByMobile(@Param("mobile") String mobile);
//
//    List<User> selectAll(@Param("offset") int offset, @Param("limited") int limited);
//
//    int consumeBalance(@Param("userId") Long userId, @Param("amount") Long amount);
//
//    int returnReservedBalance(@Param("userId") Long userId, @Param("amount") Long amount);
}