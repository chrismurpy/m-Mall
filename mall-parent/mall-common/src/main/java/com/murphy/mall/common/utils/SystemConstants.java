package com.murphy.mall.common.utils;

public class SystemConstants {
    /**
     * 秒杀商品存储到前缀的KEY
     */
    public static final String SEC_KILL_GOODS_PREFIX="SeckillGoods_";


    /**
     * 存储域订单的hash的大key
     */
    public static final String SEC_KILL_ORDER_KEY="SeckillOrder";

    /**
     * 用户排队的队列的KEY
     */
    public static final String SEC_KILL_USER_QUEUE_KEY="SeckillOrderQueue";

    /**
     * 用于防止重复排队的hash的key 的值
     */
    public static final String SEC_KILL_QUEUE_REPEAT_KEY="UserQueueCount";

}
