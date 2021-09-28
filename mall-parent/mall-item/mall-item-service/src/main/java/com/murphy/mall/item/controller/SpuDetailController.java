package com.murphy.mall.item.controller;

import com.murphy.mall.core.controller.BaseController;
import com.murphy.mall.item.po.SpuDetail;
import com.murphy.mall.item.service.ISpuDetailService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpuDetail - Spu详情管理
 *
 * @author murphy
 * @since 2021/9/19 5:11 下午
 */
@RestController
@RequestMapping("/spu-detail")
public class SpuDetailController extends BaseController<ISpuDetailService, SpuDetail> {

}
