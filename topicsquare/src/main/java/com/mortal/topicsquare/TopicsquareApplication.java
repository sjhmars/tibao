package com.mortal.topicsquare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.mortal.topicsquate.mapper"})
public class TopicsquareApplication {
    public static void main(String[] args) {
        SpringApplication.run(TopicsquareApplication.class, args);
    }

}
