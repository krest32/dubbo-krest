package com.krest.generic.controller;

import com.alibaba.fastjson2.JSON;
import org.apache.dubbo.common.config.ReferenceCache;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.SimpleReferenceCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloServiceController {
    @GetMapping("hello")
    public String hello(@RequestParam String name) {
        GenericService genericService = buildGenericService("com.krest.generic.api.HelloService", null, null);
        //传入需要调用的方法，参数类型列表，参数列表
        Object result = genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"krest"});
        return JSON.toJSONString(result);
    }

    private GenericService buildGenericService(String interfaceClass, String group, String version) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface(interfaceClass);
        reference.setVersion(version);
        //开启泛化调用
        reference.setGeneric("true");
        reference.setTimeout(30000);
        reference.setGroup(group);
        ReferenceCache cache = SimpleReferenceCache.getCache();
        try {
            return cache.get(reference);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
