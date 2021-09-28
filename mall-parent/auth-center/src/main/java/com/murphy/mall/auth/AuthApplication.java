package com.murphy.mall.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 认证启动器
 *
 * @author murphy
 * @since 2021/9/28 4:00 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
// 开启资源服务器 - 保护微服务的访问
@EnableResourceServer
@EnableFeignClients
@EnableCircuitBreaker
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
