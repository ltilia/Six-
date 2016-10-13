package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

class ab implements AppLovinAdLoadListener {
    final /* synthetic */ z a;
    private final AppLovinAdLoadListener b;

    ab(z zVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a = zVar;
        this.b = appLovinAdLoadListener;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        this.a.c = (AppLovinAdImpl) appLovinAd;
        if (this.b != null) {
            this.a.f.post(new ac(this, appLovinAd));
        }
    }

    public void failedToReceiveAd(int i) {
        if (this.b != null) {
            this.a.f.post(new ad(this, i));
        }
    }
}
