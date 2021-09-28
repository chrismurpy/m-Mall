package com.murphy.mall.canal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.transform.Result;

/**
 * 调用Feign
 * @author murphy
 * @since 2021/9/25 1:09 上午
 */
@FeignClient(name = "item")
@RequestMapping("/page")
public interface PageFeign {

    /**
     * 根据spuID生成静态页面
     * @param id
     * @return
     */
    @RequestMapping("/createHtml/{id}")
    Result createHtml(@PathVariable(name = "id") Long id);
}
