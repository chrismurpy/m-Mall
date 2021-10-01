package com.murphy.mall.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 用户拦截器
 *
 * @author murphy
 * @since 2021/10/1 4:48 下午
 */
@Component
public class MyFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 获得当前请求头，传递给商品微服务 - 获取的是当前线程的request信息，如果使用线程隔离，需要采用信号量隔离方案，否则出错
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            // 获得请求头，向下传
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                // 请求头名
                String name = headerNames.nextElement();
                // 请求头值
                String value = request.getHeader(name);
                System.out.println(name + " ::: " + value);
                // 把请求头传递给Feign
                requestTemplate.header(name, value);
            }
        }
    }
}
