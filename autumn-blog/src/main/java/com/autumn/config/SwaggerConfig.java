package com.autumn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: qiuqiuya
 * @Description: swagger2配置文件
 * @Date: 2023/6/3 19:47
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.autumn.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Autumn文档")
                .description("个人博客项目接口。\r文档作者：秋秋鸭 ")
                .contact(new Contact("秋叶个人", "http://www.autumnleaf.site/", "1764608405@qq.com"))   // 联系方式
                .version("1.1.0")  // 版本
                .build();
    }
}