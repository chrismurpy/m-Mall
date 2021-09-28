package com.murphy.mall.admin.config;

import com.murphy.mall.core.json.spring.JsonReturnHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Title: 用于@JSON(type = Post.class ,filter = "desc")忽略desc属性json序列化
 * @Description:
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Bean
    public JsonReturnHandler JsonReturnHandler(){
        return new JsonReturnHandler();
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(JsonReturnHandler());
    }

}