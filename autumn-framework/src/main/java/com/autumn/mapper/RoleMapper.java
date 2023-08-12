package com.autumn.mapper;
import com.autumn.domain.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-12 16:28:19
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<String> selectRoleKeyByUserId(Long userId) ;
}
