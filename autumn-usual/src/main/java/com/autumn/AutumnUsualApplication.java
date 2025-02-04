package com.autumn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.autumn.mapper")
@EnableScheduling
public class AutumnUsualApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutumnUsualApplication.class, args);
    }
}