package com.murphy.mall.page.service.impl;

import com.murphy.mall.common.utils.JsonUtils;
import com.murphy.mall.item.po.Category;
import com.murphy.mall.item.po.Sku;
import com.murphy.mall.item.po.Spu;
import com.murphy.mall.item.po.SpuDetail;
import com.murphy.mall.page.client.CategoryClient;
import com.murphy.mall.page.client.SkuClient;
import com.murphy.mall.page.client.SpuClient;
import com.murphy.mall.page.client.SpuDetailClient;
import com.murphy.mall.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成静态页面 - 实现类
 *
 * @author murphy
 * @since 2021/9/24 11:33 下午
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private SpuClient spuClient;
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private SpuDetailClient spuDetailClient;
    @Autowired
    private SkuClient skuClient;

    @Value("${pagepath}")
    private String pagepath;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 构建数据模版
     * @param spuId
     * @return
     */
    private Map<String, Object> buildDataModel(Long spuId) {
        // 构建数据模型
        Map<String, Object> dataMap = new HashMap<>();
        // 获取SPU和SKU列表
        Spu spu = spuClient.edit(spuId);
        Category c1 = categoryClient.edit(spu.getCid1());
        // 获取分类信息
        dataMap.put("category1", categoryClient.edit(spu.getCid1()));
        dataMap.put("category2", categoryClient.edit(spu.getCid2()));
        dataMap.put("category3", categoryClient.edit(spu.getCid3()));

        List<Sku> skus = skuClient.selectSkusBySpuId(spu.getId());
        List<String> images = new ArrayList<>();
        for (Sku sku : skus) {
            images.add(sku.getImages());
        }
        dataMap.put("imageList", images);

        SpuDetail spuDetail = spuDetailClient.edit(spu.getId());
        Map<String, Object> genericMap = JsonUtils.parseMap(spuDetail.getSpecialSpec(), String.class, Object.class);
        dataMap.put("specificationList", genericMap);

        dataMap.put("spu", spu);
        dataMap.put("spuDetail", spuDetail);

        // 根据spu查询Sku集合
        dataMap.put("skuList", skus);
        return dataMap;
    }

    /**
     * 生成静态页面
     *
     * @param id
     */
    @Override
    public void createPageHtml(Long id) {
        // 模版 + contextMap = html静态页面
        Context context = new Context();
        Map<String, Object> dataModel = buildDataModel(id);
        // model.addAttribute("key", "value") - ${key}
        context.setVariables(dataModel);

        File dir = new File(pagepath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dest = new File(dir,id + ".html");
        try {
            PrintWriter writer = new PrintWriter(dest,"UTF-8");
            templateEngine.process("item", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
