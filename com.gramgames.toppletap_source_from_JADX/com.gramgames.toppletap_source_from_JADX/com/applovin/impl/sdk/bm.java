package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

class bm implements AppLovinNativeAdLoadListener {
    final /* synthetic */ List a;
    final /* synthetic */ AppLovinNativeAdLoadListener b;
    final /* synthetic */ List c;
    final /* synthetic */ bi d;

    bm(bi biVar, List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, List list2) {
        this.d = biVar;
        this.a = list;
        this.b = appLovinNativeAdLoadListener;
        this.c = list2;
    }

    public void onNativeAdsFailedToLoad(int i) {
        if (this.b != null) {
            this.b.onNativeAdsFailedToLoad(i);
        }
    }

    public void onNativeAdsLoaded(List list) {
        this.d.c(this.a, new bn(this));
    }
}
