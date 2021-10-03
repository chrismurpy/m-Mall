package com.murphy.mall.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.murphy.mall.core.service.impl.CrudServiceImpl;
import com.murphy.mall.item.po.Category;
import com.murphy.mall.item.service.ICategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Category - 实现CRUD Service
 *
 * @author murphy
 * @since 2021/9/18 11:09 上午
 */
@Service
public class CategoryServiceImpl extends CrudServiceImpl<Category> implements ICategoryService {

    @Override
    public List<Category> list(Category entity) {
        QueryWrapper<Category> query = Wrappers.query();
        if (StringUtils.isNotEmpty(entity.getTitle())) {
            query.like("title_", entity.getTitle());
        }
        if (null != entity.getParentId()) {
            query.eq("parent_id_", entity.getParentId());
        }
        if (null != entity.getIsRoot() && entity.getIsRoot().equals(1)) {
            // 查询所有的根
            query.isNull("parent_id_");
        }
        // 调用内置方法
        return getBaseMapper().selectList(query);
    }


    @Override
    public List<String> selectNamesByIds(List<Long> ids) {
        QueryWrapper<Category> queryWrapper = Wrappers.<Category>query().in("id_", ids);
        return getBaseMapper().selectList(queryWrapper).stream().map(item ->
                item.getTitle()).collect(Collectors.toList());
    }
}