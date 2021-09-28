package com.murphy.mall.item.controller;

import com.murphy.mall.core.controller.BaseController;
import com.murphy.mall.core.po.ResponseBean;
import com.murphy.mall.item.po.SpecGroup;
import com.murphy.mall.item.service.ISpecGroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品规格参数分组 - Controller - SpecGroup
 *
 * @author murphy
 * @since 2021/9/18 4:22 下午
 */
@RestController
@RequestMapping(value = "/group")
public class SpecGroupController extends BaseController<ISpecGroupService, SpecGroup> {

    /**
     * 返回自定义实体类 - ResponseBean
     * POST请求的请求体传入 - RequestBody
     * @param specGroups
     * @return
     */
    @ApiOperation(value = "保存规格参数", notes = "保存规格参数")
    @PostMapping("/save-group")
    public ResponseBean saveGroup(@RequestBody List<SpecGroup> specGroups) {
        ResponseBean rm = new ResponseBean();
        try {
            if (null != specGroups && specGroups.size() > 0) {
                service.saveGroup(specGroups.get(0).getCid(), specGroups);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rm.setSuccess(false);
            rm.setMsg("保存失败！");
        }
        return rm;
    }
}
