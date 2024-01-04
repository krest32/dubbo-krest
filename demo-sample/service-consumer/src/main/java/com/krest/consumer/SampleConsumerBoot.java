package com.krest.consumer;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class SampleConsumerBoot {
    public static void main(String[] args) {
        SpringApplication.run(SampleConsumerBoot.class, args);
    }
}
