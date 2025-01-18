package com.autumn.service.impl;

import com.autumn.constants.SystemConstants;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Article;
import com.autumn.domain.entity.Category;
import com.autumn.domain.vo.CategoryUpdateVo;
import com.autumn.domain.vo.CategoryVo;
import com.autumn.domain.vo.PageVo;
import com.autumn.domain.vo.RoleChangeVo;
import com.autumn.mapper.CategoryMapper;
import com.autumn.service.ArticleService;
import com.autumn.service.CategoryService;
import com.autumn.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @Author: qiuqiuya
 * @Date: 2023/5/28 16:48
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    private final ArticleService articleService;

    public CategoryServiceImpl(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ResponseResult<Object> getCategoryList() {
        //查询文章表  状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());

        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream().
                filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        List<Category> list = list(wrapper);
        return BeanCopyUtils.copyBeanList(list, CategoryVo.class);
    }

    @Override
    public ResponseResult<Object> categoryList(Integer pageNum, Integer pageSize, String name, Integer status) {
        //分页查询
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!Objects.isNull(name)) {
            lambdaQueryWrapper.like(Category::getName, name);
        }
        if (!Objects.isNull(status)) {
            lambdaQueryWrapper.eq(Category::getStatus, status);
        }

        Page<Category> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, lambdaQueryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<Object> changeStatus(RoleChangeVo roleChangeVo) {
        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<Category>()
                .eq(Category::getId, roleChangeVo.getRoleId())
                .set(Category::getStatus, roleChangeVo.getStatus());
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> deleteRole(Long id) {
        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<Category>()
                .eq(Category::getId, id)
                .set(Category::getDelFlag, SystemConstants.DELETE_FLAG);
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> addTag(Category category) {
        save(category);
        return ResponseResult.okResult();
    }

    @Override
    public CategoryUpdateVo getOneUpdate(Long id) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<Category>()
                .eq(Category::getId, id);
        Category category = getOne(queryWrapper);
        return BeanCopyUtils.copyBean(category, CategoryUpdateVo.class);
    }

    @Override
    public ResponseResult<Object> updateTag(Category category) {
        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<Category>()
                .eq(Category::getId, category.getId())
                .set(Category::getStatus, category.getStatus())
                .set(Category::getName, category.getName())
                .set(Category::getDescription, category.getDescription());
        update(updateWrapper);
        return ResponseResult.okResult();
    }
}
