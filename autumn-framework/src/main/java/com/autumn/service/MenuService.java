package com.autumn.service;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-08-12 16:10:57
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    ResponseResult menuList(Integer status, String menuName);

    ResponseResult addMenu(Menu menu);

    ResponseResult menuById(Long id);

    ResponseResult updateMenu(Menu menu);

    ResponseResult deleteMenu(Long id);
}

