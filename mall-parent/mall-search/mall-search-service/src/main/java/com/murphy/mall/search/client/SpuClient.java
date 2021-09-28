package com.murphy.mall.search.client;

import com.murphy.mall.item.api.SpuApi;
import com.murphy.mall.item.po.Spu;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 搜索微服务调用查询所有Spu
 *
 * @author murphy
 * @since 2021/9/22 4:34 下午
 */
@FeignClient(name = "item-service", fallback = SpuClient.SpuClientFallback.class)
public interface SpuClient extends SpuApi {

    @Component
    @RequestMapping("/item/spu-fallback")
    class SpuClientFallback implements SpuClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(SpuClientFallback.class);

        @Override
        public List<Spu> selectAll() {
            LOGGER.error("发送异常，进入fallback方法！- Spu");
            return null;
        }

        @Override
        public Spu edit(Long id) {
            LOGGER.error("发送异常，进入fallback方法！- Spu");
            return null;
        }
    }

}
