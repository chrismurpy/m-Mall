package com.murphy.mall.seckill.service;

import com.murphy.mall.core.service.ICrudService;
import com.murphy.mall.seckill.po.SeckillOrder;

/**
 * Service - 秒杀订单
 *
 * @author murphy
 * @since 2021/10/4 4:16 下午
 */
public interface ISeckillOrderService extends ICrudService<SeckillOrder> {

    /**
     * 添加秒杀订单
     * @param id
     * @param time
     * @param username
     * @return
     */
    Boolean add(Long id, String time, String username);

}
