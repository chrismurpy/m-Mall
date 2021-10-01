package com.murphy.mall.order.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.murphy.mall.core.json.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 解析用户数据
 *
 * @author murphy
 * @since 2021/10/1 5:10 下午
 */
@Component
public class TokenDecode {

    private static final String PUBLIC_KEY = "public.key";

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 获得令牌
     * @return
     */
    public String getToken() {
        OAuth2AuthenticationDetails authentication = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String tokenValue = authentication.getTokenValue();
        return tokenValue;
    }

    /**
     * 获取当前的登录的用户的用户信息
     * @return
     * @throws IOException
     */
    public Map<String, String> getUserInfo() throws IOException {
        // 1. 获取令牌
        String token = getToken();
        // 2. 解析令牌 - 公钥
        String pubKey = getPubKey();
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(pubKey));
        String claims = jwt.getClaims();
        System.out.println(claims);

        // 3. 返回
        Map<String, String> map = objectMapper.readValue(claims, Map.class);
        return map;
    }

    private String getPubKey() {
        Resource resource = new ClassPathResource(PUBLIC_KEY);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamReader);
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            return null;
        }
    }
}
