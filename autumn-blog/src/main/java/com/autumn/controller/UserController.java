package com.autumn.controller;

import com.autumn.annotation.SystemLog;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.User;
import com.autumn.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/6/2 12:59
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @ApiOperation(value = "获取文用户信息",notes = "获取当前登录的用户的用户信息")
    @SystemLog(BusinessName = "获取用户信息")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @ApiOperation(value = "更新用户信息",notes = "更新用户的个人信息，限制项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User",value = "用户实体")
    })
    @SystemLog(BusinessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册用户",notes = "用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User",value = "用户实体")
    })
    @SystemLog(BusinessName = "注册用户")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}