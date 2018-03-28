package com.lzh.account.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lzh.account.model.dto.request.RechargeRequest;
import com.lzh.account.model.dto.request.RegisterRequest;
import com.lzh.account.model.dto.response.ObjectCollectionResponse;
import com.lzh.account.model.dto.response.ObjectDataResponse;
import com.lzh.account.model.dto.response.RegisterResponse;
import com.lzh.account.model.entity.User;
import com.lzh.account.service.impl.UserService;
import com.lzh.common.Shift;
import com.lzh.common.StatusCode;

/**
 * @author 
 */
@RestController
@RequestMapping(value = "/api/v1", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class UserController {
    @Autowired
    private UserService userService;

//    @Delay
//    @RandomlyThrowsException
//    @ApiOperation(value = "根据ID获取用户", notes = "")
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ObjectDataResponse<User> findUser(@PathVariable Long userId) {
        final User user = userService.find(userId);
        if (user == null) {
            Shift.fatal(StatusCode.USER_NOT_EXISTS);
        }
        return new ObjectDataResponse<>(user);
    }

//    @Delay
//    @RandomlyThrowsException
//    @ApiOperation(value = "获取全部用户", notes = "")
    @RequestMapping(value = "/users", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ObjectCollectionResponse<User> findAll() {
        final List<User> userList = userService.findAll(0, 10000);
        return new ObjectCollectionResponse<>(userList);
    }

//    @Delay
//    @RandomlyThrowsException
//    @ApiOperation(value = "用户注册", notes = "注册新用户, 余额自定义, 用于下单等一系列操作, 并可获取JWT")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request, BindingResult error) {
        return userService.register(request);
    }

//    @Delay
//    @RandomlyThrowsException
//    @ApiOperation(value = "用户余额变更", notes = "直接变更指定用户的余额")
    @RequestMapping(value = "/users/{userId}/balance", method = RequestMethod.PATCH)
    public ObjectDataResponse<User> recharge(@PathVariable Long userId, @Valid @RequestBody RechargeRequest request, BindingResult error) {
        final User user = userService.find(userId);
        if (user == null) {
            Shift.fatal(StatusCode.USER_NOT_EXISTS);
        }
        user.setBalance(request.getAmount());
        userService.updateNonNullProperties(user);
        return new ObjectDataResponse<>(user);
    }

}
