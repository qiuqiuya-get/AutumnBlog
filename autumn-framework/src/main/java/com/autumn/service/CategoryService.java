package com.autumn.service;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Category;
import com.autumn.domain.vo.CategoryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/5/28 16:42
 */
public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();
}
