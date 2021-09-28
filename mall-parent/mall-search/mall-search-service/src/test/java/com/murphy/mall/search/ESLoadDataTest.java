package com.murphy.mall.search;

import com.murphy.mall.item.po.Spu;
import com.murphy.mall.search.client.SpuClient;
import com.murphy.mall.search.dao.GoodsDao;
import com.murphy.mall.search.po.Goods;
import com.murphy.mall.search.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author murphy
 * @since 2021/9/23 6:47 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class ESLoadDataTest {

    @Autowired
    private IndexService indexService;

    @Autowired
    private SpuClient spuClient;

    @Autowired
    private GoodsDao goodsDao;

    @Test
    public void loadData() {
        List<Spu> spus = spuClient.selectAll();

        //spu转换成goods
        List<Goods> goods = spus.stream().map(spu -> this.indexService.buildGoods(spu)).collect(Collectors.toList());

        //导入索引库
        goodsDao.saveAll(goods);
    }
}
