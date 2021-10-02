package com.murphy.mall.order.controller;

import com.murphy.mall.core.controller.BaseController;
import com.murphy.mall.order.config.TokenDecode;
import com.murphy.mall.order.po.Order;
import com.murphy.mall.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 订单 - 下单实现
 * @author murphy
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController<IOrderService, Order> {

    @Autowired
    private TokenDecode tokenDecode;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Order order) throws IOException {
        order.setUsername(tokenDecode.getUserInfo().get("user_name"));
        service.add(order);
        return ResponseEntity.ok("下单成功");
    }
}
