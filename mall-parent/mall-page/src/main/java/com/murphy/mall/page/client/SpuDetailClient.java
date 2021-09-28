package com.murphy.mall.page.client;

import com.murphy.mall.item.api.SpuDetailApi;
import com.murphy.mall.item.po.SpuDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 调用Spu详情
 *
 * @author murphy
 * @since 2021/9/24 11:21 下午
 */
@FeignClient(name = "item-service", contextId = "p3", fallback = SpuDetailClient.SpuDetailClientFallback.class)
public interface SpuDetailClient extends SpuDetailApi {

    @Component
    @RequestMapping("/spu-detail-fallback2")
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
            LOGGER.info("异常发生，进入fallback方法！- spuDetail");
            return null;
        }
    }
}
