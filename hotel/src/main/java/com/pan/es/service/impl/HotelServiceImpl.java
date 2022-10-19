package com.pan.es.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pan.es.model.request.PageRequest;
import com.pan.es.model.vo.HotelVo;
import com.pan.es.model.entity.Hotel;
import com.pan.es.model.vo.PageVo;
import com.pan.es.service.HotelService;
import com.pan.es.mapper.HotelMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Mr.Pan
 * @description 针对表【tb_hotel】的数据库操作Service实现
 * @createDate 2022-10-08 11:41:42
 */
@Service
public class HotelServiceImpl
        extends ServiceImpl<HotelMapper, Hotel>
        implements HotelService {

    @Resource
    private RestHighLevelClient client;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public PageVo<HotelVo> getHotelPage(PageRequest pageRequest) throws IOException {
        SearchRequest request = new SearchRequest("hotel");

        /*布尔查询*/
        BoolQueryBuilder boolQueryBuilder = getBoolQueryBuilder(pageRequest);
        request.source().query(boolQueryBuilder);

        /*搜索结果处理*/
        if (!StringUtils.isEmpty(pageRequest.getSortBy())) {
            if (!StringUtils.isEmpty(pageRequest.getLocation())
                    && "location".equals(pageRequest.getSortBy())) {

                /*如果sortBy字段是location, 则根据地理位置排序*/
                request.source().sort(
                        SortBuilders.geoDistanceSort(
                                        pageRequest.getSortBy(),
                                        new GeoPoint(pageRequest.getLocation())
                                )
                                .order(SortOrder.ASC)
                                .unit(DistanceUnit.KILOMETERS)
                );

            } else {
                /*如果sortBy不是地理位置, 则直接进行普通字段排序*/
                request.source().sort(pageRequest.getSortBy(), SortOrder.ASC);
            }

        }

        /*分页查询*/
        request.source()
                .from((pageRequest.getPagenum() - 1) * pageRequest.getPagesize())
                .size(pageRequest.getPagesize());

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        PageVo<HotelVo> hotelPage = handleResponse(response);
        return hotelPage;
    }

    private BoolQueryBuilder getBoolQueryBuilder(PageRequest pageRequest) {
        /*布尔查询*/
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        /*关键字搜索*/
        if (StringUtils.isEmpty(pageRequest.getKeyword())) {
            /*如果没传keyword, 则查询所有*/
            boolQueryBuilder.must(QueryBuilders.matchAllQuery());
        } else {
            /*如果传了keyword, 则查询keyword*/
            boolQueryBuilder.must(QueryBuilders.matchQuery("all", pageRequest.getKeyword()));
        }

        /*条件过滤*/
        String[] cityList = pageRequest.getCity();
        if (!StringUtils.isEmpty(cityList)) {
            /*一个字段要查对应多个值用termsQuery*/
            boolQueryBuilder.filter(QueryBuilders.termsQuery("city", cityList));
        }

        String[] brandList = pageRequest.getBrand();
        if (!StringUtils.isEmpty(brandList)) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("brand", brandList));
        }

        String[] starNameList = pageRequest.getStarName();
        if (!StringUtils.isEmpty(starNameList)) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("starName", starNameList));
        }

        if (!ObjectUtils.isEmpty(pageRequest.getMinPrice())
                && !ObjectUtils.isEmpty(pageRequest.getMaxPrice())) {
            /*如果MinPrice和MaxPrice都不为空, 则筛选价格
             * 因为所有的城市都必须在价格范围内, 所以不用算分, 使用filter*/
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price")
                    .gte(pageRequest.getMinPrice())
                    .lte(pageRequest.getMaxPrice()));
        } else if (!ObjectUtils.isEmpty(pageRequest.getMaxPrice())) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price")
                    .lte(pageRequest.getMaxPrice()));
        } else if (!ObjectUtils.isEmpty(pageRequest.getMinPrice())) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price")
                    .gte(pageRequest.getMinPrice()));
        }

        if (!ObjectUtils.isEmpty(pageRequest.getDistance())) {
            /*如果距离不为空, 则查询离目标点, 在某距离范围内的酒店*/
            boolQueryBuilder.must(
                    QueryBuilders.geoDistanceQuery("location")
                            .point(new GeoPoint(pageRequest.getLocation()))
                            .distance(pageRequest.getDistance(), DistanceUnit.KILOMETERS)
            );
        }

        return boolQueryBuilder;
    }

    /*处理查询结果*/
    private PageVo<HotelVo> handleResponse(SearchResponse response) throws JsonProcessingException {
        SearchHits searchHits = response.getHits();
        /*总记录数*/
        long total = searchHits.getTotalHits().value;

        SearchHit[] hits = searchHits.getHits();
        List<HotelVo> hotelList = new ArrayList<>();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            HotelVo hotelVo = objectMapper.readValue(json, HotelVo.class);
            hotelList.add(hotelVo);

            /*这里的sortValues就是排序值, 如果按照距离优先的话, 那么sortValues第一个值就是距离目标点的距离*/
            Object[] distanceVal = hit.getSortValues();
            System.out.println(Arrays.toString(distanceVal));
        }

        return new PageVo<HotelVo>((int) total, hotelList);
    }
}




