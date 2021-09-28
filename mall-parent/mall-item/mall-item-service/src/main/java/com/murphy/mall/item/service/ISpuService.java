package com.murphy.mall.item.service;

import com.murphy.mall.core.service.ICrudService;
import com.murphy.mall.item.po.Spu;

/**
 * Spu - Service
 *
 * @author murphy
 * @since 2021/9/19 3:51 下午
 */
public interface ISpuService extends ICrudService<Spu> {

    /**
     * 保存SPU，包括如下表的数据
     *      Spu
     *      SpuDetail
     *      Skus
     * @param spu
     */
    public void saveSpu(Spu spu);

}
