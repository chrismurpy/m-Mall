package com.murphy.mall.item.api;

import com.murphy.mall.item.po.Brand;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 搜索专属调用 - API
 *
 * @author murphy
 * @since 2021/9/22 3:41 下午
 */
@RequestMapping("/brand")
public interface BrandApi {

    @ApiOperation(value = "根据ids查询品牌", notes = "根据ids查询")
    @GetMapping("/list-by-ids")
    public List<Brand> selectBrandByIds(@RequestParam("ids") List<Long> ids);

}
