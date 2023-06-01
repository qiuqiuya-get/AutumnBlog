package com.autumn.service;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 评论表(Comment)表服务接口
 *
 * @author qiuqiuya-auto
 * @since 2023-06-01 16:57:48
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);
}

