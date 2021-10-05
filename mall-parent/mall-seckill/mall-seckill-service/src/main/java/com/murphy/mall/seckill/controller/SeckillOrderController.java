package com.murphy.mall.seckill.controller;

import com.murphy.mall.core.controller.BaseController;
import com.murphy.mall.seckill.po.SeckillOrder;
import com.murphy.mall.seckill.service.ISeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒杀订单 - Controller
 *
 * @author murphy
 * @since 2021/10/5 2:37 下午
 */
@RestController
@RequestMapping("/seckill-order")
@CrossOrigin
public class SeckillOrderController extends BaseController<ISeckillOrderService, SeckillOrder> {

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @RequestMapping("/add")
    public ResponseEntity add(String username, String time, Long id) {

        try {
            Boolean add = seckillOrderService.add(id, time, username);
            // 返回成功
            if (add) {
                return ResponseEntity.ok("抢单成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity("抢单失败", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
