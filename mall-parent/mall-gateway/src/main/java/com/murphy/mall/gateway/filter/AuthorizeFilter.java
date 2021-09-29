package com.murphy.mall.gateway.filter;

import com.murphy.mall.gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 鉴权过滤器
 *
 * @author murphy
 * @since 2021/9/27 11:19 上午
 */
//@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    private static final String AUTHORIZE_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 如果访问登录等 - 放行
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        if (request.getURI().getPath().startsWith("/user/login")) {
            return chain.filter(exchange);
        }

        // 2. 获得令牌
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        if (StringUtils.isEmpty(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        // 3. 校验令牌
        try {
            Claims claims = JwtUtil.parseJwt(token);
            // 对解析的用户的角色判断，不同角色访问相应角色的微服务
        } catch (Exception e) {
            e.printStackTrace();
            // 解析失败
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.setComplete();
        }
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        // 返回值越小，在过滤器链中越优先执行
        return 0;
    }
}
