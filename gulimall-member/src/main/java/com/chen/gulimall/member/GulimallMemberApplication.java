package com.chen.gulimall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 1. 引入open-feign依赖
 * 2. 编写接口调用，成为注册者
 * 3. 消费者去调用接口前，需要写一个类调用
 * 4. 在main类下加注解
 */
@EnableFeignClients(basePackages = "com.chen.gulimall.member.feign")
@SpringBootApplication
@EnableDiscoveryClient
public class GulimallMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(GulimallMemberApplication.class, args);
	}

}
