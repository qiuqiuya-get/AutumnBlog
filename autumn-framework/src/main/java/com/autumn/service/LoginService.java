package com.autumn.service;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.User;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/6/1 10:23
 */
public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
