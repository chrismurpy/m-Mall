package com.murphy.mall.search.client;

import com.murphy.mall.item.api.CategoryApi;
import com.murphy.mall.item.po.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 搜索调用分类微服务 - api
 *
 * @author murphy
 * @since 2021/9/22 4:05 下午
 */
@FeignClient(name = "item-service", fallback = CategoryClient.CategoryClientFallback.class)
public interface CategoryClient extends CategoryApi {

    /**
     * 避免容器中requestMapping重复
     */
    @Component
    @RequestMapping("/item/category-fallback")
    class CategoryClientFallback implements CategoryClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(CategoryClientFallback.class);

        @Override
        public List<Category> list(Category category) {
            LOGGER.info("发生异常，进入fallback方法！- Category");
            return null;
        }

        @Override
        public List<String> queryNamesByIds(List<Long> ids) {
            LOGGER.info("发生异常，进入fallback方法！- Category");
            return null;
        }

        @Override
        public Category edit(Long id) {
            LOGGER.info("发生异常，进入fallback方法！- Category");
            return null;
        }
    }
}
