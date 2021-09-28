package com.murphy.mall.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.murphy.mall.canal.client.CategoryClient;
import com.murphy.mall.canal.client.PageFeign;
import com.murphy.mall.item.po.Category;
import com.xpand.starter.canal.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author murphy
 * @CanalEventListener - 事件监听的注解 监听数据库的变化
 * @since 2021/9/21 7:30 下午
 */
@CanalEventListener
public class MyEventListener {

    @Resource
    private CategoryClient categoryClient;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private PageFeign pageFeign;

    /**
     * 自定义事件的触发
     * destination = "example" 指定某一个目的地 一定要和配置文件中的目录保持一致
     * schema = "canal-test" 要监听的数据库实例
     * table = {"t_user", "test_table"}, 要监听的表
     * eventType = CanalEntry.EventType.UPDATE 要监听的类型
     *
     * @param eventType
     * @param rowData
     */
    @ListenPoint(destination = "example", schema = "m-Mall", table = {"category_"}, eventType =
            {CanalEntry.EventType.UPDATE, CanalEntry.EventType.INSERT, CanalEntry.EventType.DELETE})
    public void onEvent4(CanalEntry.EventType eventType, CanalEntry.RowData rowData) throws JsonProcessingException {
        // 数据库中最新的商品分类
        List<Category> list = categoryClient.list(new Category());
        ObjectMapper objectMapper = new ObjectMapper();
        stringRedisTemplate.boundValueOps("cl").set(objectMapper.writeValueAsString(list));
    }

    @ListenPoint(destination = "example", schema = "m-Mall", table = {"spu"}, eventType =
            {CanalEntry.EventType.UPDATE, CanalEntry.EventType.INSERT, CanalEntry.EventType.DELETE})
    public void onEventCustomSpu(CanalEntry.EventType eventType, CanalEntry.RowData rowData) throws JsonProcessingException {
        // 判断操作类型
        if (eventType == CanalEntry.EventType.DELETE) {
            String spuId = "";
            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
            for (CanalEntry.Column column : beforeColumnsList) {
                if (column.getName().equals("id")) {
                    // spuId
                    spuId = column.getValue();
                    break;
                }
            }
            // todo 删除静态页面
        } else {
            // 新增或者更新
            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
            String spuId = "";
            for (CanalEntry.Column column : afterColumnsList) {
                if (column.getName().equals("id")) {
                    spuId = column.getValue();
                    break;
                }
            }
            // 更新 - 生成静态页面
            pageFeign.createHtml(Long.valueOf(spuId));
        }
    }
//    /**
//     * 当数据被添加的时候触发
//     * CanalEntry.EventType eventType 监听到的操作的类型 INSERT UPDATE ,DELETE ,CREATE INDEX ,GRAND
//     * CanalEntry.RowData rowData 被修改的数据()
//     *
//     * @param eventType
//     * @param rowData
//     */
//    @InsertListenPoint
//    public void onEvent(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
//        // Do Something.
//        System.out.println("事件监听器...");
//        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
//        for (CanalEntry.Column column : afterColumnsList) {
//            System.out.println(column.getName() + "：" + column.getValue());
//        }
//    }
//
//    //当数据被更新的时候触发
//    @UpdateListenPoint
//    public void onEvent1(CanalEntry.RowData rowData) {
//        //do something...
//        System.out.println("修改数据监听...");
//        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
//        for (CanalEntry.Column column : afterColumnsList) {
//            System.out.println(column.getName() + ":" + column.getValue());
//        }
//    }
//
//    // 当数据被删除的时候触发
//    @DeleteListenPoint
//    public void onEvent3(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
//        //do something...
//        System.out.println("删除数据监听...");
//        List<CanalEntry.Column> afterColumnsList = rowData.getBeforeColumnsList();
//        for (CanalEntry.Column column : afterColumnsList) {
//            System.out.println(column.getName() + ":" + column.getValue());
//        }
//    }
}
