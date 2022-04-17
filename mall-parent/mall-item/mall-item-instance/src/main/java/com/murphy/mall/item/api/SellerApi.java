package com.murphy.mall.item.api;

import com.murphy.mall.item.po.Seller;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 商家管理
 *
 * @author murphy
 * @since 2022/4/17 12:48
 */
public interface SellerApi {

    @ApiOperation(value = "查询所有", notes = "查询所有Spu")
    @GetMapping("/list-all")
    public List<Seller> selectAll();

    @ApiOperation(value = "加载", notes = "根据ID加载")
    @GetMapping("/edit/{id}")
    public Seller edit(@PathVariable Long id);

}
