package com.krest.notify.controller;

import com.krest.notify.IDemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class DemoController {
    // 绑定接口的三种事件
    @DubboReference(
            methods = @Method(
                    name = "sayHello",
                    onreturn = "notify.onReturn",
                    onthrow = "notify.onThrow",
                    oninvoke = "notify.onInvoke"
            )
    )
    IDemoService demoService;

    @Autowired
    NotifyImpl notify;

    @GetMapping("hello")
    public String sayHello(@RequestParam Integer id) throws InterruptedException {

        String result = demoService.sayHello(id);
        System.out.println("result: " + notify.ret.get(id));
        return result;
    }
}
