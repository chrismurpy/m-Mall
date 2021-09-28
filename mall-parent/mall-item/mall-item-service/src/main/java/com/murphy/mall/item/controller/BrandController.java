package com.murphy.mall.item.controller;

import com.murphy.mall.core.controller.BaseController;
import com.murphy.mall.item.po.Brand;
import com.murphy.mall.item.po.Category;
import com.murphy.mall.item.service.IBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Brand - 控制器层
 *
 * @author murphy
 * @since 2021/9/16 4:03 下午
 */
@RestController
@RequestMapping(value = "/brand")
public class BrandController extends BaseController<IBrandService, Brand> {

    @Override
    public void afterEdit(Brand entity) {
        // 得到品牌的所属分类
        List<Category> categories = service.selectCategoryByBrand(entity.getId());
        Long[] ids = new Long[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            ids[i] = categories.get(i).getId();
        }
        entity.setCategoryIds(ids);
    }

    @ApiOperation(value = "根据ids查询品牌集合", notes = "根据ids查询")
    @GetMapping("/list-by-ids")
    public List<Brand> selectBrandByIds(@RequestParam("ids") List<Long> ids) {
        return service.selectBrandByIds(ids);
    }
}
