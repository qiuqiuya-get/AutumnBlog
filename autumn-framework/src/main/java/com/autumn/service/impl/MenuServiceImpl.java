package com.autumn.service.impl;


import com.autumn.constants.SystemConstants;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Article;
import com.autumn.domain.entity.Menu;
import com.autumn.domain.entity.Tag;
import com.autumn.domain.vo.ArticleListVo;
import com.autumn.domain.vo.PageVo;
import com.autumn.domain.vo.TagVo;
import com.autumn.mapper.MenuMapper;
import com.autumn.service.MenuService;
import com.autumn.utils.BeanCopyUtils;
import com.autumn.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-08-12 16:10:58
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员，返回所有的权限
        if(id == 1L){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType,SystemConstants.MENU,SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(wrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        //否则返回所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断是否是管理员
        if(SecurityUtils.isAdmin()){
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        }else{
            //否则  获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = builderMenuTree(menus,0L);
        return menuTree;
    }

    @Override
    public ResponseResult menuList(Integer status, String menuName) {
        LambdaQueryWrapper<Menu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!Objects.isNull(menuName)){
            lambdaQueryWrapper.like(Menu::getMenuName,menuName);
        }
        if (!Objects.isNull(status)) {
            lambdaQueryWrapper.eq(Menu::getStatus, status);
        }
        // 对isTop进行降序
        lambdaQueryWrapper.orderByAsc(Menu::getOrderNum);
        List<Menu> list = list(lambdaQueryWrapper);
        return ResponseResult.okResult(list);
    }

    @Override
    public ResponseResult addMenu(Menu menu) {
        save(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult menuById(Long id) {
        return new ResponseResult(200,"操作成功",getById(id));
    }

    @Override
    public ResponseResult updateMenu(Menu menu) {
        if (Objects.equals(menu.getId(), menu.getParentId())){
            return ResponseResult.errorResult(500,"修改失败，上级菜单不能是自己");
        }else {
            updateById(menu);
            return ResponseResult.okResult();
        }
    }

    @Override
    public ResponseResult deleteMenu(Long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",id);
        System.out.println("---------"+getMap(queryWrapper));
        if (!Objects.isNull(getMap(queryWrapper))){
            return ResponseResult.errorResult(500,"存在子菜单不允许删除");
        }else {
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("id",id);
            updateWrapper.set("del_flag", SystemConstants.DELETE_FLAG);
            update(updateWrapper);
            return ResponseResult.okResult();
        }
    }


    private List<Menu> builderMenuTree(List<Menu> menus, Long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    /**
     * 获取存入参数的 子Menu集合
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildren(m,menus)))
                .collect(Collectors.toList());
        return childrenList;
    }
}

