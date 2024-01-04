package com.krest.version.provider.service;

import com.krest.api.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("provider");
        return "hello1 " + name;
    }
}
