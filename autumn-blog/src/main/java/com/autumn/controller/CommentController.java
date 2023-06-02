package com.autumn.controller;

import com.autumn.annotation.SystemLog;
import com.autumn.constants.SystemConstants;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Comment;
import com.autumn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: qiuqiuya
 * @Description:
 * @Date: 2023/6/1 17:05
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    @SystemLog(BusinessName = "获取文章评论列表")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    @SystemLog(BusinessName = "添加评论")
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @SystemLog(BusinessName = "获取友链评论列表")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }
}