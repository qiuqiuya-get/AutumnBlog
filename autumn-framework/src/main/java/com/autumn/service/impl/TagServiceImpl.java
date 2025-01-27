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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),"name",tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),"remark",tagListDto.getRemark());

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
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.select("id","name");
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
        UpdateWrapper<Tag> updateWrapper = new UpdateWrapper<Tag>()
                .eq("id",id)
                .set("del_flag", SystemConstants.DELETE_FLAG);
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<Object> updateTag(Tag tag) {
        UpdateWrapper<Tag> updateWrapper = new UpdateWrapper<Tag>()
                .eq("id",tag.getId())
                .set("name", tag.getName())
                .set("remark",tag.getRemark());
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public TagUpdateVo getOne(Long id) {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<Tag>()
                .eq("id",id);
        Tag tag = getOne(queryWrapper);
        return BeanCopyUtils.copyBean(tag, TagUpdateVo.class);
    }
}

