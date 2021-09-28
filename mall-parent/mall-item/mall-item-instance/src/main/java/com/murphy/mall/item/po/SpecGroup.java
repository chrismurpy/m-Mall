package com.murphy.mall.item.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 规格参数分组 - 实体类
 *
 * @author murphy
 * @since 2021/9/18 2:43 下午
 */
@Data
@TableName("spec_group_")
@JsonIgnoreProperties(value = {"handler"}) // 避免懒加载错误 - 产生代理对象属性去除
public class SpecGroup extends BaseEntity {

    @TableField("cid_")
    private Long cid;
    @TableField("name_")
    private String name;

    /**
     * 瞬时属性
     * 当前组下的所有规格项，使用ResultMap懒加载查询
     */
    @TableField(exist = false)
    private List<SpecParam> params;

}
