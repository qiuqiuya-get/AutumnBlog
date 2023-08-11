package com.autumn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Author: qiuqiuya
 * @Description: 项目后台主启动类
 * @Date: 2023/8/10 17:01
 */
@SpringBootApplication
@MapperScan("com.autumn.mapper")
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class,args);
    }
}
