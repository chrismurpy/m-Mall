package com.murphy.mall.seckill.service.impl;

import com.murphy.mall.common.utils.IdWorker;
import com.murphy.mall.common.utils.SystemConstants;
import com.murphy.mall.core.service.impl.CrudServiceImpl;
import com.murphy.mall.seckill.dao.SeckillGoodsDao;
import com.murphy.mall.seckill.po.SeckillGoods;
import com.murphy.mall.seckill.po.SeckillOrder;
import com.murphy.mall.seckill.pojo.SeckillStatus;
import com.murphy.mall.seckill.service.ISeckillOrderService;
import com.murphy.mall.seckill.task.MultiThreadingCreateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 秒杀订单 - 实现类
 *
 * @author murphy
 * @since 2021/10/4 4:18 下午
 */
@Service
public class SeckillOrderServiceImpl extends CrudServiceImpl<SeckillOrder> implements ISeckillOrderService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SeckillGoodsDao seckillGoodsDao;

    @Autowired
    private MultiThreadingCreateOrder multiThreadingCreateOrder;

    /**
     * 添加秒杀订单
     *
     * @param id
     * @param time
     * @param username
     * @return
     */
    @Override
    public Boolean add(Long id, String time, String username) {
        /**
         * 用户秒杀排队次数 -> namespace = UserQueueCount
         *                      - username 次数
         */
        Long userQueueCount = redisTemplate.boundHashOps(SystemConstants.SEC_KILL_QUEUE_REPEAT_KEY).increment(username,1);
        // 判断 是否大于1 如果是，返回异常，否则 就放行 重复了.
        if (userQueueCount > 1) {

            throw new RuntimeException("秒杀重复排队");
        }

        // 排队信息封装
        SeckillStatus seckillStatus = new SeckillStatus(username, new Date(), 1, id, time);
        // 将秒杀排队信息左压，leftPush存入Redis的List队列，List本身是一个队列 - 队列削峰
        redisTemplate.boundListOps(SystemConstants.SEC_KILL_USER_QUEUE_KEY).leftPush(seckillStatus);

        // 多线程抢单
        multiThreadingCreateOrder.createOrder();

        return true;
    }
}
