package com.murphy.mall.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.murphy.mall.core.service.impl.CrudServiceImpl;
import com.murphy.mall.item.po.SpecParam;
import com.murphy.mall.item.service.ISpecParamService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品规格具体参数 - 实现类
 *
 * @author murphy
 * @since 2021/9/18 4:06 下午
 */
@Service
public class SpecParamServiceImpl extends CrudServiceImpl<SpecParam> implements ISpecParamService {

    @Override
    public List<SpecParam> list(SpecParam entity) {
        QueryWrapper<SpecParam> queryWrapper = Wrappers.<SpecParam>query();
        // 根据分类ID查询规格参数
        if (null != entity.getCid()) {
            queryWrapper.eq("cid_", entity.getCid());
        }
        if (null != entity.getSearching()) {
            queryWrapper.eq("searching_", entity.getSearching());
        }
        return getBaseMapper().selectList(queryWrapper);
    }
}
