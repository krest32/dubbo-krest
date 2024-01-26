package com.krest.aysnc.api;

import java.util.concurrent.CompletableFuture;

public interface HelloService {
    String sayHello(String name);

    CompletableFuture<String> asyncHello(String name);

    String asyncMethod1(String name);

    String asyncMethod2(String name);

}
