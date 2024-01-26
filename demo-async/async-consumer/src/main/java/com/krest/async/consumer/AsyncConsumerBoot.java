package com.krest.async.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class AsyncConsumerBoot {
    public static void main(String[] args) {
        SpringApplication.run(AsyncConsumerBoot.class, args);
    }
}
