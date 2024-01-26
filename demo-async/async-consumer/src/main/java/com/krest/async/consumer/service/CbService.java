package com.krest.async.consumer.service;

import org.springframework.stereotype.Component;

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
