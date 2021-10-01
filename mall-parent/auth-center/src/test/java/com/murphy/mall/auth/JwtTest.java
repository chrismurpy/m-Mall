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

import java.io.IOException;
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
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MzMwODU4ODEsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9NQU5BR0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiI5ZDlkOTdjZi00ZDg0LTQwZTQtODEzYi1hMGRiYWE5NGFmN2UiLCJjbGllbnRfaWQiOiJjbGllbnQiLCJzY29wZSI6WyJyZWFkIl19.MobPPQqMzwtNykS7bEwThfJrtZ1I34xhs4Zi0OvMgj-Rwadr-dn3LZU4G2a8tZNsKDtlcRpKeACLAd9GfUSuvxu3qU5jURvZZakMVShLyKBearRo231PTg5G2RMzQ6aFVi6g0R4FcnSppj1EjsoQLADDuhcVtRr04__lKtl5S0aGYDD1WxOyuLSFyeKJpMK4VK5CEVEd0EIdElku_sYncbZrovUjJWDfeXYEJJAS4bsa1k3-TwJ4jiCLXN4tDFQRBVDYB0_lNZV2wmFWml2ymYz_dX1pX7Qi63iGC_ixUq0Rdpf9HTpYGGlLvP04yGT0YMHSG1jseAUFlfhXspx0wA";
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

        try {
            Map<String, String> map = new ObjectMapper().readValue(claims, Map.class);
            System.out.println(map.get("user_name"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用私钥颁发令牌
     */
    @Test
    public void testCreateAdminJwt() throws Exception {
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
        tokenMap.put("user_name", "admin");
        tokenMap.put("authorities", new String[]{"ROLE_ADMIN"});
        tokenMap.put("client_id", "client");
        // 使用工具类 - 通过私钥颁发 Jwt 令牌
        Jwt jwt = JwtHelper.encode(new ObjectMapper().writeValueAsString(tokenMap), new RsaSigner(privateKey));
        // 取出私钥
        String token = jwt.getEncoded();
        System.out.println(token);
    }
}