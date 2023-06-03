package com.autumn.controller;

import com.autumn.annotation.SystemLog;
import com.autumn.domain.ResponseResult;
import com.autumn.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "分类",description = "文章分类相关")
public class CategoryController {

    final
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("getCategoryList")
    @ApiOperation(value = "获取文章分类",notes = "获取文章分类的种类")
    @SystemLog(BusinessName = "获取文章分类")
    public ResponseResult categoryList(){
        ResponseResult result = categoryService.getCategoryList();
        return result;
    }
}
