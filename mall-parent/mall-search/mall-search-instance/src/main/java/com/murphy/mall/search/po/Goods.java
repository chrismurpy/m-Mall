package com.murphy.mall.search.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品搜索
 *
 * @author murphy
 * @since 2021/9/22 2:19 下午
 */
@Data
@Document(indexName = "goods_mall", type = "doc_mall", shards = 1, replicas = 0)
public class Goods {

    /**
     * spuId - 主键
     */
    @Id
    private Long id;
    /**
     * 用来搜索的字段 - 标题 / 分类 / 品牌
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String all;
    /**
     * 卖点 - 不分词不索引 - 关键词
     */
    @Field(type = FieldType.Keyword, index = false)
    private String subTitle;
    // 品牌ID
    private Long brandId;
    // 1级分类 - ID
    private Long cid1;
    // 2级分类 - ID
    private Long cid2;
    // 3级分类 - ID
    private Long cid3;
    // 创建时间
    private Date createTime;
    // 价格
    private List<Long> price;
    /**
     * sku信息 -> json结构
     */
    @Field(type = FieldType.Keyword, index = false)
    private String skus;
    /**
     * 可搜索的规格参数 - key：参数名 / value：参数值
     */
    private Map<String, Object> specs;
}
