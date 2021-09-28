package com.murphy.mall.item.controller;

import com.murphy.mall.core.controller.BaseController;
import com.murphy.mall.item.po.Category;
import com.murphy.mall.item.service.ICategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Category - 分类管理
 *
 * @author murphy
 * @since 2021/9/18 11:23 上午
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController<ICategoryService, Category> {

    @ApiOperation(value = "根据ids查询names", notes = "根据分类ID查询名称列表")
    @GetMapping("/names")
    public ResponseEntity<List<String>> queryNamesByIds(@RequestParam("ids") List<Long> ids) {
        List<String> names = service.selectNamesByIds(ids);

        if (null == names || names.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(names);
    }

}