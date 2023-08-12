package com.autumn.service.impl;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.LoginUser;
import com.autumn.domain.entity.User;
import com.autumn.domain.vo.BlogUserLoginVo;
import com.autumn.domain.vo.UserInfoVo;
import com.autumn.service.BlogLoginService;
import com.autumn.service.LoginService;
import com.autumn.utils.BeanCopyUtils;
import com.autumn.utils.JwtUtil;
import com.autumn.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/6/1 10:24
 */
@Service
public class SystemLoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token封装 返回
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);
    }


}
