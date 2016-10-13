package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

class bj implements AppLovinNativeAdLoadListener {
    final /* synthetic */ AppLovinNativeAdLoadListener a;
    final /* synthetic */ bi b;

    bj(bi biVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.b = biVar;
        this.a = appLovinNativeAdLoadListener;
    }

    public void onNativeAdsFailedToLoad(int i) {
        this.b.a(this.a, i);
    }

    public void onNativeAdsLoaded(List list) {
        this.b.a(list, this.a);
    }
}
