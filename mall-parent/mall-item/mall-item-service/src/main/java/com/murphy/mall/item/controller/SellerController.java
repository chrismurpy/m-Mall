package com.murphy.mall.item.controller;

import com.murphy.mall.core.controller.BaseController;
import com.murphy.mall.item.po.Seller;
import com.murphy.mall.item.service.ISellerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商家管理 - API
 *
 * @author murphy
 * @since 2022/4/17 12:56
 */
@RestController
@RequestMapping("/seller/")
public class SellerController extends BaseController<ISellerService, Seller> {
}
