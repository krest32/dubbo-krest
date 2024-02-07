package com.krest.notify;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class NotifyProviderBoot {
    public static void main(String[] args) {
        SpringApplication.run(NotifyProviderBoot.class, args);
    }
}
