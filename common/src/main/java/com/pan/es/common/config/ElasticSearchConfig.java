package com.pan.es.common.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2022-10-08 16:46
 **/
@Configuration
public class ElasticSearchConfig {
    @Bean
    public RestHighLevelClient getRestHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.79.130:9200")
        ));
    }
}
