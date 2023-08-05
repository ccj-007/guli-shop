package com.chen.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 1. 整和mybatis-plus 依赖
 * 2. 配置数据源 application.yml
 * 3. 使用mapperscan找到对应路径的dao
 *
 * 一、逻辑删除
 * 1. 配置全局的逻辑删除规则、 Bean
 * 2. 加上逻辑删除的注解
 */
@EnableTransactionManagement
@EnableFeignClients(basePackages = "com.chen.gulimall.product.feign")
@EnableDiscoveryClient
@MapperScan("com.chen.gulimall.product.dao")
@SpringBootApplication
public class GulimailProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailProductApplication.class, args);
    }
}