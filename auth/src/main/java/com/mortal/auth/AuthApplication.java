package com.mortal.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = {"com.mortal.auth.mapper"})
//@ComponentScan("com.mortal.auth.mapper")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AuthApplication.class);
        springApplication.setAllowCircularReferences(Boolean.TRUE);
        springApplication.run(args);
        System.out.println("----------------------  认证授权中心 启动成功 -------------------------");
    }
}
