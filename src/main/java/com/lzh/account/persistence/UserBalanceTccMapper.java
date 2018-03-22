package com.lzh.account.persistence;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.lzh.account.model.entity.UserBalanceTcc;
import com.lzh.common.annotation.MyBatisRepository;
import com.lzh.common.persistence.CrudMapper;

@SuppressWarnings("InterfaceNeverImplemented")
@MyBatisRepository
public interface UserBalanceTccMapper extends CrudMapper<UserBalanceTcc> {
    Set<UserBalanceTcc> selectExpireReservation(@Param("limitation") int limitation);

    int deleteTryingById(@Param("id") Long id);

    int updateToConfirmationById(@Param("id") Long id);
}