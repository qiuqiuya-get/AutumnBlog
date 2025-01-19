package com.autumn.service;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Role;
import com.autumn.domain.vo.RoleChangeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-08-12 16:28:21
 */
public interface RoleService extends IService<Role> {
    List<String> selectRoleKeyByUserId(Long id);

    ResponseResult<Object> getRoleList(Integer pageNum, Integer pageSize, String roleName, Integer status);

    ResponseResult<Object> changeStatus(RoleChangeVo roleChangeVo);

    ResponseResult<Object> deleteRole(Long id);

    ResponseResult<Object> getAllRole();
}

