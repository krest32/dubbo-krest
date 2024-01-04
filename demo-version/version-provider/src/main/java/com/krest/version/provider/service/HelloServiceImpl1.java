package com.krest.version.provider.service;

import com.krest.version.api.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "group2", version = "1.0")
public class HelloServiceImpl1 implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello1 " + name;
    }
}
