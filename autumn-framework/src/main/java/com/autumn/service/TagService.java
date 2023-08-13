package com.autumn.service;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.dto.TagListDto;
import com.autumn.domain.entity.Tag;
import com.autumn.domain.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-08-11 14:38:15
 */
public interface TagService extends IService<Tag> {
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
}


