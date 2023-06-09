package com.chen.gulimall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 一、如何用nacos配置中心
 * 1. 引入依赖
 * 2. 创建bootstrap.properties nacos
 * 3.配置application.properties  key-value
 * 4. @ResfreshScope 动态刷新  @Value
 *
 * 配置中心的配置优先级更高
 *
 *
 * 二、细节
 * 1. 命名空间, 环境pro test
 * spring.cloud.nacos.config.namespace=315d2332-2d72-4615-952e-922ae5ea9d82
 * 每个微服务创建一个命名空间
 *
 * 2. 配置集
 *
 * 3.配置集ID（Data ID）类比文件名
 *
 * 4. 配置分组 Group： 类比目录
 * 	  默认所有的配置都在  DEFAULT_GROUP
 *
 * 	  每个微服务一个命名空间，每个group为单独的环境
 *
 * 	3. 同时加载多个配置集
 * 	1) 任何配置文件都可以单独配置
 * 	2) 只需要在application.properties 说明
 * 	3) @Value、@ConfigurationProperties 优先使用配置中心
 *
 * 网关：
 *
 * 断言 -》route -》 filter -》 service
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GulimallCouponApplication {

	public static void main(String[] args) {
		SpringApplication.run(GulimallCouponApplication.class, args);
	}
}
