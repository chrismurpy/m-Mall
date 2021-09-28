package com.murphy.mall.item.service;

import com.murphy.mall.core.service.ICrudService;
import com.murphy.mall.item.dao.CategoryDao;
import com.murphy.mall.item.po.Category;

import java.util.List;

/**
 * 商品分类 - Service
 *
 * @author murphy
 * @since 2021/9/18 11:08 上午
 */
public interface ICategoryService extends ICrudService<Category> {

    /**
     * 根据ID集合，得到分类名称
     * @param ids
     * @return
     */
    public List<String> selectNamesByIds(List<Long> ids);

}
