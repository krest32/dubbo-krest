package com.krest.provider.service;

import com.krest.api.HelloService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@DubboService
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("provider");
        return "hello " + name;
    }
}
