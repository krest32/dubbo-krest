package com.krest.consumer.filter;


import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.stereotype.Component;

@Activate
public class TestFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("before");
        Result result = invoker.invoke(invocation);
        System.out.println("after");
        return result;
    }
}