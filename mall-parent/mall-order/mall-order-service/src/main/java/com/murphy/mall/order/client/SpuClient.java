package com.murphy.mall.order.client;

import com.murphy.mall.item.api.SpuApi;
import com.murphy.mall.item.po.Spu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 购物车微服务调用SpuApi
 *
 * @author murphy
 * @since 2021/10/1 1:56 下午
 */
@FeignClient(name = "item-service", fallback = SpuClient.SpuClientFallback.class)
public interface SpuClient extends SpuApi {

    @Component
    @RequestMapping("/spu-fallback2")
    class SpuClientFallback implements SpuClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(SpuClientFallback.class);

        @Override
        public List<Spu> selectAll() {
            LOGGER.info("异常发生，进入fallback方法！- spu");
            return null;
        }

        @Override
        public Spu edit(Long id) {
            LOGGER.info("异常发生，进入fallback方法！- spu");
            return null;
        }
    }
}
