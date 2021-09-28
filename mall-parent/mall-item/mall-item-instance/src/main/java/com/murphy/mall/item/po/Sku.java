package com.murphy.mall.item.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * SKU
 *
 * @author murphy
 * @since 2021/9/19 2:45 下午
 */
@Data
@TableName("sku_")
public class Sku extends BaseEntity {

    @TableField("spu_id_")
    private Long spuId; // 外键
    @TableField("title_")
    private String title;
    @TableField("images_")
    private String images;
    @TableField("price_")
    private Long price;
    @TableField("own_spec_")
    private String ownSpec; // 商品特殊规格的键值对
    @TableField("indexes_")
    private String indexes; // 商品特殊规格的下标
    @TableField("enable_")
    private Boolean enable; // 是否有效，逻辑删除用
    @TableField("create_time_")
    private Date createTime; // 创建时间
    @TableField("last_update_time_")
    private Date lastUpdateTime; // 最后修改时间
    @TableField("stock_")
    private Integer stock; // 库存
}
