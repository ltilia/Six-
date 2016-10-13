package com.applovin.impl.sdk;

class f implements Runnable {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void run() {
        if (!this.a.a.isForegroundClickInvalidated()) {
            this.a.e.a(this.a.b, this.a.c, this.a.d, this.a.a);
        }
    }
}
