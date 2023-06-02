package com.autumn.controller;


import com.autumn.annotation.SystemLog;
import com.autumn.domain.ResponseResult;
import com.autumn.service.ArticleService;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/hotArticleList")
    @SystemLog(BusinessName = "获取热点文章列表")
    public ResponseResult hotArticleList(){

        ResponseResult result =  articleService.hotArticleList();
        return result;
    }

    @GetMapping("/articleList")
    @SystemLog(BusinessName = "获取文章列表")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @PutMapping("/updateViewCount/{id}")
    @SystemLog(BusinessName = "更新浏览量")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }

    @GetMapping("/{id}")
    @SystemLog(BusinessName = "获取文章详情")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }
}
