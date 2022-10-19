package com.pan.es.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2022-10-08 11:28
 **/
@Configuration
@EnableKnife4j
public class Knife4jConfig {
    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pan.es.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("<<es-study项目>>的后端接口文档")
                .description("此文档说明了es-study项目的后端接口规范")
                .termsOfServiceUrl("http://localhost:9999/")
                .version("1.0.0")
                .contact(new Contact("pan", "localhost:9999", "pan@QQ.com"))
                .build();
    }
}
