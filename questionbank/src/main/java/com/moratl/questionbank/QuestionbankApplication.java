package com.moratl.questionbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class QuestionbankApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(QuestionbankApplication.class);
        springApplication.setAllowCircularReferences(Boolean.TRUE);
        springApplication.run(args);
        System.out.println("----------------------  题库模块 启动成功 -------------------------");
    }

}
