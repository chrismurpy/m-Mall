package com.murphy.mall.seckill.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 秒杀订单 - 实体类
 *
 * @author murphy
 * @since 2021/10/4 2:32 下午
 */
@Data
@TableName("seckill_order_")
public class SeckillOrder extends BaseEntity {

    // 秒杀商品ID
    @TableField("seckill_id_")
    private Long seckillId;

    // 支付金额
    @TableField("money_")
    private String money;

    // 用户
    @TableField("user_id_")
    private String userId;

    @TableField("seller_id_")
    private String sellerId;

    // 创建时间
    @TableField("create_time_")
    private Date createTime;

    // 支付时间
    @TableField("pay_time_")
    private Date payTime;

    // 状态，0未支付，1已支付
    @TableField("status_")
    private String status;

    // 收货人地址
    @TableField("receiver_address_")
    private String receiverAddress;

    // 收货人电话
    @TableField("receiver_mobile_")
    private String receiverMobile;

    // 收货人
    @TableField("receiver_")
    private String receiver;

    // 交易流水
    @TableField("transaction_id_")
    private String transactionId;
}
