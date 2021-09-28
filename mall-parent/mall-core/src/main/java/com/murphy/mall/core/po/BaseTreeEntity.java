package com.murphy.mall.core.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 树状结构实体类父类
 *
 * @author murphy
 * @file BaseTreeEntity.java
 *
 * 避免懒加载，json转换报错
 */
@Data
@JsonIgnoreProperties(value = {"handler"})
public class BaseTreeEntity extends BaseEntity {

    // 排序字段
    @TableField("order_")
    private Integer order;
    @TableField("parent_id_")
    // 父节点id
    private Long parentId;
    // 节点名称
    @TableField("title_")
    private String title;
    // 是否展开节点
    @TableField("expand_")
    private Boolean expand = false;

}
