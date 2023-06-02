package com.autumn.service;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户表(User)表服务接口
 *
 * @author qiuqiuya-auto
 * @since 2023-06-01 17:50:58
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}

