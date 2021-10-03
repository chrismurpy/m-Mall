package com.murphy.mall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.murphy.mall.order.dao.IOrderDao;
import com.murphy.mall.order.dao.IOrderItemDao;
import com.murphy.mall.order.po.Order;
import com.murphy.mall.order.po.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IOrderItemDao orderItemDao;

    @Test
    public void closePayStatus() {
        Order order = orderDao.selectById(1444513139172446208L);
        System.out.println(order);
//        QueryWrapper<OrderItem> queryWrapper = Wrappers.<OrderItem>query().eq("order_id_",1444513139172446208L);
//        OrderItem orderItem = orderItemDao.selectOne(queryWrapper);
//        System.out.println(orderItem);

        OrderItem orderItem = orderItemDao.selectOne(Wrappers.<OrderItem>query().eq("order_id_", 1444513139172446208L));
        System.out.println(orderItem);
    }
}