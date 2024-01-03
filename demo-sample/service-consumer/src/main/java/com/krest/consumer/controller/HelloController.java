package com.krest.consumer.controller;


import com.krest.api.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class HelloController {
    @DubboReference
    HelloService helloService;

    @GetMapping("hello")
    public String Hello(@RequestParam String name) {
        return helloService.sayHello(name);
    }
}
