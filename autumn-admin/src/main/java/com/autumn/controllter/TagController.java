package com.autumn.controllter;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.dto.TagListDto;
import com.autumn.domain.entity.Tag;
import com.autumn.domain.vo.PageVo;
import com.autumn.domain.vo.TagUpdateVo;
import com.autumn.domain.vo.TagVo;
import com.autumn.service.TagService;
import com.autumn.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody TagListDto tagListDto){
        Tag tag = BeanCopyUtils.copyBean(tagListDto, Tag.class);
        tagService.addTag(tag);
        return ResponseResult.okResult("操作成功");
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable("id") Long id){
        tagService.deleteTag(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult updateGetOne(@PathVariable("id") Long id){
        TagUpdateVo tagUpdateVo = tagService.getOne(id);
        return new ResponseResult(200,"操作成功",tagUpdateVo);
    }
    @PutMapping
    public ResponseResult updateTag(@RequestBody TagUpdateVo tagUpdateVo){
        Tag tag = BeanCopyUtils.copyBean(tagUpdateVo, Tag.class);
        tagService.updateTag(tag);
        return ResponseResult.okResult();
    }
}