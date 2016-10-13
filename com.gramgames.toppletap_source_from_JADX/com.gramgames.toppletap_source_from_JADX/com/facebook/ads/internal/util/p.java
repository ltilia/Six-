package com.facebook.ads.internal.util;

import android.support.annotation.NonNull;
import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class p implements ThreadFactory {
    protected final AtomicLong a;
    private int b;

    public p() {
        this.a = new AtomicLong();
        this.b = Thread.currentThread().getPriority();
    }

    protected String a() {
        return String.format(Locale.ENGLISH, "com.facebook.ads thread-%d %tF %<tT", new Object[]{Long.valueOf(this.a.incrementAndGet()), Long.valueOf(System.currentTimeMillis())});
    }

    public Thread newThread(@NonNull Runnable runnable) {
        Thread thread = new Thread(null, runnable, a(), 0);
        thread.setPriority(this.b);
        return thread;
    }
}
