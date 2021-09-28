package com.murphy.mall.search.po;

import com.murphy.mall.item.po.Brand;
import com.murphy.mall.item.po.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 搜索 - 封装的结果类
 *
 * @author murphy
 * @since 2021/9/24 2:57 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {

    // 总行数
    private Long total;
    // 总页数
    private Long totalPage;
    // 当前页的数据
    private List items;
    private List<Category> categories;
    private List<Brand> brands;
    private List<Map<String, Object>> specs;

}
