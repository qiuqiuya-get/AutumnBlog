package com.autumn.controller;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.User;
import com.autumn.enums.AppHttpCodeEnum;
import com.autumn.exception.SystemException;
import com.autumn.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/6/1 10:19
 */
@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText((user.getUserName()))){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
