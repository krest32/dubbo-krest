package com.krest.provider2.service;

import com.krest.api.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class HelloServiceImpl2 implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello2 " + name;
    }
}
