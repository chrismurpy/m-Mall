package com.murphy.mall.auth;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    /**
     * 使用私钥颁发令牌
     */
    @Test
    public void testCreateJwt() throws Exception {
//        String keyLocation = "murphy.jks";
//        String keyPassword = "murphy";
//        ClassPathResource resource = new ClassPathResource(keyLocation);
        // 存储密钥的工厂对象
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("murphy.jks"),"murphy".toCharArray());
        // 密钥对 - 公钥 + 私钥
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("murphy","murphy".toCharArray());
        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 自定义 载荷信息(PayLoad)
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("id", 123);
        tokenMap.put("name", "murphy");
        tokenMap.put("roles", "r01, ro2, admin");
        // 使用工具类 - 通过私钥颁发 Jwt 令牌
        Jwt jwt = JwtHelper.encode(new ObjectMapper().writeValueAsString(tokenMap), new RsaSigner(privateKey));
        // 取出私钥
        String token = jwt.getEncoded();
        System.out.println(token);
    }

    /**
     * 使用 公钥 校验令牌
     */
    @Test
    public void testVerify() {
        // 令牌
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6InIwMSwgcm8yLCBhZG1pbiIsIm5hbWUiOiJtdXJwaHkiLCJpZCI6MTIzfQ.Gc2ntSS5XdV66PlT1sZK99lCBa3rUWTwwBljWUS5FglsZ_bZhy42PoG6v1EUFMm7z9k8apqQJfky2oWIqFUhcESE8V2esOo5qxQBc7B1NdL61TbBS4xMJsJHSYn0lkjuvqA1kudmBf5IkW-tjyK_Ni-sFUYf82wMsATsAoY2xY0jFYjxTSnOunMl3NHZOZkXgG708tw83ZhP_WpzZzV9Inp-SYTLyTniMKQ4hFbG2C3GawUeSlAAfqBQAkRI-5WygVFxN9p-oM2YQCq0BJKTlb-1iiSNPwI6Zon2x1HpkyFRWONsFOz2Z0ceD1LxzAiRIG570OwbXO7ZB3Ae8QLlMQ";
        // 公钥
        String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmFxQrckCtns0gKihj35D\n" +
                "HOdquKwpK3o25nExA3xLYCMDjE6BEbXQ56rVDrzfGbbaCytDW46nz9cE0EbWly17\n" +
                "4i001GObjaR8IE4Vw7a37+xnvaWSMeMWuM3R7XueopWN50IICO79/VU0OZpCHyUY\n" +
                "SS2qVrl5H/pvI9WXTxkqqJnnLP7GGtaHGgHLxmMNnvQxVJhSRooQlbASw1f4gJYl\n" +
                "iFH5/JUhio3sqTZirhswS4gb+BYW5ih+8vI99ME8Ezk8LkkVBw8SF2JWAIJHHDQR\n" +
                "sf+u8uqbusxRv4ZTw4DamqqyTqjhgFJ28TEaPiWi2PD6UnOx2N1l+lDh8Q9Dk842\n" +
                "/QIDAQAB\n" +
                "-----END PUBLIC KEY-----";
        // 校验令牌
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publicKey));
        String claims = jwt.getClaims();
        System.out.println(claims);
    }
}