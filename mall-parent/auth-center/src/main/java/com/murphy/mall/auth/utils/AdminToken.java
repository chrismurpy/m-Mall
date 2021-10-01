package com.murphy.mall.auth.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.io.IOException;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 传递管理员令牌
 *
 * @author murphy
 * @since 2021/10/1 4:20 下午
 */
public class AdminToken {

    public static String adminToken() throws IOException {
        // 证书文件
        String key_location = "murphy.jks";
        // 密钥库密码
        String keystore_password = "murphy";
        // 访问证书路径
        ClassPathResource resource = new ClassPathResource(key_location);
        // 密钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, keystore_password.toCharArray());
        // 密钥的密码，此密码和别名要匹配
        String keypassword = "murphy";
        // 密钥别名
        String alias = "murphy";
        // 密钥对（密钥和公钥）
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, keypassword.toCharArray());
        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 定义payload信息
        Map<String, Object> tokenMap = new HashMap<String, Object>();
        tokenMap.put("user_name", "admin");
        tokenMap.put("client_id", "client");
        tokenMap.put("authorities", new String[] {"ROLE_ADMIN"});
        // 生成JWT令牌
        Jwt jwt = JwtHelper.encode(new ObjectMapper().writeValueAsString(tokenMap), new RsaSigner(privateKey));
        // 取出JWT令牌
        String token = jwt.getEncoded();
        return token;
    }
}
