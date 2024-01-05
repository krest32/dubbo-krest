package com.krest.notify.service;

import com.krest.notify.IDemoService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class DemoServiceImpl implements IDemoService {

    @Override
    public String sayHello(Integer id) {
        if (id > 10) {
            throw new RuntimeException("exception from sayHello: too large id");
        }
        return "demo" + id;
    }

}
