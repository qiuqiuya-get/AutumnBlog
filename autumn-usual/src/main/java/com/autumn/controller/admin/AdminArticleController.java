package com.autumn.controller.admin;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.dto.AddArticleDto;
import com.autumn.domain.entity.Article;
import com.autumn.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章
 */
@RestController
@RequestMapping("/content/article")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult<Object> add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult<Object> articleList(int pageNum, int pageSize, String title, String summary){
        return articleService.articleList(pageNum,pageSize,title,summary);
    }

    @GetMapping("/{id}")
    public ResponseResult<Object> articleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

    @PutMapping
    public ResponseResult<Object> articleUpdate(@RequestBody Article article){
        return articleService.updateArticle(article);
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Object> deleteArticle(@PathVariable("id") Long id){
        return articleService.delete(id);
    }

}