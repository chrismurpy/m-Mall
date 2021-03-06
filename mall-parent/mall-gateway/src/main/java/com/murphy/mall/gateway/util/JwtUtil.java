package com.murphy.mall.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * 生成JWT令牌 - 工具类
 *
 * @author murphy
 * @since 2021/9/25 7:44 下午
 */
public class JwtUtil {

    /**
     * 有效期 - 60 * 60 * 1000 = 一小时
     */
    public static final Long JWT_TTL = 3600000L;
    /**
     * JWT 令牌信息
     */
    public static final String JWT_KEY = "murphy";

    /**
     * 生成令牌
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJwt(String id, String subject, Long ttlMillis) {
        // 指定算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 当前时间
        long nowMillis = System.currentTimeMillis();

        // 令牌签发时间
        Date now = new Date(nowMillis);

        // 如果令牌有效期为null，则默认设置有效期1小时
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        // 令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        // 生成密钥
        SecretKey secretKey = generalKey();

        // 封装JWT令牌信息
        JwtBuilder builder = Jwts.builder()
                // 唯一ID
                .setId(id)
                // 主题，可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer("admin")
                // 签发时间
                .setIssuedAt(now)
                // 签名算法以及密匙
                .signWith(signatureAlgorithm, secretKey)
                // 设置过期时间
                .setExpiration(expDate);
        return builder.compact();
    }

    /**
     * 生成加密 secretKey - AES加密
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JwtUtil.JWT_KEY.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey,0, encodedKey.length,"AES");
        return key;
    }

    /**
     * 解析令牌
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJwt(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
