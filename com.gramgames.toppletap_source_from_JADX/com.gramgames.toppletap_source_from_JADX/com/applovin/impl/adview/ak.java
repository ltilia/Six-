package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;

class ak implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ ah b;

    ak(ah ahVar, AppLovinAd appLovinAd) {
        this.b = ahVar;
        this.a = appLovinAd;
    }

    public void run() {
        if (this.b.g != null) {
            this.b.g.adReceived(this.a);
        }
    }
}
