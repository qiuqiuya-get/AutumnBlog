package com.autumn.mapper;

import com.autumn.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-12 16:10:54
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);
}

