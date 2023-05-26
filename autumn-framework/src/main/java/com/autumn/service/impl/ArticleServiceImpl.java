package com.autumn.service.impl;

import com.autumn.domain.entity.Article;
import com.autumn.mapper.ArticleMapper;
import com.autumn.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/5/25 23:00
 */
@Component
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
