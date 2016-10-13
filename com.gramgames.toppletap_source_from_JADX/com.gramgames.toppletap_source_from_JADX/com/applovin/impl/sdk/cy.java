package com.applovin.impl.sdk;

import java.lang.Thread.UncaughtExceptionHandler;

class cy implements UncaughtExceptionHandler {
    final /* synthetic */ cx a;

    cy(cx cxVar) {
        this.a = cxVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        this.a.a.b.e("TaskManager", "Caught unhandled exception", th);
    }
}
