package com.murphy.mall.search.client;

import com.murphy.mall.item.api.SpecParamApi;
import com.murphy.mall.item.po.SpecParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 搜索服务查询规格参数
 *
 * @author murphy
 * @since 2021/9/22 4:58 下午
 */
@FeignClient(name = "item-service", fallback = SpecParamClient.SpecParamClientFallback.class)
public interface SpecParamClient extends SpecParamApi {

    @Component
    @RequestMapping("/item/param-fallback")
    class SpecParamClientFallback implements SpecParamClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(SpecParamClientFallback.class);

        @Override
        public List<SpecParam> selectSpecParamApi(SpecParam entity) {
            LOGGER.error("发生异常，进入fallback方法！- Spec");
            return null;
        }
    }
}
