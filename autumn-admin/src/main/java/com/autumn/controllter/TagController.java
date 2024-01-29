package com.autumn.controllter;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.dto.TagListDto;
import com.autumn.domain.entity.Tag;
import com.autumn.domain.vo.PageVo;
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
        System.out.println("-------"+tagListDto.toString());
        Tag tag = BeanCopyUtils.copyBean(tagListDto, Tag.class);
        tagService.addTag(tag);
        return ResponseResult.okResult("操作成功");
    }
}