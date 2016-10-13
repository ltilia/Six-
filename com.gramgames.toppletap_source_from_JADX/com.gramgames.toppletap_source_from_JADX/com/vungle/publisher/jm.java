package com.vungle.publisher;

import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: vungle */
public final class jm {
    private final AtomicInteger a;

    public jm() {
        this.a = new AtomicInteger();
    }

    public final int a(int i) {
        int i2;
        int i3;
        do {
            i3 = this.a.get();
            if (i < 0 || i > 31) {
                throw new IllegalArgumentException("bit index must be 0-31");
            }
            i2 = (1 << i) | i3;
        } while (!this.a.compareAndSet(i3, i2));
        return i2;
    }
}
