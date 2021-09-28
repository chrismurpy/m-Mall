package com.murphy.mall.admin.controller;

import com.murphy.mall.admin.po.Dict;
import com.murphy.mall.admin.service.IDictService;
import com.murphy.mall.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dict")
public class DictController extends BaseController<IDictService, Dict> {


}
