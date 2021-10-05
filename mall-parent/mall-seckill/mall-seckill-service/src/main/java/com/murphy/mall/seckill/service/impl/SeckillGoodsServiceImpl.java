package com.murphy.mall.seckill.service.impl;

import com.murphy.mall.common.utils.SystemConstants;
import com.murphy.mall.core.service.impl.CrudServiceImpl;
import com.murphy.mall.seckill.po.SeckillGoods;
import com.murphy.mall.seckill.service.ISeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 秒杀商品 - 实现类
 *
 * @author murphy
 * @since 2021/10/4 4:17 下午
 */
@Service
public class SeckillGoodsServiceImpl extends CrudServiceImpl<SeckillGoods> implements ISeckillGoodsService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查出当前时间对应的秒杀商品
     * @param key 秒杀时区
     * @return
     */
    @Override
    public List<SeckillGoods> list(String key) {
        return redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + key).values();
    }

    /**
     * 根据ID查询商品详情
     *
     * @param time 时间区间
     * @param id   商品ID
     * @return
     */
    @Override
    public SeckillGoods one(String time, Long id) {
        return (SeckillGoods) redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + time).get(id);
    }
}
