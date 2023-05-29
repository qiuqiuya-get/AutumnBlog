package com.autumn.service.impl;

import com.autumn.constants.SystemConstants;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Article;
import com.autumn.domain.entity.Category;
import com.autumn.domain.entity.vo.CategoryVo;
import com.autumn.mapper.CategoryMapper;
import com.autumn.service.ArticleService;
import com.autumn.service.CategoryService;
import com.autumn.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/5/28 16:48
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    private final ArticleService articleService;

    public CategoryServiceImpl(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ResponseResult getCategoryList() {
        //查询文章表  状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
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
}
