package com.krest.version.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class VersionProviderBoot {
    public static void main(String[] args) {
        SpringApplication.run(VersionProviderBoot.class, args);
    }
}
