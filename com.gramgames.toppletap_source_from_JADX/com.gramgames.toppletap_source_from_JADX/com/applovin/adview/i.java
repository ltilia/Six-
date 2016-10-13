package com.applovin.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class i implements AppLovinAdClickListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    i(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void adClicked(AppLovinAd appLovinAd) {
        AppLovinAdClickListener e = this.a.b.e();
        if (e != null) {
            e.adClicked(appLovinAd);
        }
    }
}
