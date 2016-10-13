package com.applovin.impl.sdk;

import java.util.LinkedList;
import java.util.Queue;

class bw {
    private final int a;
    private final Queue b;
    private final Object c;

    bw(int i) {
        if (i > 10) {
            i = 10;
        }
        this.a = i;
        this.b = new LinkedList();
        this.c = new Object();
    }

    int a() {
        int size;
        synchronized (this.c) {
            size = this.b.size();
        }
        return size;
    }

    void a(bd bdVar) {
        synchronized (this.c) {
            if (!c()) {
                this.b.offer(bdVar);
            }
        }
    }

    int b() {
        return this.a;
    }

    boolean c() {
        boolean z;
        synchronized (this.c) {
            z = a() >= this.a;
        }
        return z;
    }

    boolean d() {
        boolean z;
        synchronized (this.c) {
            z = a() == 0;
        }
        return z;
    }

    bd e() {
        try {
            bd bdVar;
            synchronized (this.c) {
                bdVar = !d() ? (bd) this.b.poll() : null;
            }
            return bdVar;
        } catch (Exception e) {
            return null;
        }
    }
}
