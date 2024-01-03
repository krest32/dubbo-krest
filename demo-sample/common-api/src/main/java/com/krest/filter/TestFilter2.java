package com.krest.filter;


import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate(
        group = {"consumer"},
        order = Integer.MIN_VALUE
)
public class TestFilter2 implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("before2");
        Result result = invoker.invoke(invocation);
        System.out.println("after2");
        return result;
    }
}