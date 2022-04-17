package com.murphy.mall.item.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

/**
 * @Title: 商家实体类
 * @Description:
 *
 * @Copyright 2019 lxs - Powered By murphy
 * @Author: lxs
 * @Date:  2019/11/20
 * @Version V1.0
 */

@Data
@TableName("tab_seller")
public class Seller extends BaseEntity {
    // 商家名称
    @TableField("sname")
    private String sname;
    // 商家电话
    @TableField("consphone")
    private String consphone;
    // 商家地址
    @TableField("address")
    private String address;

}
