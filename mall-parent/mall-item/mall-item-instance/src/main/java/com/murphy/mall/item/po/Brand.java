package com.murphy.mall.item.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

/**
 * 品牌 - 实体类
 *
 * @author murphy
 * @since 2021/9/16 3:49 下午
 */
@Data
@TableName("brand_")
public class Brand extends BaseEntity  {

    /**
     * 名称
     */
    @TableField("name_")
    private String name;

    /**
     * 图片
     */
    @TableField("image_")
    private String image;

    /**
     * 首字母
     */
    @TableField("letter_")
    private String letter;

    /**
     * 瞬时属性，品牌的所属分类如[1,2,3,4]
     * exist - 与数据库的字段是否有映射
     */
    @TableField(exist = false)
    private Long[] categoryIds;
}
