package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class ag implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ ae b;

    ag(ae aeVar, AppLovinAd appLovinAd) {
        this.b = aeVar;
        this.a = appLovinAd;
    }

    public void run() {
        this.b.c.adHidden(this.a);
    }
}
