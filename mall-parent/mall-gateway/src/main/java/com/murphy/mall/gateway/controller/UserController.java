package com.murphy.mall.gateway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.murphy.mall.gateway.client.UserClient;
import com.murphy.mall.gateway.util.BCrypt;
import com.murphy.mall.gateway.util.JwtUtil;
import com.murphy.mall.security.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户登录设计
 *
 * @author murphy
 * @since 2021/9/25 11:05 下午
 */
//@RequestMapping("/user")
//@RestController
public class UserController {

    @Autowired
    private UserClient userClient;
    @Autowired
    private ObjectMapper om;

    @RequestMapping("/login")
    public ResponseEntity login(String username, String password) throws Exception {
        // 1. 通过Feign调用用户微服务得到用户名对应的用户
        User user = userClient.getByUserName(username);
        // 2. 用户为空，返回401
        if (user == null) {
            return new ResponseEntity("用户名错误！", HttpStatus.UNAUTHORIZED);
        }
        // 3. 不为空，通过BCrypt工具类比对密文和输入的密码是否一致
        if (BCrypt.checkpw(password, user.getPassword())) {
            // 4. 认证成功，颁发令牌
            Map<String, Object> info = new HashMap<>();
            info.put("role", "USER");
            info.put("Success", "Success");
            info.put("username", username);
            String jwt = JwtUtil.createJwt(UUID.randomUUID().toString(), om.writeValueAsString(info),null);
            return ResponseEntity.ok(jwt);
        } else {
            return new ResponseEntity("密码错误！", HttpStatus.UNAUTHORIZED);
        }
    }

}
