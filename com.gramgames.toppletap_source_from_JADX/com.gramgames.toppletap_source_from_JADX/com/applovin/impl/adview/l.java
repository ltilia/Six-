package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

class l implements AppLovinAdLoadListener, AppLovinAdUpdateListener {
    private final WeakReference a;
    private final AppLovinAdService b;
    private final AppLovinLogger c;

    l(AdViewControllerImpl adViewControllerImpl, AppLovinSdk appLovinSdk) {
        if (adViewControllerImpl == null) {
            throw new IllegalArgumentException("No view specified");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            this.a = new WeakReference(adViewControllerImpl);
            this.c = appLovinSdk.getLogger();
            this.b = appLovinSdk.getAdService();
        }
    }

    public void adReceived(AppLovinAd appLovinAd) {
        AdViewControllerImpl adViewControllerImpl = (AdViewControllerImpl) this.a.get();
        if (adViewControllerImpl != null) {
            adViewControllerImpl.a(appLovinAd);
        } else {
            this.c.userError("AppLovinAdView", "Ad view has been garbage collected by the time an ad was recieved");
        }
    }

    public void adUpdated(AppLovinAd appLovinAd) {
        AdViewControllerImpl adViewControllerImpl = (AdViewControllerImpl) this.a.get();
        if (adViewControllerImpl != null) {
            adViewControllerImpl.a(appLovinAd);
            return;
        }
        this.b.removeAdUpdateListener(this, appLovinAd.getSize());
        this.c.userError("AppLovinAdView", "Ad view has been garbage collected by the time an ad was updated");
    }

    public void failedToReceiveAd(int i) {
        AdViewControllerImpl adViewControllerImpl = (AdViewControllerImpl) this.a.get();
        if (adViewControllerImpl != null) {
            adViewControllerImpl.a(i);
        }
    }

    public String toString() {
        return "[AdViewController listener: " + hashCode() + "]";
    }
}
