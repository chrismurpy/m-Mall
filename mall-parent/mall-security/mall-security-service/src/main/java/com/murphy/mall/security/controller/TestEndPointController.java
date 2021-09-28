package com.murphy.mall.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 授权测试
 *
 * @author murphy
 * @since 2021/9/28 8:34 下午
 */
@RestController
public class TestEndPointController {

    Logger LOGGER = LoggerFactory.getLogger(TestEndPointController.class);

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        return "project Id：" + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        return "order Id：" + id;
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable String id) {
        return "book Id：" + id;
    }

    @GetMapping("/anno/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAnno(@PathVariable String id) {
        return "Admin Id：" + id;
    }

    @RequestMapping("/hello")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String hello() {
        return "Hello.";
    }

    @GetMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        LOGGER.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        LOGGER.info(oAuth2Authentication.toString());
        LOGGER.info("principal.toString() " + principal.toString());
        LOGGER.info("principal.getName() " + principal.getName());
        LOGGER.info("authentication：" + authentication.getAuthorities().toString());

        return oAuth2Authentication;
    }
}
