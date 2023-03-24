package com.mortal.searchquestions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SearchquestionsApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SearchquestionsApplication.class);
        springApplication.setAllowCircularReferences(Boolean.TRUE);
        springApplication.run(args);
        System.out.println("----------------------  搜索模块 启动成功 -------------------------");
    }

}
