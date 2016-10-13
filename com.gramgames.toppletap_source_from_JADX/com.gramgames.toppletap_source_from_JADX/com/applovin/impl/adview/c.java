package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class c implements AppLovinAdClickListener {
    final /* synthetic */ AdViewControllerImpl a;

    c(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    public void adClicked(AppLovinAd appLovinAd) {
        if (this.a.y != null) {
            this.a.y.adClicked(appLovinAd);
        }
    }
}
