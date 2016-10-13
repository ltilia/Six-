package com.vungle.publisher;

import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: vungle */
public final class jg {
    public static boolean a(AtomicInteger atomicInteger) {
        if (atomicInteger.compareAndSet(0, 1)) {
            return true;
        }
        int i;
        do {
            i = atomicInteger.get();
        } while (!atomicInteger.compareAndSet(i, i + 1));
        return false;
    }
}
