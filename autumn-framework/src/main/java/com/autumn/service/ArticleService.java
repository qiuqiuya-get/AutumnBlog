package com.autumn.service;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/5/25 22:52
 */
@Service
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);
}
