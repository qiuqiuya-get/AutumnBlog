package com.autmn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Author: qiuqiuya
 * @Description: 项目主启动类
 * @Date: 2023/8/10 17:01
 */
@SpringBootApplication
@MapperScan("com.autumn.mapper")
@EnableScheduling
@EnableSwagger2
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class,args);
    }
}
