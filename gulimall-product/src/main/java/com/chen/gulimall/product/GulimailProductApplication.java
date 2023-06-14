package com.chen.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1. 整和mybatis-plus 依赖
 * 2. 配置数据源 application.yml
 * 3. 使用mapperscan找到对应路径的dao
 */
@EnableDiscoveryClient
@MapperScan("com.chen.gulimall.product.dao")
@SpringBootApplication
public class GulimailProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailProductApplication.class, args);
    }
}