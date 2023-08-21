package com.chen.gulimall.gulimallsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class esConfig {

    public RestHighLevelClient esRestClient() {
        RestHighLevelClient http = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.56.103", 9200, "http")));
        return http;
    }
}
