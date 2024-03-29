package com.krest.generic.service;

import com.krest.generic.api.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "generic-provider", version = "1.1")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
