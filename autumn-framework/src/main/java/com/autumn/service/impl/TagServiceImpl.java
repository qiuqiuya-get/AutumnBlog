package com.autumn.service.impl;

import com.autumn.domain.entity.Tag;
import com.autumn.mapper.TagMapper;
import com.autumn.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author qiuqiuya
 * @since 2023-08-11 14:38:17
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
}

