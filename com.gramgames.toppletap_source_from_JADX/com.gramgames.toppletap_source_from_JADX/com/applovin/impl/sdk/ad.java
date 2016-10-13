package com.applovin.impl.sdk;

class ad implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ ab b;

    ad(ab abVar, int i) {
        this.b = abVar;
        this.a = i;
    }

    public void run() {
        this.b.b.failedToReceiveAd(this.a);
    }
}
