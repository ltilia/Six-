package com.applovin.impl.sdk;

import java.util.concurrent.ThreadFactory;

class cx implements ThreadFactory {
    final /* synthetic */ cv a;
    private final String b;

    public cx(cv cvVar, String str) {
        this.a = cvVar;
        this.b = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "AppLovinSdk:" + this.b + ":" + dm.a(this.a.a.getSdkKey()));
        thread.setDaemon(true);
        thread.setPriority(10);
        thread.setUncaughtExceptionHandler(new cy(this));
        return thread;
    }
}
