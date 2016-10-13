package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class h implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;
    private final AppLovinAd b;

    public h(AdViewControllerImpl adViewControllerImpl, AppLovinAd appLovinAd) {
        this.a = adViewControllerImpl;
        this.b = appLovinAd;
    }

    public void run() {
        AppLovinAdDisplayListener h = this.a.w;
        if (h != null && this.b != null) {
            try {
                h.adHidden(this.b);
            } catch (Throwable th) {
                this.a.d.userError("AppLovinAdView", "Exception while notifying ad display listener", th);
            }
        }
    }
}
