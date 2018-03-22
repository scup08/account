package com.lzh.account.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lzh.account.model.entity.User;
import com.lzh.common.annotation.MyBatisRepository;
import com.lzh.common.persistence.CrudMapper;

@SuppressWarnings("InterfaceNeverImplemented")
@MyBatisRepository
public interface UserMapper extends CrudMapper<User> {

    User selectByMobile(@Param("mobile") String mobile);

    List<User> selectAll(@Param("offset") int offset, @Param("limited") int limited);

    int consumeBalance(@Param("userId") Long userId, @Param("amount") Long amount);

    int returnReservedBalance(@Param("userId") Long userId, @Param("amount") Long amount);

}