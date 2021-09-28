package com.murphy.mall.search.service;

import com.murphy.mall.item.po.Brand;
import com.murphy.mall.item.po.Category;
import com.murphy.mall.item.po.SpecParam;
import com.murphy.mall.search.client.BrandClient;
import com.murphy.mall.search.client.CategoryClient;
import com.murphy.mall.search.client.SpecParamClient;
import com.murphy.mall.search.dao.GoodsDao;
import com.murphy.mall.search.po.Goods;
import com.murphy.mall.search.po.SearchRequest;
import com.murphy.mall.search.po.SearchResult;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索服务
 *
 * @author murphy
 * @since 2021/9/24 3:06 下午
 */
@Service
public class SearchService {

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private SpecParamClient specParamClient;

    @Autowired
    private GoodsDao goodsDao;

    public SearchResult search(SearchRequest searchRequest) {
        String key = searchRequest.getKey();
        if (key == null) {
            return null;
        }

        // 构件本地查询对象
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 设置返回的查询字段
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"id", "subTitle", "skus"}, null));

        // 构件布尔查询
        BoolQueryBuilder boolQueryBuilder = buildBasicQueryWithFilter(searchRequest);

        queryBuilder.withQuery(boolQueryBuilder);

        // 分页
        // es分页从0开始
        Integer page = searchRequest.getPage() - 1;
        Integer size = searchRequest.getSize();
        queryBuilder.withPageable(PageRequest.of(page, size));

        // 排序
        String sortBy = searchRequest.getSortBy();
        Boolean desc = searchRequest.getDescending();
        if (StringUtils.isNotBlank(sortBy)) {
            queryBuilder.withSort(SortBuilders.fieldSort(sortBy).order(desc ? SortOrder.DESC : SortOrder.ASC));
        }

        // 品牌聚合查询
        String brandAggsName = "brands";
        String categoryAggsName = "categories";
        queryBuilder.addAggregation(AggregationBuilders.terms(brandAggsName).field("brandId"));
        queryBuilder.addAggregation(AggregationBuilders.terms(categoryAggsName).field("cid3"));

        // 查询结果
        AggregatedPage<Goods> goodsResult = (AggregatedPage<Goods>) goodsDao.search(queryBuilder.build());

        // 聚合结果
        List<Brand> brands = getBrandAgg(brandAggsName, goodsResult);
        List<Category> categories = getCategoryAgg(categoryAggsName, goodsResult);

        // 统计规格参数
        /**
         - 当分类结果为1行，统计规格参数
         - 根据分类查询当前分类用于搜索的的规格参数
         - 创建NativeQueryBuilder,使用一样的查询条件key等。。。
         - 把可搜索的规格参数，依次添加到query中的聚合
         - 处理规格参数搜索的结果，k：搜索的参数名,options：聚合结果的数组
         */
        List<Map<String, Object>> specs = null;
        if (categories.size() == 1) {
            specs = getSpecs(categories.get(0).getId(), boolQueryBuilder);
        }


        // 分页数据
        // 总行数
        long total = goodsResult.getTotalElements();
        // 总页数
        long totalPages = goodsResult.getTotalPages();

        return new SearchResult(total, totalPages, goodsResult.getContent(), categories, brands, specs);
    }

    /**
     * 规格参数统计
     *
     * @param cid
     * @param query
     * @return
     */
    private List<Map<String, Object>> getSpecs(Long cid, QueryBuilder query) {
        List<Map<String, Object>> specs = new ArrayList<>();

        SpecParam sp = new SpecParam();
        sp.setCid(cid);
        sp.setSearching(true);
        List<SpecParam> specParams = this.specParamClient.selectSpecParamApi(sp);

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // 在做聚合之前先做查询，只有符合条件的规格参数才应该被查出来
        queryBuilder.withQuery(query);

        for (SpecParam specParam : specParams) {
            // 操作系统，cpu核数
            String name = specParam.getName();
            queryBuilder.addAggregation(AggregationBuilders.terms(name).field("specs." + name + ".keyword"));
        }

        AggregatedPage<Goods> aggs = (AggregatedPage<Goods>) this.goodsDao.search(queryBuilder.build());
        Map<String, Aggregation> stringAggregationMap = aggs.getAggregations().asMap();

        for (SpecParam specParam : specParams) {
            Map<String, Object> spec = new HashMap<>();
            String name = specParam.getName();
            if (stringAggregationMap.get(name) instanceof StringTerms) {
                StringTerms stringTerms = (StringTerms) stringAggregationMap.get(name);
                List<String> val = new ArrayList<>();
                for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
                    val.add(bucket.getKeyAsString());
                }
                // 内存，存储空间，屏幕尺寸
                spec.put("k", name);
                spec.put("options", val);

                specs.add(spec);
            }
        }
        return specs;
    }


    /**
     * 搜索过滤条件
     *
     * @param searchRequest
     * @return
     */
    private BoolQueryBuilder buildBasicQueryWithFilter(SearchRequest searchRequest) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("all", searchRequest.getKey()));

        // 过滤条件
        BoolQueryBuilder filterQueryBuilder = QueryBuilders.boolQuery();
        for (Map.Entry<String, String> entry : searchRequest.getFilter().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            // 商品分类和品牌不需要加.keywords，和"spec."前缀,规格参数要加
            if (!"cid3".equals(key) && !"brandId".equals(key)) {
                key = "specs." + key + ".keyword";
            }
            // 使用termQuery进行过滤
            filterQueryBuilder.must(QueryBuilders.termQuery(key, value));

        }

        return boolQueryBuilder.filter(filterQueryBuilder);
    }

    /**
     * 品牌聚合数据处理
     *
     * @param brandAggsName
     * @param goodsResult
     * @return
     */
    private List<Brand> getBrandAgg(String brandAggsName, AggregatedPage<Goods> goodsResult) {
        LongTerms longTerms = (LongTerms) goodsResult.getAggregation(brandAggsName);
        List<Long> brandIds = new ArrayList<>();
        for (LongTerms.Bucket bucket : longTerms.getBuckets()) {
            brandIds.add(bucket.getKeyAsNumber().longValue());
        }
        return brandClient.selectBrandByIds(brandIds);
    }

    /**
     * 分类聚合数据的处理
     *
     * @param categoryAggsName
     * @param goodsResult
     * @return
     */
    private List<Category> getCategoryAgg(String categoryAggsName, AggregatedPage<Goods> goodsResult) {
        LongTerms longTerms = (LongTerms) goodsResult.getAggregation(categoryAggsName);
        List<Long> categoryIds = new ArrayList<>();
        for (LongTerms.Bucket bucket : longTerms.getBuckets()) {
            categoryIds.add(bucket.getKeyAsNumber().longValue());
        }
        List<String> names = this.categoryClient.queryNamesByIds(categoryIds);

        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            Category category = new Category();
            category.setId(categoryIds.get(i));
            category.setTitle(names.get(i));
            categories.add(category);
        }
        return categories;
    }
}
