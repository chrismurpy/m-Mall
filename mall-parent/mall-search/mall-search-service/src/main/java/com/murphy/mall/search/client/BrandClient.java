package com.murphy.mall.search.client;

import com.murphy.mall.item.api.BrandApi;
import com.murphy.mall.item.po.Brand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author murphy
 * @since 2021/9/22 3:45 下午
 */
@FeignClient(name = "item-service", fallback = BrandClient.BrandClientFallback.class)
public interface BrandClient extends BrandApi {

    @Component
    @RequestMapping("/item/brand-fallback")
    class BrandClientFallback implements BrandClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(BrandClientFallback.class);

        @Override
        public List<Brand> selectBrandByIds(List<Long> ids) {
            LOGGER.info("出现异常，进入fallback方法！- Brand");
            return null;
        }
    }

}
