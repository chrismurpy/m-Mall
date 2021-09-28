package com.murphy.mall.item.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * SPU
 *
 * @author murphy
 * @since 2021/9/19 2:25 下午
 */
@Data
@TableName("spu_")
public class Spu extends BaseEntity {

    @TableField("brand_id_")
    private Long brandId;
    @TableField("cid1_")
    private Long cid1; // 一级类目
    @TableField("cid2_")
    private Long cid2; // 二级类目
    @TableField("cid3_")
    private Long cid3; // 三级类目
    @TableField("title_")
    private String title; // 标题
    @TableField("sub_title_")
    private String subTitle; // 子标题
    @TableField("saleable_")
    private Boolean saleable; // 是否上架
    @TableField("valid_")
    private Boolean valid; // 是否有效
    @TableField("create_time_")
    private Date createTime; // 创建时间
    @TableField("last_update_time_")
    private Date lastUpdateTime; // 最后修改时间
    @TableField(exist = false)
    private SpuDetail spuDetail; // SPU详情对象：描述、规格参数、SKU参数等
    @TableField(exist = false)
    private List<Sku> skus; // spu对应的sku集合
    @TableField(exist = false)
    private String brandName; // 品牌名称 - 查询是显示
    @TableField(exist = false)
    private String categoryName; // 分类名称 - 查询是显示
}
