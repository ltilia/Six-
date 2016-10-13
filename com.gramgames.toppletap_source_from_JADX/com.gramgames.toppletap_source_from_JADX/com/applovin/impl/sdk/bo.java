package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

class bo implements AppLovinNativeAdLoadListener {
    final /* synthetic */ AppLovinNativeAdLoadListener a;
    final /* synthetic */ bi b;

    bo(bi biVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.b = biVar;
        this.a = appLovinNativeAdLoadListener;
    }

    public void onNativeAdsFailedToLoad(int i) {
        if (this.a != null) {
            this.a.onNativeAdsFailedToLoad(i);
        }
    }

    public void onNativeAdsLoaded(List list) {
        if (this.a != null) {
            this.a.onNativeAdsLoaded(list);
        }
    }
}
