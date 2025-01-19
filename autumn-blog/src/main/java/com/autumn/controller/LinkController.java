package com.autumn.controller;

import com.autumn.annotation.SystemLog;
import com.autumn.domain.ResponseResult;
import com.autumn.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: qiuqiuya
 * @Description: 友链控制层
 * @Date: 2023/5/30 21:08
 */
@RestController
@RequestMapping("/link")
@Api(tags = "友链",description = "友链相关接口")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/getAllLink")
    @ApiOperation(value = "获取全部友链",notes = "获取全部友链的详细信息")
    @SystemLog(BusinessName = "获取全部友链")
    public ResponseResult<Object> getAllLink(){
        return linkService.getAllLink();
    }
}