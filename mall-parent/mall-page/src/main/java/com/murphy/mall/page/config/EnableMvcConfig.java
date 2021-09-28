package com.murphy.mall.page.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态资源过滤
 *
 * @author murphy
 * @since 2021/9/25 12:45 上午
 */
@ControllerAdvice
@Configuration
public class EnableMvcConfig implements WebMvcConfigurer {

    /**
     * 静态资源放行访问
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/items/**").addResourceLocations("classpath:/templates/items/");
    }
}
