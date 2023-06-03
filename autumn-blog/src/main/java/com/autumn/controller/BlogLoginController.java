package com.autumn.controller;

import com.autumn.annotation.SystemLog;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.User;
import com.autumn.enums.AppHttpCodeEnum;
import com.autumn.exception.SystemException;
import com.autumn.service.BlogLoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "用户登录",notes = "用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User",value = "用户实体")
    })
    @SystemLog(BusinessName = "用户登录")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText((user.getUserName()))){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户退出",notes = "用户退出登录状态")
    @SystemLog(BusinessName = "用户退出")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
