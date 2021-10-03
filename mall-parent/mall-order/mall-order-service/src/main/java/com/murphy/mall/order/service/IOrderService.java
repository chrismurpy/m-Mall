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

    /**
     * 修改订单 支付状态
     * @param outTradeNo
     * @param tradeNo
     */
    public void updatePayStatus(String outTradeNo, String tradeNo);

    /**
     * 取消订单
     * @param outTradeNo
     */
    public void closePayStatus(String outTradeNo);
}
