package com.chartboost.sdk.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class aw {
    private static ExecutorService a;
    private static ThreadFactory b;

    static class 1 implements ThreadFactory {
        private final AtomicInteger a;

        1() {
            this.a = new AtomicInteger(1);
        }

        public Thread newThread(Runnable r) {
            return new Thread(r, "Chartboost Thread #" + this.a.getAndIncrement());
        }
    }

    static {
        a = null;
        b = null;
    }

    public static ExecutorService a() {
        if (b == null) {
            b = new 1();
        }
        if (a == null) {
            a = Executors.newFixedThreadPool(2, b);
        }
        return a;
    }
}
