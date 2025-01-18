package com.autumn.service;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.dto.AddArticleDto;
import com.autumn.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * @Author: qiuqiuya
 * @Date: 2023/5/25 22:52
 */
@Service
public interface ArticleService extends IService<Article> {
    ResponseResult<Object> hotArticleList();

    ResponseResult<Object> articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult<Object> getArticleDetail(Long id);

    ResponseResult<Object> updateViewCount(Long id);

    ResponseResult<Object> add(AddArticleDto article);

    ResponseResult<Object> articleList(int pageNum, int pageSize, String title, String summary);

    ResponseResult<Object> updateArticle(Article article);

    ResponseResult<Object> delete(Long id);
}
