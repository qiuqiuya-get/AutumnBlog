package com.autumn.service;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.User;
import com.autumn.domain.vo.RoleChangeVo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户表(User)表服务接口
 *
 * @author qiuqiuya-auto
 * @since 2023-06-01 17:50:58
 */
public interface UserService extends IService<User> {

    ResponseResult<Object> userInfo();

    ResponseResult<Object> updateUserInfo(User user);

    ResponseResult<Object> register(User user);

    ResponseResult<Object> getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, Integer status);

    ResponseResult<Object> deleteUser(Long id);

    ResponseResult<Object> changeStatus(RoleChangeVo roleChangeVo);
}

