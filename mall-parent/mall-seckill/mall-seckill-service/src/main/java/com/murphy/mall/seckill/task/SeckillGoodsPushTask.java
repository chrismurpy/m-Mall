package com.murphy.mall.seckill.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.murphy.mall.common.utils.DateUtils;
import com.murphy.mall.common.utils.SystemConstants;
import com.murphy.mall.seckill.dao.SeckillGoodsDao;
import com.murphy.mall.seckill.po.SeckillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 定时任务
 *
 * @author murphy
 * @since 2021/10/4 2:55 下午
 */
@Component
public class SeckillGoodsPushTask {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SeckillGoodsDao goodsDao;

    /**
     * 定时任务：每隔30秒执行一次
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void loadGoodsPushRedis() {
        // 1. 获取当前时间后的秒杀时间间隔(5)
        List<Date> dateMenus = DateUtils.getDateMenus();
        for (Date dateMenu : dateMenus) {
            // 2021100414
            String extName = DateUtils.data2str(dateMenu,DateUtils.PATTERN_YYYYMMDDHH);
            /**
             * select * from seckill_goods_ where stock_count>0
             and `status`='1'
             and date_menu_="当前秒杀时间段2020121716"
             and id_ not in redis
             */
            QueryWrapper<SeckillGoods> query = Wrappers.<SeckillGoods>query();
            // 库存>0
            query.gt("stock_count_",0);
            // 入库状态为1 - 审核通过
            query.eq("status_",1);
            // 参与的秒杀时间段
            query.eq("date_menu_", extName);

            // 获得现有的Redis中的秒杀商品ID
            Set keys = redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + extName).keys();
            if (keys != null && keys.size() > 0) {
                query.notIn("id_", keys);
            }
            List<SeckillGoods> seckillGoods = goodsDao.selectList(query);
            for (SeckillGoods seckillGood : seckillGoods) {
                redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + extName).put(seckillGood.getId(), seckillGood);
                // 设置有效期 - 2小时
                redisTemplate.expireAt(SystemConstants.SEC_KILL_GOODS_PREFIX + extName, DateUtils.addDateHour(dateMenu,2));
            }
        }
    }

}
