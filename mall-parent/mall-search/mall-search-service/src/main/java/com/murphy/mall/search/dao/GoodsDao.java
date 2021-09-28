package com.murphy.mall.search.dao;

import com.murphy.mall.search.po.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 商品展示 - dao
 *
 * @author murphy
 * @since 2021/9/22 11:14 下午
 */
public interface GoodsDao extends ElasticsearchRepository<Goods, Long> {

}
