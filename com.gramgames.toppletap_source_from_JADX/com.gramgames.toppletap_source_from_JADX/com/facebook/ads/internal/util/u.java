package com.facebook.ads.internal.util;

import java.lang.ref.WeakReference;

public abstract class u<T> implements Runnable {
    private final WeakReference<T> a;

    public u(T t) {
        this.a = new WeakReference(t);
    }

    public T a() {
        return this.a.get();
    }
}
