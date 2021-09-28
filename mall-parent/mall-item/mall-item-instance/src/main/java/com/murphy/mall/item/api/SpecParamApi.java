package com.murphy.mall.item.api;

import com.murphy.mall.item.po.SpecParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 查询规格参数
 *
 * @author murphy
 * @since 2021/9/22 4:52 下午
 */
@RequestMapping(value = "/item/param")
public interface SpecParamApi {

    @ApiOperation(value = "查询规格", notes = "根据实体条件查询参数")
    @PostMapping(value = "/select-param-by-entity", consumes = "application/json")
    public List<SpecParam> selectSpecParamApi(@RequestBody SpecParam entity);
}
