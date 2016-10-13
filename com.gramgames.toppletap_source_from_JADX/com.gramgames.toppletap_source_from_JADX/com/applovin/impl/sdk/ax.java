package com.applovin.impl.sdk;

class ax implements Runnable {
    final /* synthetic */ aw a;

    ax(aw awVar) {
        this.a = awVar;
    }

    public void run() {
        if (this.a.c != null) {
            this.a.c.dismiss();
        }
    }
}
