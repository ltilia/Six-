package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

class ai implements AppLovinAdLoadListener {
    final /* synthetic */ String a;
    final /* synthetic */ ah b;

    ai(ah ahVar, String str) {
        this.b = ahVar;
        this.a = str;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        this.b.b(appLovinAd);
        this.b.showAndRender(appLovinAd, this.a);
    }

    public void failedToReceiveAd(int i) {
        this.b.a(i);
    }
}
