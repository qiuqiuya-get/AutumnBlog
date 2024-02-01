package com.autumn.controllter;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Menu;
import com.autumn.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult getMenuList(Integer status, String menuName){
        return menuService.menuList(status,menuName);
    }

    @PostMapping
    public ResponseResult addMenu(@RequestBody Menu menu){
        return menuService.addMenu(menu);
    }

    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable("id")Long id){
        return menuService.menuById(id);
    }
    @PutMapping
    public ResponseResult updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{menuid}")
    public ResponseResult deleteMenu(@PathVariable("menuid")Long id){
        return menuService.deleteMenu(id);
    }
}
