package com.murphy.mall.order.service;

import com.murphy.mall.order.po.OrderItem;

import java.util.List;

/**
 * 购物车接口
 *
 * @author murphy
 * @since 2021/10/1 2:13 下午
 */
public interface CartService {

    /**
     * 添加购物车
     * @param id spuId
     * @param num 购买数量
     * @param username 用户名 - 从登录令牌中获得
     */
    public void add(Long id, Integer num, String username);

    /**
     * 通过用户名 - 查询Redis中当前用户对应的购物车数据
     * @param username
     * @return
     */
    public List<OrderItem> list(String username);
}
