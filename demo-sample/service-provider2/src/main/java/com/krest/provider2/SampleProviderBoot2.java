package com.krest.provider2;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class SampleProviderBoot2 {
    public static void main(String[] args) {
        SpringApplication.run(SampleProviderBoot2.class, args);
    }
}
