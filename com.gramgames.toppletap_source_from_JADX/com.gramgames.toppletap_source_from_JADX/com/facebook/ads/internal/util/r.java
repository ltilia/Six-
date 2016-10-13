package com.facebook.ads.internal.util;

import android.graphics.Bitmap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class r {
    static final int a;
    static final ExecutorService b;
    private static volatile boolean c;
    private final Bitmap d;
    private Bitmap e;
    private final j f;

    static {
        a = Runtime.getRuntime().availableProcessors();
        b = Executors.newFixedThreadPool(a);
        c = true;
    }

    public r(Bitmap bitmap) {
        this.d = bitmap;
        this.f = new n();
    }

    public Bitmap a() {
        return this.e;
    }

    public Bitmap a(int i) {
        this.e = this.f.a(this.d, (float) i);
        return this.e;
    }
}
