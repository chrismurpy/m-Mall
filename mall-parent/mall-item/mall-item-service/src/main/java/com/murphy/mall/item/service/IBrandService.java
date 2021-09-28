package com.murphy.mall.item.service;

import com.murphy.mall.core.service.ICrudService;
import com.murphy.mall.item.po.Brand;
import com.murphy.mall.item.po.Category;

import java.util.List;

/**
 * Brand - Service 接口
 *
 * @author murphy
 * @since 2021/9/16 3:59 下午
 */
public interface IBrandService extends ICrudService<Brand> {

    /**
     * 根据商品ID查询分类
     * @param id
     * @return
     */
    public List<Category> selectCategoryByBrand(Long id);

    /**
     * 根据品牌ID集合，得到品牌集合
     * @param ids
     */
    public List<Brand> selectBrandByIds(List<Long> ids);
}
