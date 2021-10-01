package com.murphy.mall.item.api;

import com.murphy.mall.item.po.SpuDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 根据ID查询SpuDetail
 *
 * @author murphy
 * @since 2021/9/22 4:40 下午
 */
@RequestMapping("/spu-detail")
public interface SpuDetailApi {

    /**
     * 根据ID加载
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "加载数据", notes = "根据ID加载")
    @GetMapping("/edit/{id}")
    public SpuDetail edit(@PathVariable Long id);

}
