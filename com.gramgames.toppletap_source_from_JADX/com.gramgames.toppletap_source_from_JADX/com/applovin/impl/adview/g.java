package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class g implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;
    private final AppLovinAd b;

    public g(AdViewControllerImpl adViewControllerImpl, AppLovinAd appLovinAd) {
        this.a = adViewControllerImpl;
        this.b = appLovinAd;
    }

    public void run() {
        AppLovinAdClickListener i = this.a.y;
        if (i != null && this.b != null) {
            try {
                i.adClicked(this.b);
            } catch (Throwable th) {
                this.a.d.userError("AppLovinAdView", "Exception while notifying ad click listener", th);
            }
        }
    }
}
