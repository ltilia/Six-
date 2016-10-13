package com.applovin.impl.adview;

class al implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ ah b;

    al(ah ahVar, int i) {
        this.b = ahVar;
        this.a = i;
    }

    public void run() {
        if (this.b.g != null) {
            this.b.g.failedToReceiveAd(this.a);
        }
    }
}
