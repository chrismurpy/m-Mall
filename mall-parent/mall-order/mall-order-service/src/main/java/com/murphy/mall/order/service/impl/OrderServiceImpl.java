package com.murphy.mall.order.service.impl;


import com.murphy.mall.common.utils.IdWorker;
import com.murphy.mall.core.service.impl.CrudServiceImpl;
import com.murphy.mall.order.client.SkuClient;
import com.murphy.mall.order.client.UserClient;
import com.murphy.mall.order.dao.IOrderItemDao;
import com.murphy.mall.order.po.Order;
import com.murphy.mall.order.po.OrderItem;
import com.murphy.mall.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单信息
 * @author murphy
 */
@Service
public class OrderServiceImpl extends CrudServiceImpl<Order> implements IOrderService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IOrderItemDao orderItemDao;

    @Autowired
    private SkuClient skuClient;

    @Autowired
    private UserClient userClient;

    @Override
    public void add(Order order) {
        // 1 添加订单主表(Order)数据
        order.setId(idWorker.nextId());

        // 2 循环购物车数据，添加订单明细(OrderItem)数据
        List<OrderItem> cartList = redisTemplate.boundHashOps("Cart_" + order.getUsername()).values();

        // 订单总数量
        Long totalNum =0L;
        // 订单总金额
        long totalMoney = 0L;
        for (OrderItem orderItem : cartList) {
            totalNum += orderItem.getNum();
            totalMoney += orderItem.getPayMoney();
            // 订单选项的iD
            orderItem.setId(idWorker.nextId());
            // 订单的iD
            orderItem.setOrderId(order.getId());
            // 未退货
            orderItem.setIsReturn("0");
            orderItemDao.insert(orderItem);

            // 3 调用商品微服务，减库存
            skuClient.decrCount(orderItem.getNum(), orderItem.getSkuId());
        }

        // 设置总数量
        order.setTotalNum(totalNum);
        // 设置总金额
        order.setTotalMoney(totalMoney);
        // 设置实付金额
        order.setPayMoney(totalMoney);
        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());
        // 0:未完成
        order.setOrderStatus("0");
        // 未支付
        order.setPayStatus("0");
        // 未发货
        order.setConsignStatus("0");
        // 未删除
        order.setIsDelete("0");

        getBaseMapper().insert(order);

        // 4 增加用户积分
        userClient.addPoint(10L, order.getUsername());

        // 5 删除redis中的购物车数据
        redisTemplate.delete("Cart_" + order.getUsername());
    }
}
