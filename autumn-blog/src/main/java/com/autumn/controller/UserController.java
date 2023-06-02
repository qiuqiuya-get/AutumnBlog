package com.autumn.controller;

import com.autumn.annotation.SystemLog;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.User;
import com.autumn.service.UserService;
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
    @SystemLog(BusinessName = "获取用户信息")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(BusinessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @SystemLog(BusinessName = "注册用户")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}