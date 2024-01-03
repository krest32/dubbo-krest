package com.krest.consumer;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class ConsumerBoot {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerBoot.class, args);
    }
}
