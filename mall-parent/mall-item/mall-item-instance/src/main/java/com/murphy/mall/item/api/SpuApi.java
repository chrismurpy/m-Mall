package com.murphy.mall.item.api;

import com.murphy.mall.item.po.Spu;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Spu查询所有
 * @author murphy
 * @since 2021/9/22 4:32 下午
 */
@RequestMapping("/spu")
public interface SpuApi {

    @ApiOperation(value = "查询所有", notes = "查询所有Spu")
    @GetMapping("/list-all")
    public List<Spu> selectAll();

    @ApiOperation(value = "加载", notes = "根据ID加载")
    @GetMapping("/edit/{id}")
    public Spu edit(@PathVariable Long id);
}
