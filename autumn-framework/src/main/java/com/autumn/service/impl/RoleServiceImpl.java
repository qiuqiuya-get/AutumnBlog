package com.autumn.service.impl;

import com.autumn.constants.SystemConstants;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Menu;
import com.autumn.domain.entity.Role;
import com.autumn.domain.entity.Tag;
import com.autumn.domain.vo.PageVo;
import com.autumn.domain.vo.RoleChangeVo;
import com.autumn.mapper.RoleMapper;
import com.autumn.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-08-12 16:30:20
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult getRoleList(Integer pageNum, Integer pageSize, String roleName, Integer status) {
        //分页查询
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (!Objects.isNull(roleName)){
            queryWrapper.like(Role::getRoleName,roleName);
        }
        if (!Objects.isNull(status)) {
            queryWrapper.eq(Role::getStatus, status);
        }

        Page<Role> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult changeStatus(RoleChangeVo roleChangeVo) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",roleChangeVo.getRoleId());
        updateWrapper.set("status", roleChangeVo.getStatus());
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteRole(Long id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",id);
        updateWrapper.set("del_flag", SystemConstants.DELETE_FLAG);
        update(updateWrapper);
        return ResponseResult.okResult();
    }
}
