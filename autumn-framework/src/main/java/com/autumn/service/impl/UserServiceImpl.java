package com.autumn.service.impl;

import com.autumn.constants.SystemConstants;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.User;
import com.autumn.domain.vo.PageVo;
import com.autumn.domain.vo.RoleChangeVo;
import com.autumn.domain.vo.UserInfoVo;
import com.autumn.enums.AppHttpCodeEnum;
import com.autumn.exception.SystemException;
import com.autumn.mapper.UserMapper;
import com.autumn.service.UserService;
import com.autumn.utils.BeanCopyUtils;
import com.autumn.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 用户表(User)表服务实现类
 *
 * @author qiuqiuya-auto
 * @since 2023-06-01 17:50:59
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult<Object> userInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user,UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult<Object> updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseResult<Object> register(User user) {
        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //对数据进行是否存在的判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        //...
        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //存入数据库
        save(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, Integer status) {
        //分页查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!Objects.isNull(userName)){
            queryWrapper.like("userName",userName);
        }
        if (!Objects.isNull(phonenumber)) {
            queryWrapper.eq("phonenumber", phonenumber);
        }
        if (!Objects.isNull(status)) {
            queryWrapper.eq("status", status);
        }

        Page<User> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<Object> deleteUser(Long id) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>()
                .eq("id",id)
                .set("del_flag", SystemConstants.DELETE_FLAG);
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> changeStatus(RoleChangeVo roleChangeVo) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>()
                .eq("id",roleChangeVo.getRoleId())
                .set("status", roleChangeVo.getStatus());
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    private boolean nickNameExist(String nickName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nick_name",nickName);
        return count(queryWrapper)>0;
    }

    private boolean userNameExist(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        return count(queryWrapper)>0;
    }
}
