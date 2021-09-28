package com.murphy.mall.item.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseTreeEntity;
import lombok.Data;

/**
 * 分类 - 实体类
 *
 * @author murphy
 * @since 2021/9/16 5:09 下午
 */
@Data
@TableName(value = "category_")
public class Category extends BaseTreeEntity {

    /**
     * 是否为父节点
     */
    @TableField("is_parent_")
    private Boolean isParent = false;

    /**
     * 值为1，查询根节点条件
     */
    @TableField(exist = false)
    private Integer isRoot = 0;

    public String getLabel() {
        // TreeSelect需要的属性
        return this.getTitle();
    }
}

