package com.murphy.mall.item.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.murphy.mall.core.dao.ICrudDao;
import com.murphy.mall.item.po.SpecGroup;

import java.util.List;

/**
 * 规格分组 - Dao - CRUD操作
 *
 * @author murphy
 * @since 2021/9/18 3:15 下午
 */
public interface SpecGroupDao extends ICrudDao<SpecGroup> {

    /**
     * 根据实体类的条件 - 生成动态SQL语句查询规格分组
     * @param specGroup
     * @return
     */
    public List<SpecGroup> selectList(SpecGroup specGroup);
}
