package com.autumn.controller;

import com.autumn.annotation.SystemLog;
import com.autumn.domain.ResponseResult;
import com.autumn.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/5/28 16:41
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    final
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("getCategoryList")
    @SystemLog(BusinessName = "获取文章分类")
    public ResponseResult categoryList(){
        ResponseResult result = categoryService.getCategoryList();
        return result;
    }
}
