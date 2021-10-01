package com.murphy.mall.order.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

/**
 * 订单明细
 *
 * @author murphy
 * @since 2021/10/1 2:08 下午
 */
@Data
@TableName("order_item_")
public class OrderItem extends BaseEntity {

    // 1级分类
    @TableField("category_id1_")
    private Long categoryId1;
    // 2级分类
    @TableField("category_id2_")
    private Long categoryId2;
    // 3级分类
    @TableField("category_id3_")
    private Long categoryId3;
    // SPU_ID
    @TableField("spu_id_")
    private Long spuId;
    // SKU_ID
    @TableField("sku_id_")
    private Long skuId;
    // 订单ID
    @TableField("order_id_")
    private String orderId;
    // 商品名称
    @TableField("name_")
    private String name;
    // 单价
    @TableField("price_")
    private Long price;
    // 数量
    @TableField("num_")
    private Integer num;
    // 总金额
    @TableField("money_")
    private Long money;
    // 实付金额
    @TableField("pay_money_")
    private Long payMoney;
    // 图片地址
    @TableField("image_")
    private String image;
    // 重量
    @TableField("weight_")
    private Integer weight;
    // 运费
    @TableField("post_fee_")
    private Integer postFee;
    // 是否退货,0:未退货，1:已退货
    @TableField("is_return_")
    private String isReturn;

}
