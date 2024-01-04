package com.krest.version.consumer.controller;

import com.krest.version.api.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class HelloController {
    @DubboReference(group = "group2",version = "1.0")
    HelloService helloService1;

    @DubboReference(group = "group2",version = "2.0")
    HelloService helloService2;
    @GetMapping("hello")
    public String hello(@RequestParam String name) {
        System.out.println(helloService1.sayHello(name));
        System.out.println(helloService2.sayHello(name));
        return "ok";
    }
}
