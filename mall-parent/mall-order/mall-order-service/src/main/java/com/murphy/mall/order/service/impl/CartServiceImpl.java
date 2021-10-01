package com.murphy.mall.order.service.impl;

import com.murphy.mall.item.po.Sku;
import com.murphy.mall.item.po.Spu;
import com.murphy.mall.order.client.SkuClient;
import com.murphy.mall.order.client.SpuClient;
import com.murphy.mall.order.po.Order;
import com.murphy.mall.order.po.OrderItem;
import com.murphy.mall.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车实现接口
 *
 * @author murphy
 * @since 2021/10/1 2:15 下午
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private SkuClient skuClient;
    @Autowired
    private SpuClient spuClient;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加购物车
     *
     * @param id       spuId
     * @param num      购买数量
     * @param username 用户名 - 从登录令牌中获得
     */
    @Override
    public void add(Long id, Integer num, String username) {
        if (num <= 0) {
            // 删除购物车数据
            redisTemplate.boundHashOps("Cart_" + username).delete(id);
            return;
        }
        // 1. 根据Sku的ID得到Sku
        Sku data = skuClient.edit(id);
        if (data != null) {
            // 2. 得到Spu
            Long spuId = data.getSpuId();
            Spu spu = spuClient.edit(spuId);
            // 3. 将数据封装到购物车对象OrderItem中
            OrderItem orderItem = new OrderItem();
            orderItem.setCategoryId1(spu.getCid1());
            orderItem.setCategoryId2(spu.getCid2());
            orderItem.setCategoryId3(spu.getCid3());
            orderItem.setSpuId(spu.getId());
            orderItem.setId(id);
            // 商品的名称 - SKU名称
            orderItem.setName(data.getTitle());
            // SKU的单价
            orderItem.setPrice(data.getPrice());
            // 购买的数量
            orderItem.setNum(num);
            // 单价 * 数量
            orderItem.setPayMoney(orderItem.getNum() * orderItem.getPrice());
            // 商品的图片
            orderItem.setImage(data.getImages());

            // 4. 加入Redis缓存
            redisTemplate.boundHashOps("Cart_" + username).put(id, orderItem);
        }
    }

    /**
     * 通过用户名 - 查询Redis中当前用户对应的购物车数据
     *
     * @param username
     * @return
     */
    @Override
    public List<OrderItem> list(String username) {
        return redisTemplate.boundHashOps("Cart_" + username).values();
    }
}
