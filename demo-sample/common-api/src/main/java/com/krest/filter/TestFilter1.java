package com.krest.filter;


import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate(
        group = {"provider"},
        order = Integer.MIN_VALUE
)
public class TestFilter1 implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("before");
        Result result = invoker.invoke(invocation);
        System.out.println("after");
        return result;
    }
}