package com.autumn.controllter;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.dto.AddArticleDto;
import com.autumn.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult articleList(int pageNum, int pageSize, String title, String summary){
        return articleService.articleList(pageNum,pageSize,title,summary);
    }

}