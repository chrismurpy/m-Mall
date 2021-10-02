package com.murphy.mall.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.murphy.mall.core.service.impl.CrudServiceImpl;
import com.murphy.mall.item.dao.SkuDao;
import com.murphy.mall.item.po.Sku;
import com.murphy.mall.item.service.ISkuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Sku - 实现CRUD Service
 *
 * @author murphy
 * @since 2021/9/19 3:52 下午
 */
@Service
public class SkuServiceImpl extends CrudServiceImpl<Sku> implements ISkuService {

    /**
     * 根据spuId查询sku
     * @param entity
     * @return
     */
    @Override
    public List<Sku> list(Sku entity) {
        QueryWrapper<Sku> queryWrapper = Wrappers.<Sku>query();
        if (null != entity.getSpuId()) {
            queryWrapper.eq("spu_id_", entity.getSpuId());
        }
        return getBaseMapper().selectList(queryWrapper);
    }

    /**
     * 减库存
     *
     * @param num
     * @param skuId
     */
    @Override
    public void decrCount(Integer num, Long skuId) {
        ((SkuDao) getBaseMapper()).decrCount(num, skuId);
    }
}
