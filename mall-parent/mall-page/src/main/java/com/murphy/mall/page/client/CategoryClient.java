package com.murphy.mall.page.client;

import com.murphy.mall.item.api.CategoryApi;
import com.murphy.mall.item.po.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 调用分类查询功能
 *
 * @author murphy
 * @since 2021/9/24 11:07 下午
 */
@FeignClient(name = "item-service", contextId = "p1", fallback = CategoryClient.CategoryClientFallback.class)
public interface CategoryClient extends CategoryApi {

    @Component
    @RequestMapping("/category-fallback2")
    class CategoryClientFallback implements CategoryClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(CategoryClientFallback.class);

        @Override
        public List<Category> list(Category category) {
            LOGGER.info("异常发生，进入fallback方法！- category");
            return null;
        }

        @Override
        public List<String> queryNamesByIds(List<Long> ids) {
            LOGGER.info("异常发生，进入fallback方法！- category");
            return null;
        }

        @Override
        public Category edit(Long id) {
            LOGGER.info("异常发生，进入fallback方法！- category");
            return null;
        }
    }
}
