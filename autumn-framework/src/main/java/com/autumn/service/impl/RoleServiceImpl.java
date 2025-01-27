package com.autumn.service.impl;

import com.autumn.constants.SystemConstants;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Role;
import com.autumn.domain.vo.PageVo;
import com.autumn.domain.vo.RoleChangeVo;
import com.autumn.mapper.RoleMapper;
import com.autumn.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public ResponseResult<Object> getRoleList(Integer pageNum, Integer pageSize, String roleName, Integer status) {
        //分页查询
        QueryWrapper<Role> QueryWrapper = new QueryWrapper<>();
        if (!Objects.isNull(roleName)){
            QueryWrapper.like("role_name",roleName);
        }
        if (!Objects.isNull(status)) {
            QueryWrapper.eq("status", status);
        }

        Page<Role> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, QueryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<Object> changeStatus(RoleChangeVo roleChangeVo) {
        UpdateWrapper<Role> updateWrapper = new UpdateWrapper<Role>()
                .eq("id",roleChangeVo.getRoleId())
                .set("status", roleChangeVo.getStatus());
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> deleteRole(Long id) {
        UpdateWrapper<Role> updateWrapper = new UpdateWrapper<Role>()
                .eq("id",id)
                .set("del_flag", SystemConstants.DELETE_FLAG);
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> getAllRole() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("status",0);
        List<Role> list = list(wrapper);
        return new ResponseResult<>(200,"操作成功",list);
    }
}
