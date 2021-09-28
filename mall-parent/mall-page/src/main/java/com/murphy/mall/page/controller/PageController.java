package com.murphy.mall.page.controller;

import com.murphy.mall.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理静态页面的生成
 *
 * @author murphy
 * @since 2021/9/25 12:41 上午
 */
@RestController
@RequestMapping("/page")
@CrossOrigin
public class PageController {

    @Autowired
    private PageService pageService;

    /**
     * 生成静态页面
     * @param id
     * @return
     */
    @RequestMapping("/createHtml/{id}")
    public ResponseEntity<String> createHtml(@PathVariable(name = "id") Long id) {
        pageService.createPageHtml(id);
        return ResponseEntity.ok("生成成功！");
    }
}
