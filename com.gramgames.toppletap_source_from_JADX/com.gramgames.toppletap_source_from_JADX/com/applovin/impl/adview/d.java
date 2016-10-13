package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class d implements AppLovinAdDisplayListener {
    final /* synthetic */ AdViewControllerImpl a;

    d(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
    }

    public void adHidden(AppLovinAd appLovinAd) {
        if (this.a.w != null) {
            this.a.w.adHidden(appLovinAd);
        }
    }
}
