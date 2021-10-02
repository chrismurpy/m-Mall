package com.murphy.mall.order.service;

import com.murphy.mall.core.service.ICrudService;
import com.murphy.mall.order.po.Order;

/**
 * 订单信息
 * @author murphy
 */
public interface IOrderService extends ICrudService<Order> {

    /**
     * 添加订单
     * @param order
     */
    public void add(Order order);

}
