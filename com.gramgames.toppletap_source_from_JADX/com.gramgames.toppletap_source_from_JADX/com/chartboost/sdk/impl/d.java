package com.chartboost.sdk.impl;

import com.mopub.volley.DefaultRetryPolicy;

public class d implements p {
    private int a;
    private int b;
    private final int c;
    private final float d;

    public d() {
        this(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public d(int i, int i2, float f) {
        this.a = i;
        this.c = i2;
        this.d = f;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public void a(s sVar) throws s {
        this.b++;
        this.a = (int) (((float) this.a) + (((float) this.a) * this.d));
        if (!c()) {
            throw sVar;
        }
    }

    protected boolean c() {
        return this.b <= this.c;
    }
}
