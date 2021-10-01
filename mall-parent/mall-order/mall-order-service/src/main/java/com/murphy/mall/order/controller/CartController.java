package com.murphy.mall.order.controller;

import com.murphy.mall.order.config.TokenDecode;
import com.murphy.mall.order.po.OrderItem;
import com.murphy.mall.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 购物车 - 控制器层
 *
 * @author murphy
 * @since 2021/10/1 2:40 下午
 */
@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private TokenDecode tokenDecode;

    /**
     * 添加购物车
     * @param id skuId
     * @param num 购买的数量
     * @return
     */
    @RequestMapping("/add")
    public ResponseEntity add(Long id, Integer num) throws IOException {
        // 从Spring Security中获得当前用户
//        String username = "murphy";
        Map<String, String> userInfo = tokenDecode.getUserInfo();
        String username = userInfo.get("user_name");
        cartService.add(id, num, username);
        return ResponseEntity.ok("添加成功！");
    }

    /**
     * 查询购物车数据
     * @return 商品详情
     */
    @RequestMapping("/list")
    public ResponseEntity<List<OrderItem>> list() throws IOException {
//        String username = "murphy";
        Map<String, String> userInfo = tokenDecode.getUserInfo();
        String username = userInfo.get("user_name");

        List<OrderItem> list = cartService.list(username);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}