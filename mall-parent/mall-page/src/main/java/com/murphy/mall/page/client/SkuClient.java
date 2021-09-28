package com.murphy.mall.page.client;

import com.murphy.mall.item.api.SkuApi;
import com.murphy.mall.item.po.Sku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 调用Sku查询方法
 *
 * @author murphy
 * @since 2021/9/24 11:23 下午
 */
@FeignClient(name = "item-service", contextId = "p4", fallback = SkuClient.SkuClientFallback.class)
public interface SkuClient extends SkuApi {

    @Component
    @RequestMapping("/sku-fallback2")
    class SkuClientFallback implements SkuClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(SkuClientFallback.class);

        @Override
        public List<Sku> selectSkusBySpuId(Long spuId) {
            LOGGER.info("异常发生，进入fallback方法！- sku");
            return null;
        }
    }
}
