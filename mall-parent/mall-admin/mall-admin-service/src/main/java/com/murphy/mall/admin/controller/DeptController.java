package com.murphy.mall.admin.controller;

import com.murphy.mall.admin.po.Dept;
import com.murphy.mall.admin.service.IDeptService;
import com.murphy.mall.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController<IDeptService, Dept> {

}
