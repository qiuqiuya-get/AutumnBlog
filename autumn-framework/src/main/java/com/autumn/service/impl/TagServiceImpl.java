package com.autumn.service.impl;

import com.autumn.constants.SystemConstants;
import com.autumn.domain.ResponseResult;
import com.autumn.domain.dto.TagListDto;
import com.autumn.domain.entity.Tag;
import com.autumn.domain.vo.PageVo;
import com.autumn.domain.vo.TagUpdateVo;
import com.autumn.domain.vo.TagVo;
import com.autumn.mapper.TagMapper;
import com.autumn.service.TagService;
import com.autumn.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author qiuqiuya
 * @since 2023-08-11 14:38:17
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Tag::getId,Tag::getName);
        List<Tag> list = list(wrapper);
        return BeanCopyUtils.copyBeanList(list, TagVo.class);
    }

    @Override
    public ResponseResult<Object> addTag(Tag tag) {
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> deleteTag(Long id) {
        LambdaUpdateWrapper<Tag> updateWrapper = new LambdaUpdateWrapper<Tag>()
                .eq(Tag::getId,id)
                .set(Tag::getDelFlag, SystemConstants.DELETE_FLAG);
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> updateTag(Tag tag) {
        LambdaUpdateWrapper<Tag> updateWrapper = new LambdaUpdateWrapper<Tag>()
                .eq(Tag::getId,tag.getId())
                .set(Tag::getName, tag.getName())
                .set(Tag::getRemark,tag.getRemark());
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public TagUpdateVo getOne(Long id) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<Tag>()
                .eq(Tag::getId,id);
        Tag tag = getOne(queryWrapper);
        return BeanCopyUtils.copyBean(tag, TagUpdateVo.class);
    }
}

