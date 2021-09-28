package com.murphy.mall.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    public static final String SWAGGER_SCAN_ADMIN_PACKAGE = "com.murphy.mall.admin.controller";
    public static final String ADMIN_VERSION = "1.0.0";

    @Bean
    public Docket createAdminRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("系统设置接口")
                .apiInfo(apiAdminInfo())
                .select()
                // api接口包扫描路径
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_ADMIN_PACKAGE))
                // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiAdminInfo() {
        return new ApiInfoBuilder()
                // 设置文档的标题
                .title("系统设置接口")
                // 设置文档的描述->1.Overview
                .description("系统设置接口实现的文档")
                // 设置文档的版本信息-> 1.1 Version information
                .version(ADMIN_VERSION)
                .build();
    }

}
