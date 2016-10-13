package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class ah implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ ae b;

    ah(ae aeVar, AppLovinAd appLovinAd) {
        this.b = aeVar;
        this.a = appLovinAd;
    }

    public void run() {
        this.b.d.adClicked(this.a);
    }
}
