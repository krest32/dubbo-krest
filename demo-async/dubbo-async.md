# 简单案例

## 方式一：

**provider**

~~~java
@Override
public CompletableFuture<String> asyncHello(String name) {
    return CompletableFuture.supplyAsync(() -> {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "async:" + name;
    });
}

~~~

**consumer**

~~~java
CompletableFuture<String> future = service.asyncHello(name);
        
// 增加回调
future.whenComplete((v, t) -> {
    if (t != null) {
        t.printStackTrace();
    } else {
        System.out.println("Response: " + v);
        res.set(v);
    }
});
~~~







## 方式二

**注解配置**

1. sent="true" 等待消息发出，消息发送失败将抛出异常。

2. sent="false" 不等待消息发出，将消息放入IO队列，即刻返回。

~~~java
@DubboReference(
    methods = {
        @Method(
            name = "asyncMethod1", 
            sent = true,
            async = true, 
            isReturn = true, 
            onreturn = "cbService.onreturn", 
            onthrow = "cbService.onthrow")
    }
)
HelloService helloService;
~~~



**callback方法**

~~~java
@Component
public class CbService {
    // onreturn 函数的参数是有限定的，细节下面提及。
    public void onreturn(String str) {
        System.out.println("onreturn:" + str);
    }
    public void onthrow(Throwable ex) {
        System.out.println("onthrow");
    }
    public String oninvoke() {
        System.out.println("oninvoke");
        return null;
    }
}
~~~



## 原理

**判断是否单向传输**

~~~java
String RETURN_KEY = "return";

public static boolean isOneway(URL url, Invocation inv) {
    boolean isOneway;
    if (Boolean.FALSE.toString().equals(inv.getAttachment(RETURN_KEY))) {
        isOneway = true;
    } else {
        isOneway = !url.getMethodParameter(getMethodName(inv), RETURN_KEY, true);
    }
    return isOneway;
}
~~~





**根据传输方式，确定是否返回结果**

DubboInvoker #doInvoke

~~~java
if (isOneway) {
    boolean isSent = getUrl().getMethodParameter(methodName, Constants.SENT_KEY, false);
    request.setTwoWay(false);
    currentClient.send(request, isSent);
    return AsyncRpcResult.newDefaultAsyncResult(invocation);
} else {
    // 异步处理
    request.setTwoWay(true);
    ExecutorService executor = getCallbackExecutor(getUrl(), inv);
    CompletableFuture<AppResponse> appResponseFuture =
        currentClient.request(request, timeout, executor).thenApply(AppResponse.class::cast);
    FutureContext.getContext().setCompatibleFuture(appResponseFuture);
    AsyncRpcResult result = new AsyncRpcResult(appResponseFuture, inv);
    result.setExecutor(executor);
    return result;
}
~~~



### 单向

~~~java
public static AsyncRpcResult newDefaultAsyncResult(Invocation invocation) {
    return newDefaultAsyncResult(null, null, invocation);
}

public static AsyncRpcResult newDefaultAsyncResult(Object value, Throwable t, Invocation invocation) {
	CompletableFuture<AppResponse> future = new CompletableFuture<>();
    AppResponse result = new AppResponse(invocation);
    if (t != null) {
        result.setException(t);
    } else {
        result.setValue(value);
    }
    future.complete(result);
    return new AsyncRpcResult(future, invocation);
}
~~~

### 双向

~~~java
@Override
public CompletableFuture<Object> request(Object request, int timeout, ExecutorService executor)
    throws RemotingException {
    if (closed) {
        throw new RemotingException(
            this.getLocalAddress(),
            null,
            "Failed to send request " + request + ", cause: The channel " + this + " is closed!");
    }
    Request req;
    if (request instanceof Request) {
        req = (Request) request;
    } else {
        // create request.
        req = new Request();
        req.setVersion(Version.getProtocolVersion());
        req.setTwoWay(true);
        req.setData(request);
    }
    DefaultFuture future = DefaultFuture.newFuture(channel, req, timeout, executor);
    try {
        channel.send(req);
    } catch (RemotingException e) {
        future.cancel();
        throw e;
    }
    return future;
}
~~~



NettyChannel # send

~~~java
@Override
public void send(Object message, boolean sent) throws RemotingException {
    super.send(message, sent);

    boolean success = true;
    int timeout = 0;
    try {
        ChannelFuture future = channel.write(message);
        if (sent) {
            timeout = getUrl().getPositiveParameter(TIMEOUT_KEY, DEFAULT_TIMEOUT);
            success = future.await(timeout);
        }
        Throwable cause = future.getCause();
        if (cause != null) {
            throw cause;
        }
    } catch (Throwable e) {
        throw new RemotingException(
            this,
            "Failed to send message " + PayloadDropper.getRequestWithoutData(message) + " to "
            + getRemoteAddress() + ", cause: " + e.getMessage(),
            e);
    }

    if (!success) {
        throw new RemotingException(
            this,
            "Failed to send message " + PayloadDropper.getRequestWithoutData(message) + " to "
            + getRemoteAddress() + "in timeout(" + timeout + "ms) limit");
    }
}
~~~



## triple

关于 tripple协议兼容http2.0和grpc，更适用于云原生；可以跨语言、跨端跨平台进行调用；可以流式处理数据，适用于直播等大数据包场景；可以使用protobuf进行编解码；

proto作为媒介，定义接口和参数。
