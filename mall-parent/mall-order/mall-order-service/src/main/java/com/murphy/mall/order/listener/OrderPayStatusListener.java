package com.murphy.mall.order.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.murphy.mall.order.service.IOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * MQ - 监听支付
 *
 * @author murphy
 * @since 2021/10/3 12:14 下午
 */
@Component
@RabbitListener(queues = "${mq.pay.queue.order}")
public class OrderPayStatusListener {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IOrderService orderService;

    @RabbitHandler
    public void handlerData(String msg) {
        // 反序列化消息数据
        Map<String, String> map = null;
        try {
             map = objectMapper.readValue(msg, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 更新订单状态
        if (map != null) {
            if (map.get("trade_status").equals("TRADE_SUCCESS")) {
                orderService.updatePayStatus(map.get("out_trade_no"), map.get("trade_no"));
            } else {
                // 删除订单，支付失败，回滚库存
                orderService.closePayStatus(map.get("out_trade_no"));
            }
        }
    }
}
