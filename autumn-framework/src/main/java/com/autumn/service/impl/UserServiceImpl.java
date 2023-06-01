package com.autumn.service.impl;

import com.autumn.domain.entity.User;
import com.autumn.mapper.UserMapper;
import com.autumn.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author qiuqiuya-auto
 * @since 2023-06-01 17:50:59
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
