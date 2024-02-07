package com.krest.notify;

public interface Notify {
    void onReturn(String name, int id);

    void onThrow(Throwable ex, int id);

    void onInvoke(int id);
}
