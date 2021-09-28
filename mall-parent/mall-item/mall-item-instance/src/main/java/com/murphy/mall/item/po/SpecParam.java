package com.murphy.mall.item.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

/**
 * 规格具体参数 - 实体类
 *
 * @author murphy
 * @since 2021/9/18 2:45 下午
 */
@Data
@TableName("spec_param_")
public class SpecParam extends BaseEntity {

    @TableField("cid_")
    private Long cid;
    @TableField("group_id_")
    private Long groupId;
    @TableField("name_")
    private String name;
    @TableField("numeric_")
    private Boolean numeric;
    @TableField("unit_")
    private String unit;
    @TableField("generic_")
    private Boolean generic;
    @TableField("searching_")
    private Boolean searching;
    @TableField("segments_")
    private String segments;

}
