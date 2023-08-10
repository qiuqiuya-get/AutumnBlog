package com.autumn.controller;


import com.autumn.annotation.SystemLog;
import com.autumn.domain.ResponseResult;
import com.autumn.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/5/26 10:17
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章",description = "文章相关接口")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/hotArticleList")
    @ApiOperation(value = "获取热点文章列表",notes = "获取热点文章列表-包含标题和围观数")
    @SystemLog(BusinessName = "获取热点文章列表")
    public ResponseResult hotArticleList(){
        ResponseResult result =  articleService.hotArticleList();
        return result;
    }

    @GetMapping("/articleList")
    @ApiOperation(value = "获取文章列表",notes = "获取一页文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小"),
            @ApiImplicitParam(name = "categoryId",value = "文章分类id")
    })
    @SystemLog(BusinessName = "获取文章列表")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @PutMapping("/updateViewCount/{id}")
    @ApiOperation(value = "更新浏览量",notes = "更新文章浏览量至数据后台")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "文章id")
    })
    @SystemLog(BusinessName = "更新浏览量")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取文章详情",notes = "获取指定文章id的文章详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "文章id")
    })
    @SystemLog(BusinessName = "获取文章详情")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }
}
