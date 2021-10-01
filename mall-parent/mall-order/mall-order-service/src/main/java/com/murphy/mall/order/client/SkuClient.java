package com.murphy.mall.order.client;

import com.murphy.mall.item.api.SkuApi;
import com.murphy.mall.item.po.Sku;
import com.murphy.mall.item.po.Spu;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 购物车微服务 - 调用SkuApi
 *
 * @author murphy
 * @since 2021/10/1 1:53 下午
 */
@FeignClient(name = "item-service", fallbackFactory = SkuClient.SkuClientFallbackFactory.class)
public interface SkuClient extends SkuApi {

    @Component
    @RequestMapping("/sku-fallback")
    class SkuClientFallback implements SkuClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(SkuClientFallback.class);

        @Override
        public List<Sku> selectSkusBySpuId(Long spuId) {
            LOGGER.info("异常发生，进入fallback方法！- sku");
            return null;
        }

        @Override
        public Sku edit(Long id) {
            LOGGER.info("异常发生，进入fallback方法！- sku");
            return null;
        }
    }

    @Component
    @RequestMapping("/sku-fallback-factory")
    class SkuClientFallbackFactory implements FallbackFactory<SkuClient> {

        Logger logger = LoggerFactory.getLogger(SkuClientFallbackFactory.class);

        @Override
        public SkuClient create(Throwable throwable) {
            throwable.printStackTrace();
            logger.error(throwable.getMessage());
            return new SkuClient() {
                @Override
                public List<Sku> selectSkusBySpuId(Long spuId) {
                    return null;
                }

                @Override
                public Sku edit(Long id) {
                    return null;
                }
            };
        }
    }
}
