package com.applovin.adview;

import com.applovin.impl.sdk.AppLovinAdImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class h implements AppLovinAdDisplayListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    h(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        this.a.g = (AppLovinAdImpl) appLovinAd;
        if (!this.a.i) {
            this.a.a(appLovinAd);
        }
    }

    public void adHidden(AppLovinAd appLovinAd) {
        this.a.b(appLovinAd);
    }
}
