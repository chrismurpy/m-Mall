package com.murphy.mall.order.service.impl;

import com.murphy.mall.item.po.Sku;
import com.murphy.mall.item.po.Spu;
import com.murphy.mall.order.client.SkuClient;
import com.murphy.mall.order.client.SpuClient;
import com.murphy.mall.order.po.OrderItem;
import com.murphy.mall.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车实现
 * @Des 新职课商城项目
 * @Author murphy
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private SkuClient skuClient;

    @Autowired
    private SpuClient spuClient;

    @Autowired
    private RedisTemplate redisTemplate;
    

    @Override
    public void add(Long id, Integer num, String username) {

        if (num <= 0) {
            //删除购物车数据
            redisTemplate.boundHashOps("Cart_" + username).delete(id);
            return;
        }

        //根据sku的Id得到sku
        Sku data = skuClient.edit(id);
        if (data != null) {
            //得到spu
            Long spuId = data.getSpuId();
            Spu spu = spuClient.edit(spuId);

            //将数据封装到OrderItem中
            //3.将数据存储到 购物车对象(order_item)中
            OrderItem orderItem = new OrderItem();

            orderItem.setCategoryId1(spu.getCid1());
            orderItem.setCategoryId2(spu.getCid2());
            orderItem.setCategoryId3(spu.getCid3());
            orderItem.setSpuId(spu.getId());
            orderItem.setSkuId(id);
            orderItem.setName(data.getTitle());//商品的名称  sku的名称
            orderItem.setPrice(data.getPrice());//sku的单价
            orderItem.setNum(num);//购买的数量
            orderItem.setPayMoney(orderItem.getNum() * orderItem.getPrice());//单价* 数量
            orderItem.setImage(data.getImages());//商品的图片

            redisTemplate.boundHashOps("Cart_" + username).put(id, orderItem);
        }

    }

    @Override
    public List<OrderItem> list(String username) {
        return redisTemplate.boundHashOps("Cart_" + username).values();
    }


}
