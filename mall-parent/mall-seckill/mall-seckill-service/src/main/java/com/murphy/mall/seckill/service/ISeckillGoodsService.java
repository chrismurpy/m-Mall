package com.murphy.mall.seckill.service;

import com.murphy.mall.core.service.ICrudService;
import com.murphy.mall.seckill.po.SeckillGoods;

import java.util.List;

/**
 * Service - 秒杀商品
 *
 * @author murphy
 * @since 2021/10/4 4:15 下午
 */
public interface ISeckillGoodsService extends ICrudService<SeckillGoods> {

    /**
     * 获取指定时间对应的秒杀商品列表
     * @param key 时间戳
     * @return
     */
    List<SeckillGoods> list(String key);

    /**
     * 根据ID查询商品详情
     * @param time 时间区间
     * @param id 商品ID
     * @return
     */
    SeckillGoods one(String time, Long id);
}
