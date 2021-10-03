package com.murphy.mall.order.listener;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.murphy.mall.order.client.SkuClient;
import com.murphy.mall.order.dao.IOrderItemDao;
import com.murphy.mall.order.po.Order;
import com.murphy.mall.order.po.OrderItem;
import com.murphy.mall.order.service.IOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * MQ - 监听延迟队列 - 订单30分钟未支付
 *
 * @author murphy
 * @since 2021/10/3 12:14 下午
 */
@Component
@RabbitListener(queues = "${mq.order.queue.dlx}")
public class OrderLazyListener {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderItemDao orderItemDao;
    @Autowired
    private SkuClient skuClient;

    @RabbitHandler
    public void handlerData(String msg) {
        if (StringUtils.isNotEmpty(msg)) {
            Long id = Long.parseLong(msg);
            Order order = orderService.getById(id);
            order.setOrderStatus("3");
            orderService.updateById(order);

            // 回滚库存
//            OrderItem orderItem = orderItemDao.selectOne(Wrappers.<OrderItem>query().eq("order_id_", order.getId()));
//            skuClient.rollbackCount(orderItem.getNum(), orderItem.getSkuId());
        }
    }
}
