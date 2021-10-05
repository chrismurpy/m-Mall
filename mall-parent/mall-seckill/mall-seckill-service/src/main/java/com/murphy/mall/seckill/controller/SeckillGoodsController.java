package com.murphy.mall.seckill.controller;

import com.murphy.mall.common.utils.DateUtils;
import com.murphy.mall.core.controller.BaseController;
import com.murphy.mall.seckill.po.SeckillGoods;
import com.murphy.mall.seckill.service.ISeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 秒杀商品 - Controller - 时间菜单
 *
 * @author murphy
 * @since 2021/10/4 4:12 下午
 */
@RestController
@RequestMapping("/seckill-goods")
@CrossOrigin
public class SeckillGoodsController extends BaseController<ISeckillGoodsService, SeckillGoods> {

    @Autowired
    private ISeckillGoodsService seckillGoodsService;

    /**
     * 返回五个时间间隔
     * @return
     */
    @GetMapping("/menus")
    public List<Date> dateMenus() {
        List<Date> dates = DateUtils.getDateMenus();
        for (Date date : dates) {
            System.out.println(date);
        }
        return dates;
    }

    /**
     * 查出当前时间对应的秒杀商品
     * @param time 秒杀时区
     * @return
     */
    @RequestMapping("/list/{time}")
    public List<SeckillGoods> list(@PathVariable("time") String time) {
        return seckillGoodsService.list(time);
    }

    /**
     * 根据时区和ID查询秒杀商品
     * @param time
     * @param id
     * @return
     */
    @RequestMapping("/one")
    public SeckillGoods one(String time, Long id) {
        return seckillGoodsService.one(time, id);
    }
}
