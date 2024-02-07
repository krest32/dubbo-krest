package com.krest.consumer.controller;


import com.krest.api.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @DubboReference
    HelloService helloService;

    @GetMapping("hello")
    public String Hello(@RequestParam String name) {
        String ans = helloService.sayHello(name);
        LOGGER.info("result: {}", ans);
        return helloService.sayHello(name);
    }
}
