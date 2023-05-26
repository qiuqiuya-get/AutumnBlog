package com.autumn.controller;


import com.autumn.domain.entity.Article;
import com.autumn.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/5/26 10:17
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/test")
    public List<Article> test(){
        return articleService.list();
    }
}
