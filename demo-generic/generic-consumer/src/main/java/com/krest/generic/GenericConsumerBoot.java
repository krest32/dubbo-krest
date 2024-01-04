package com.krest.generic;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class GenericConsumerBoot {
    public static void main(String[] args) {
        SpringApplication.run(GenericConsumerBoot.class, args);
    }
}
