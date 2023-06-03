package com.autumn.controller;

import com.autumn.annotation.SystemLog;
import com.autumn.constants.SystemConstants;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.dto.AddCommentDto;
import com.autumn.domain.entity.Comment;
import com.autumn.service.CommentService;
import com.autumn.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/6/1 17:05
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论",description = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    @ApiOperation(value = "获取文章评论列表",notes = "获取一页文章评论评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId",value = "文章id"),
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    @SystemLog(BusinessName = "获取文章评论列表")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    @ApiOperation(value = "发送评论",notes = "添加评论至数据后台")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "AddCommentDto",value = "评论实体")
    })
    @SystemLog(BusinessName = "添加评论")
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto){
        Comment comment = BeanCopyUtils.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    @SystemLog(BusinessName = "获取友链评论列表")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }
}