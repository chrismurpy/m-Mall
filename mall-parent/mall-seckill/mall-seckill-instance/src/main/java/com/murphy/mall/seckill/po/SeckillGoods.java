package com.murphy.mall.seckill.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 秒杀商品 - 实体类
 *
 * @author murphy
 * @since 2021/10/4 2:27 下午
 */
@Data
@TableName("seckill_goods_")
public class SeckillGoods extends BaseEntity {

    // spu ID
    @TableField("spu_id_")
    private Long supId;

    // sku ID
    @TableField("sku_id_")
    private Long skuId;

    // 标题
    @TableField("name_")
    private String name;

    // 商品图片
    @TableField("small_pic_")
    private String smallPic;

    // 原价格
    @TableField("price_")
    private String price;

    // 秒杀价格
    @TableField("cost_price_")
    private String costPrice;

    // 添加日期
    @TableField("create_time_")
    private Date createTime;

    // 审核日期
    @TableField("check_time_")
    private Date checkTime;

    // 审核状态，0未审核，1审核通过，2审核不通过
    @TableField("status_")
    private String status;

    // 开始时间
    @TableField("start_time_")
    private Date startTime;

    // 结束时间
    @TableField("end_time_")
    private Date endTime;

    // 秒杀商品数
    @TableField("num_")
    private Integer num;

    // 剩余库存数
    @TableField("stock_count_")
    private Integer stockCount;

    // 描述
    @TableField("introduction_")
    private String introduction;

}
