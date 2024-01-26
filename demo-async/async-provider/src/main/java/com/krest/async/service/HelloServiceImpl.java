package com.krest.async.service;

import com.krest.aysnc.api.HelloService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;
import java.util.concurrent.CompletableFuture;

@DubboService
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "sync:" + name;
    }

    @Override
    public CompletableFuture<String> asyncHello(String name) {
        RpcContext savedContext = RpcContext.getContext();
        // 建议为 supplyAsync 提供自定义线程池，避免使用JDK公用线程池
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(savedContext.getAttachment("userKey"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "async:" + name;
        });
    }

    @Override
    public String asyncMethod1(String name) {
        System.out.println(name);
        return "async method:" + name;
    }

    @Override
    public String asyncMethod2(String name) {
        final AsyncContext asyncContext = RpcContext.startAsync();
        new Thread(() -> {
            // 如果要使用上下文，则必须要放在第一句执行
            asyncContext.signalContextSwitch();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 写回响应
            asyncContext.write("Hello " + name + ", response from provider.");
        }).start();
        return null;
    }
}
