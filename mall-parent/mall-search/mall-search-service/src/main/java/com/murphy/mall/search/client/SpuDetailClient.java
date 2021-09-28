package com.murphy.mall.search.client;

import com.murphy.mall.item.api.SpuApi;
import com.murphy.mall.item.api.SpuDetailApi;
import com.murphy.mall.item.po.SpuDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 搜索服务调用查询SpuDetail
 *
 * @author murphy
 * @since 2021/9/22 4:43 下午
 */
@FeignClient(name = "item-service", fallback = SpuDetailClient.SpuDetailClientFallback.class)
public interface SpuDetailClient extends SpuDetailApi {

    @Component
    @RequestMapping("/item/spu-detail-fallback")
    class SpuDetailClientFallback implements SpuDetailClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(SpuDetailClientFallback.class);

        /**
         * 根据ID加载
         *
         * @param id
         * @return
         */
        @Override
        public SpuDetail edit(Long id) {
            LOGGER.error("发生异常，进入fallback方法！- SpuDetail");
            return null;
        }
    }
}
