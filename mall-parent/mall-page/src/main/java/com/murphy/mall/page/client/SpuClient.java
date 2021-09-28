package com.murphy.mall.page.client;

import com.murphy.mall.item.api.SpuApi;
import com.murphy.mall.item.po.Category;
import com.murphy.mall.item.po.Spu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 调用查询SPU
 *
 * @author murphy
 * @since 2021/9/24 11:14 下午
 */
@FeignClient(name = "item-service", contextId = "p2", fallback = SpuClient.SpuClientFallback.class)
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
