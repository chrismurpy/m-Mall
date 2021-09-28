package com.murphy.mall.search.po;

import java.util.HashMap;
import java.util.Map;

/**
 * 搜索请求对象
 *
 * @author murphy
 * @since 2021/9/24 3:01 下午
 */
public class SearchRequest {
    // 搜索条件
    private String key;
    // 当前页
    private Integer page;
    // 排序条件
    private String sortBy;
    // 升序 / 降序
    private Boolean descending;

    private Map<String, String> filter = new HashMap<>();
    // 每页大小，不从页面接收，而是固定大小
    private static final Integer DEFAULT_SIZE = 20;
    // 默认页数 - 1
    private static final Integer DEFAULT_PAGE = 1;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        if (page == null) {
            return DEFAULT_PAGE;
        }
        // 获取页码时做校验，不可小于1
        return Math.max(DEFAULT_PAGE, page);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return DEFAULT_SIZE;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Boolean getDescending() {
        return descending;
    }

    public void setDescending(Boolean descending) {
        this.descending = descending;
    }

    public Map<String, String> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, String> filter) {
        this.filter = filter;
    }

    public static Integer getDefaultSize() {
        return DEFAULT_SIZE;
    }

    public static Integer getDefaultPage() {
        return DEFAULT_PAGE;
    }
}
