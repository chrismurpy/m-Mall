package com.murphy.mall.item.api;

import com.murphy.mall.item.po.Category;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类查询
 *
 * @author murphy
 * @since 2021/9/21 9:12 下午
 */
@RequestMapping("/category")
public interface CategoryApi {

    @ApiOperation(value = "查询", notes = "根据实体类查询")
    @RequestMapping(value = "/list")
    public List<Category> list(Category category);

    @ApiOperation(value = "根据ids查询names", notes = "根据分类Id查询名称列表")
    @GetMapping(value = "/names")
    public List<String> queryNamesByIds(@RequestParam("ids") List<Long> ids);

    @ApiOperation(value = "加载数据", notes = "根据id加载")
    @GetMapping("/edit/{id}")
    public Category edit(@PathVariable Long id);
}
