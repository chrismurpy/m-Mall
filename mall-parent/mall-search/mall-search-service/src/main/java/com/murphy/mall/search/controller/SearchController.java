package com.murphy.mall.search.controller;

import com.murphy.mall.search.po.SearchRequest;
import com.murphy.mall.search.po.SearchResult;
import com.murphy.mall.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基本搜索
 * @author murphy
 * @since 2021/9/24 3:18 下午
 */
@RestController
@CrossOrigin
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/query")
    public ResponseEntity<SearchResult> queryGoodsByPage(@RequestBody SearchRequest searchRequest) {
        SearchResult result = searchService.search(searchRequest);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(result);
    }
}
