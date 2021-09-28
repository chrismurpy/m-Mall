package com.murphy.mall.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.murphy.mall.core.service.impl.CrudServiceImpl;
import com.murphy.mall.item.dao.BrandDao;
import com.murphy.mall.item.po.Brand;
import com.murphy.mall.item.po.Category;
import com.murphy.mall.item.service.IBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Brand - 实现CRUD Service
 *
 * @author murphy
 * @since 2021/9/16 4:00 下午
 */
@Service
public class BrandServiceImpl extends CrudServiceImpl<Brand> implements IBrandService {

    /**
     * 根据商品ID查询分类
     *
     * @param id
     * @return
     */
    @Override
    public List<Category> selectCategoryByBrand(Long id) {
        return ((BrandDao) getBaseMapper()).selectCategoryByBrand(id);
    }

    /**
     * 根据品牌ID集合，得到品牌集合
     *
     * @param ids
     */
    @Override
    public List<Brand> selectBrandByIds(List<Long> ids) {
        QueryWrapper<Brand> queryWrapper = Wrappers.<Brand>query().in("id_", ids);
        return getBaseMapper().selectList(queryWrapper);
    }

    /**
     * 保存更新 - 品牌分类信息的修改
     *      readOnly=true表明所注解的方法或类只是读取数据。
     *      readOnly=false表明所注解的方法或类是增加，删除，修改数据。
     * @param entity
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public boolean saveOrUpdate(Brand entity) {
        boolean result = super.saveOrUpdate(entity);

        // 先删除商品和分类的关联
        ((BrandDao) getBaseMapper()).deleteCategoryByBrand(entity.getId());
        // 添加商品和分类的关系
        Long[] categoryIds = entity.getCategoryIds();
        if (categoryIds != null) {
            for (Long categoryId : categoryIds) {
                ((BrandDao) getBaseMapper()).insertCategoryAndBrand(categoryId, entity.getId());
            }
        }
        return result;
    }
}
