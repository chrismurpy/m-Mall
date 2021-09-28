package com.murphy.mall.item.controller;

import com.murphy.mall.core.controller.BaseController;
import com.murphy.mall.item.po.SpecParam;
import com.murphy.mall.item.service.ISpecParamService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品规格具体参数 - SpecParam
 *
 * @author murphy
 * @since 2021/9/18 10:37 下午
 */
@RestController
@RequestMapping(value = "/param")
public class SpecParamController extends BaseController<ISpecParamService, SpecParam> {

    @ApiOperation(value = "查询", notes = "根据实体条件查询参数")
    @PostMapping(value = "/select-param-by-entity")
    public List<SpecParam> selectSpecParamApi(@RequestBody SpecParam entity) {
        return service.list(entity);
    }
}
