package com.murphy.mall.item.api;

import com.murphy.mall.item.po.Sku;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Sku查询
 *
 * @author murphy
 * @since 2021/9/22 4:22 下午
 */
@RequestMapping(value = "/item/sku")
public interface SkuApi {

    @ApiOperation(value = "查询spu对应的sku", notes = "根据spuId查询sku集合")
    @GetMapping(value = "/select-skus-by-spuid/{id}")
    public List<Sku> selectSkusBySpuId(@PathVariable("id") Long spuId);
}
