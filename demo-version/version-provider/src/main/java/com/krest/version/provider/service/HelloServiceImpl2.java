package com.krest.version.provider.service;

import com.krest.version.api.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "group2", version = "2.0")
public class HelloServiceImpl2 implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello2 " + name;
    }
}
