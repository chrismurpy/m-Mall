package com.murphy.mall.item.service;

import com.murphy.mall.core.service.ICrudService;
import com.murphy.mall.item.po.SpecGroup;

import java.util.List;

/**
 * 商品规格参数分组 - Service
 *
 * @author murphy
 * @since 2021/9/18 3:44 下午
 */
public interface ISpecGroupService extends ICrudService<SpecGroup> {
    /**
     * 根据前端传递的规格参数列表，保存规格参数
     *
     * @param cid 分类ID
     * @param groups 前端传递的分组列表 - [{[cid:1, name:'', params:[..]},...]
     */
    public void saveGroup(Long cid, List<SpecGroup> groups);
}
