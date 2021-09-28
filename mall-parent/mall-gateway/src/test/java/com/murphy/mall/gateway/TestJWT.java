package com.murphy.mall.gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试JWT
 *
 * @author murphy
 * @since 2021/9/25 7:22 下午
 */
public class TestJWT {

    // 当前时间戳
    long l1 = System.currentTimeMillis();
    // 过期时间
    long l2 = l1 + 20000;

    /**
     * 创建JWT令牌
     */
    @Test
    public void TestJwt() {
        JwtBuilder builder = Jwts.builder()
                // 设置唯一编号
                .setId("123456")
                // 设置主题，可以是JSON数据
                .setSubject("JWT使用的声明")
                // 设置签发时间
                .setIssuedAt(new Date())
                // 设置过期时间
                .setExpiration(new Date(l2))
                // 自定义载荷
                .claim("role","ADMIN")
                // 设置签名 - 使用HS256算法，并设置SecretKey(字符串)
                .signWith(SignatureAlgorithm.HS256,"murphy");
        // 自定义载荷
        Map<String, Object> map = new HashMap<>();
        map.put("myAddress", "cn");
        map.put("myCity", "Wuxi");
        builder.addClaims(map);
        // 构建并返回一个字符串
        System.out.println(builder.compact());
    }

    @Test
    public void TestParseJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NTYiLCJzdWIiOiJKV1Tkvb_nlKjnmoTlo7DmmI4iLCJpYXQiOjE2MzI1Njk5NDksImV4cCI6MTYzMjU2OTk2OSwicm9sZSI6IkFETUlOIiwibXlBZGRyZXNzIjoiY24iLCJteUNpdHkiOiJXdXhpIn0.U2Q81XECN9Qdplwk9s4vtP7DNgFdGi_2TQRR4DDTYDk";
        Claims claims = Jwts.parser()
                .setSigningKey("murphy")
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println(claims.get("role"));
        System.out.println(claims);
        System.out.println(claims.getSubject());
    }
}
