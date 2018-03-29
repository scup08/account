package com.lzh.account.persistence;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.lzh.account.model.entity.generator.TAccountUserBalanceTcc;

public interface TAccountUserBalanceTccMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TAccountUserBalanceTcc record);

    int insertSelective(TAccountUserBalanceTcc record);

    TAccountUserBalanceTcc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAccountUserBalanceTcc record);

    int updateByPrimaryKey(TAccountUserBalanceTcc record);
    
//    Set<UserBalanceTcc> selectExpireReservation(@Param("limitation") int limitation);
//
//    int deleteTryingById(@Param("id") Long id);
//
//    int updateToConfirmationById(@Param("id") Long id);
}