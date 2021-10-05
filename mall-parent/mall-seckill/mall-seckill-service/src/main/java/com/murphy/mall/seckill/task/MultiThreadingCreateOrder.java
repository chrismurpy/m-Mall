package com.murphy.mall.seckill.task;

import com.murphy.mall.common.utils.IdWorker;
import com.murphy.mall.common.utils.SystemConstants;
import com.murphy.mall.seckill.dao.SeckillGoodsDao;
import com.murphy.mall.seckill.dao.SeckillOrderDao;
import com.murphy.mall.seckill.po.SeckillGoods;
import com.murphy.mall.seckill.po.SeckillOrder;
import com.murphy.mall.seckill.pojo.SeckillStatus;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 多线程抢单 - 任务
 *
 * @author murphy
 * @since 2021/10/5 2:57 下午
 */
@Component
public class MultiThreadingCreateOrder {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SeckillGoodsDao seckillGoodsDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 多线程下单操作
     */
    @Async
    public void createOrder() {
        // 右取
        SeckillStatus seckillStatus = (SeckillStatus) redisTemplate.boundListOps(SystemConstants.SEC_KILL_USER_QUEUE_KEY).rightPop();

        if (seckillStatus != null) {
            // 秒杀时区
            String time = seckillStatus.getTime();
            // 秒杀商品ID
            Long id = seckillStatus.getGoodsId();
            // 用户名
            String username = seckillStatus.getUsername();

            // 分布式锁
            RLock lock = redissonClient.getLock("seckillstock:" + id);

            try {
                // 获得分布式锁 -
                /**
                 * 参数1 - waitTime：等待锁的时间
                 * 参数2 - leaseTime：持有锁的时间
                 * 参数3：时间单位
                 */
                lock.tryLock(20,20, TimeUnit.SECONDS);

                // 获取秒杀商品信息
                SeckillGoods goods = (SeckillGoods) redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + time).get(id);

                // 如果没有库存 - 直接抛出异常
                if (goods == null || goods.getStockCount() <= 0) {
                    throw new RuntimeException("已售罄！");
                }
                // 将秒杀预订单存入Redis
                SeckillOrder seckillOrder = new SeckillOrder();
                seckillOrder.setId(idWorker.nextId());
                seckillOrder.setSeckillId(id);
                seckillOrder.setMoney(goods.getCostPrice());
                seckillOrder.setUserId(username);
                seckillOrder.setCreateTime(new Date());
                seckillOrder.setStatus("0");

                // 模拟下单耗时操作
                try {
                    System.out.println("...开始模拟下单操作...");
                    Thread.sleep(10000);
                    System.out.println("...结束模拟下单操作...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**
                 * Hash -> namespace = SeckillOrder
                 *      ->    key    = value
                 *      -> username  = seckillOrder
                 */
                redisTemplate.boundHashOps(SystemConstants.SEC_KILL_ORDER_KEY).put(username, seckillOrder);

                // 库存递减
                goods.setStockCount(goods.getStockCount() - 1);

                // 库存为0时，同步库存到MySQL
                if (goods.getStockCount() <= 0) {
                    seckillGoodsDao.updateById(goods);
                    // 清除Redis中的秒杀商品
                    redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + time).delete(id);
                } else {
                    // 同步库存到Redis
                    redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + time).put(id, goods);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 解锁 - 根据业务时间释放
                lock.unlock();
            }
        }
    }
}
