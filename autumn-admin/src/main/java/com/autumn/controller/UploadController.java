package com.autumn.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * 图片上传
 */
@RestController
public class UploadController {

    //service层未实现

//    @Autowired
//    private UploadService uploadService;
//
//    @PostMapping("/upload")
//    public ResponseResult uploadImg(@RequestParam("img") MultipartFile multipartFile) {
//        try {
//            return uploadService.uploadImg(multipartFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("文件上传上传失败");
//        }
//    }
}