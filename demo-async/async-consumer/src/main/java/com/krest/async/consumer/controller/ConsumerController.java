package com.krest.async.consumer.controller;

import com.krest.aysnc.api.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

@RequestMapping
@RestController
public class ConsumerController {
    @DubboReference(
//            methods = {@Method(name = "asyncMethod1", async = true, isReturn = true,  onthrow = "cbService.onthrow")}
            methods = {@Method(name = "asyncMethod1", async = true, isReturn = true, onreturn = "cbService.onreturn", onthrow = "cbService.onthrow")}
    )
    HelloService helloService;


    @GetMapping("hello")
    public String get(@RequestParam String name) {
        return helloService.sayHello(name);
    }

    @GetMapping("async")
    public String async(@RequestParam String name) {
        RpcContext.getContext().setAttachment("userKey", "userValue");
        // 调用直接返回CompletableFuture
        CompletableFuture<String> future = helloService.asyncHello(name);
        AtomicReference<String> res = new AtomicReference<>();
        // 增加回调
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("Response: " + v);
                res.set(v);
            }
        });
        // 早于结果输出
        System.out.println("Executed before response return.");
        // 就是不能够马上得到结果
        return res.get();
    }

    @GetMapping("asyncMethod1")
    public String asyncMethod1(@RequestParam String name) {
        String a = helloService.asyncMethod1(name);
        // 结果不会返回
        System.out.println(a);
        return "ok";
    }

    @GetMapping("asyncMethod2")
    public void asyncMethod2(){
        //consumer异步调用
        CompletableFuture<String> future3 =  CompletableFuture.supplyAsync(() -> {
            return helloService.asyncMethod2("invoke call request3");
        });
        future3.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("AsyncTask Response-3: " + v);
            }
        });

        System.out.println("AsyncTask Executed before response return.");
    }

}
