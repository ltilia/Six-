package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.Map;

public class dg {
    private static final dg a;
    private final Object b;
    private final Map c;

    static {
        a = new dg();
    }

    private dg() {
        this.c = new HashMap(2);
        this.b = new Object();
    }

    static dg a() {
        return a;
    }

    di a(String str) {
        di diVar;
        synchronized (this.b) {
            diVar = (di) this.c.remove(str);
        }
        return diVar;
    }

    void a(String str, long j, String str2, boolean z) {
        di diVar = new di(str2, j, z, null);
        synchronized (this.b) {
            this.c.put(str, diVar);
        }
    }
}
