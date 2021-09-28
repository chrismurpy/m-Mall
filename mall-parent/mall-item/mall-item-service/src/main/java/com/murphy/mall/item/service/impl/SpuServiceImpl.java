package com.murphy.mall.item.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.murphy.mall.core.service.impl.CrudServiceImpl;
import com.murphy.mall.item.po.Sku;
import com.murphy.mall.item.po.Spu;
import com.murphy.mall.item.service.ISkuService;
import com.murphy.mall.item.service.ISpuDetailService;
import com.murphy.mall.item.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spu - 实现CRUD Service
 *
 * @author murphy
 * @since 2021/9/19 4:02 下午
 */
@Service
public class SpuServiceImpl extends CrudServiceImpl<Spu> implements ISpuService {

    @Autowired
    private ISpuDetailService spuDetailService;
    @Autowired
    private ISkuService skuService;

    /**
     * 保存SPU，包括如下表的数据
     *      Spu
     *      SpuDetail
     *      Skus
     * @param spu
     * @
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveSpu(Spu spu) {
        // 1. 保存 Spu - 调用 tk-mybatis -> spu持久化产生主键属性
        this.saveOrUpdate(spu);
        // 2. 保存 spuDetail
        if (null == spu.getSpuDetail().getId()) {
            // 添加
            spu.getSpuDetail().setId(spu.getId());
            spuDetailService.save(spu.getSpuDetail());
        } else {
            // 修改
            spuDetailService.updateById(spu.getSpuDetail());
        }

        // 3. 保存 skus
        // 删除当前spu的所有sku
        skuService.remove(Wrappers.<Sku>query().eq("spu_id_",spu.getId()));
        for (Sku sku : spu.getSkus()) {
            sku.setSpuId(spu.getId());
            skuService.save(sku);
        }
    }
}
