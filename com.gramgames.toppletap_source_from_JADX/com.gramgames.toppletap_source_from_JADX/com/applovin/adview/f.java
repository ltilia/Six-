package com.applovin.adview;

import com.applovin.impl.adview.u;

class f implements Runnable {
    final /* synthetic */ u a;
    final /* synthetic */ AppLovinInterstitialActivity b;

    f(AppLovinInterstitialActivity appLovinInterstitialActivity, u uVar) {
        this.b = appLovinInterstitialActivity;
        this.a = uVar;
    }

    public void run() {
        if (this.a.equals(this.b.y)) {
            this.b.l();
        } else if (this.a.equals(this.b.A)) {
            this.b.n();
        }
    }
}
