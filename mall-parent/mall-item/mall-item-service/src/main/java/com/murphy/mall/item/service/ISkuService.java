package com.murphy.mall.item.service;

import com.murphy.mall.core.service.ICrudService;
import com.murphy.mall.item.po.Sku;

/**
 * Sku - Service
 *
 * @author murphy
 * @since 2021/9/19 3:51 下午
 */
public interface ISkuService extends ICrudService<Sku> {

    /**
     * 减库存
     * @param num
     * @param skuId
     */
    public void decrCount(Integer num, Long skuId);
}
