package com.murphy.mall.item.dao;

import com.murphy.mall.core.dao.ICrudDao;
import com.murphy.mall.item.po.Brand;
import com.murphy.mall.item.po.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌 - CRUD操作
 *
 * @author murphy
 * @since 2021/9/16 3:53 下午
 */
public interface BrandDao extends ICrudDao<Brand> {

    /**
     * 删除商品和分类关联
     * @param id
     * @return
     */
    public int deleteCategoryByBrand(Long id);

    /**
     * 关联商品和分类
     * @param categoryId
     * @param brandId
     * @return
     */
    public int insertCategoryAndBrand(@Param("categoryId") Long categoryId, @Param("brandId") Long brandId);

    /**
     * 查询商品的分类
     * @param id
     * @return
     */
    public List<Category> selectCategoryByBrand(Long id);
}
