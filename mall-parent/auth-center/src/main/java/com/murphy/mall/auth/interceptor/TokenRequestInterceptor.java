package com.murphy.mall.auth.interceptor;

import com.murphy.mall.auth.utils.AdminToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 拦截器 - 在Feign调用之前执行
 *
 * @author murphy
 * @since 2021/10/1 4:28 下午
 */
@Component
public class TokenRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = null;
        try {
            token = AdminToken.adminToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestTemplate.header("Authorization","Bearer " + token);
    }
}
