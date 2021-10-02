package com.murphy.mall.order.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

/**
 * 订单明细
 * @author murphy
 */
@Data
@TableName("order_item_")
public class OrderItem extends BaseEntity {

    /**
     * 采用雪花算法，获得主键
     */
    @TableId(value = "id_", type = IdType.INPUT)
    private Long id;

    @TableField("category_id1_")
    private Long categoryId1;//1级分类

    @TableField("category_id2_")
    private Long categoryId2;//2级分类

    @TableField("category_id3_")
    private Long categoryId3;//3级分类

    @TableField("spu_id_")
    private Long spuId;//SPU_ID

    @TableField("sku_id_")
    private Long skuId;//SKU_ID

    @TableField("order_id_")
    private Long orderId;//订单ID

    @TableField("name_")
    private String name;//商品名称

    @TableField("price_")
    private Long price;//单价

    @TableField("num_")
    private Integer num;//数量

    @TableField("money_")
    private Long money;//总金额

    @TableField("pay_money_")
    private Long payMoney;//实付金额

    @TableField("image_")
    private String image;//图片地址

    @TableField("weight_")
    private Long weight;//重量

    @TableField("post_fee_")
    private Long postFee;//运费

    @TableField("is_return_")
    private String isReturn;//是否退货,0:未退货，1：已退货

}