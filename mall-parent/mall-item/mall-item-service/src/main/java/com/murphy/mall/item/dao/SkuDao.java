package com.murphy.mall.item.dao;

import com.murphy.mall.core.dao.ICrudDao;
import com.murphy.mall.item.po.Sku;
import com.murphy.mall.item.po.Spu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Sku - CRUD
 *
 * @author murphy
 * @since 2021/9/19 3:14 下午
 */
public interface SkuDao extends ICrudDao<Sku> {

    /**
     * 外键查询
     * @param spuId
     * @return
     */
    @Select("select * from sku_ where spu_id_ = #{spuId}")
    public List<Sku> findBySpuId(Integer spuId);

    /**
     * 减库存
     * @param num
     * @param skuId
     * @return
     */
    @Update(value = "update sku_ set stock_ = stock_ - #{num} where id_ = #{skuId} and stock_ >= #{num}")
    public int decrCount(@Param("num") Integer num, @Param("skuId") Long skuId);
}
