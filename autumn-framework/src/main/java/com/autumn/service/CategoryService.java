package com.autumn.service;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Category;
import com.autumn.domain.vo.CategoryUpdateVo;
import com.autumn.domain.vo.CategoryVo;
import com.autumn.domain.vo.RoleChangeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/5/28 16:42
 */
public interface CategoryService extends IService<Category> {
    ResponseResult<Object> getCategoryList();

    List<CategoryVo> listAllCategory();

    ResponseResult<Object> categoryList(Integer pageNum, Integer pageSize, String name, Integer status);

    ResponseResult<Object> changeStatus(RoleChangeVo roleChangeVo);

    ResponseResult<Object> deleteRole(Long id);

    ResponseResult<Object> addTag(Category category);

    CategoryUpdateVo getOneUpdate(Long id);

    ResponseResult<Object> updateTag(Category category);
}
