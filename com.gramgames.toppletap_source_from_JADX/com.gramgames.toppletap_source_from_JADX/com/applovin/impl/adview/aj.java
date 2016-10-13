package com.applovin.impl.adview;

import android.app.Activity;

class aj implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ boolean b;
    final /* synthetic */ Activity c;
    final /* synthetic */ ah d;

    aj(ah ahVar, boolean z, boolean z2, Activity activity) {
        this.d = ahVar;
        this.a = z;
        this.b = z2;
        this.c = activity;
    }

    public void run() {
        if (this.a && this.b) {
            this.d.b(this.c);
        } else {
            this.d.a(this.c);
        }
    }
}
