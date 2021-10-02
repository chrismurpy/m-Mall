package com.murphy.mall.item.api;

import com.murphy.mall.item.po.Sku;
import com.murphy.mall.item.po.Spu;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Sku查询
 *
 * @author murphy
 * @since 2021/9/22 4:22 下午
 */
@RequestMapping(value = "/sku")
public interface SkuApi {

    @ApiOperation(value = "查询spu对应的sku", notes = "根据spuId查询sku集合")
    @GetMapping(value = "/select-skus-by-spuid/{id}")
    public List<Sku> selectSkusBySpuId(@PathVariable("id") Long spuId);

    @ApiOperation(value = "加载", notes = "根据ID加载")
    @GetMapping("/edit/{id}")
    public Sku edit(@PathVariable Long id);

    @PostMapping("/decr-count")
    public void decrCount(@RequestParam("num") Integer num, @RequestParam("skuId") Long skuId);
}
