package com.autumn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Author: qiuqiuya
 * @Description: 项目主启动类
 * @Date: 2023/5/24 20:10
 */
@SpringBootApplication
@MapperScan("com.autumn.mapper")
public class AutumnBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutumnBlogApplication.class,args);
    }
}
