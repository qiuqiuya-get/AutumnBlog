package com.autumn.service;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 友链(Link)表服务接口
 *
 * @author qiuqiuya-auto
 * @since 2023-05-30 21:11:36
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

