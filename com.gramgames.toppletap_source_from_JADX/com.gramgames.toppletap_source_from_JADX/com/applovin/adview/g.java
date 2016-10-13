package com.applovin.adview;

import java.util.UUID;

class g implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ UUID b;
    final /* synthetic */ AppLovinInterstitialActivity c;

    g(AppLovinInterstitialActivity appLovinInterstitialActivity, int i, UUID uuid) {
        this.c = appLovinInterstitialActivity;
        this.a = i;
        this.b = uuid;
    }

    public void run() {
        this.c.a(this.a, this.b);
    }
}
