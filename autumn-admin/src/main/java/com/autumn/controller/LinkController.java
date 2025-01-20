package com.autumn.controller;

import com.autumn.domain.ResponseResult;
import com.autumn.domain.dto.AddLinkDto;
import com.autumn.domain.entity.Link;
import com.autumn.domain.vo.LinkUpdateDto;
import com.autumn.service.LinkService;
import com.autumn.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult<Object> linkList(Integer pageNum, Integer pageSize, String name, Integer status){
        return linkService.linkList(pageNum,pageSize,name,status);
    }

    @PostMapping
    public ResponseResult<Object> addLink(@RequestBody AddLinkDto addLinkDto){
        Link link = BeanCopyUtils.copyBean(addLinkDto, Link.class);
        return linkService.addTag(link);
    }

    @GetMapping("/{id}")
    public ResponseResult<Object> updateGetOne(@PathVariable("id") Long id){
        LinkUpdateDto linkUpdateDto = linkService.getOneUpdate(id);
        return new ResponseResult<>(200,"操作成功",linkUpdateDto);
    }
    @PutMapping
    public ResponseResult<Object> updateLink(@RequestBody LinkUpdateDto linkUpdateDto){
        Link link = BeanCopyUtils.copyBean(linkUpdateDto, Link.class);
        return linkService.updateTag(link);
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Object> deleteLink(@PathVariable("id")Long id){
        return linkService.deleteLink(id);
    }
}
