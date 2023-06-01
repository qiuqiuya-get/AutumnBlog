package com.autumn.mapper;

import com.autumn.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


/**
 * 用户表(User)表数据库访问层
 *
 * @author qiuqiuya-auto
 * @since 2023-05-31 19:21:43
 */
@Repository(value="userMapper")
public interface UserMapper extends BaseMapper<User> {

}
