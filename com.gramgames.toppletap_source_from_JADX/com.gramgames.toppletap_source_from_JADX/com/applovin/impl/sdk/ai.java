package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class ai implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ ae b;

    ai(ae aeVar, AppLovinAd appLovinAd) {
        this.b = aeVar;
        this.a = appLovinAd;
    }

    public void run() {
        this.b.e.videoPlaybackBegan(this.a);
    }
}
