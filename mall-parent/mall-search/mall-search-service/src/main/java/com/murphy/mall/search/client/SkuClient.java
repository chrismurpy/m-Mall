package com.murphy.mall.search.client;

import com.murphy.mall.item.api.SkuApi;
import com.murphy.mall.item.po.Sku;
import com.murphy.mall.item.po.Spu;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 搜索调用Sku查询微服务
 *
 * @author murphy
 * @since 2021/9/22 4:25 下午
 */
@FeignClient(name = "item-service", fallback = SkuClient.SkuClientFallback.class)
public interface SkuClient extends SkuApi {

    @Component
    @RequestMapping("/item/sku-fallback")
    class SkuClientFallback implements SkuClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(SkuClientFallback.class);

        @Override
        public List<Sku> selectSkusBySpuId(Long spuId) {
            LOGGER.error("发送异常，进入fallback方法！- Sku");
            return null;
        }

        @Override
        public Sku edit(Long id) {
            LOGGER.info("异常发生，进入fallback方法！- sku");
            return null;
        }
    }
}
