package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;

class bk implements AppLovinNativeAdPrecacheListener {
    final /* synthetic */ AppLovinNativeAdPrecacheListener a;
    final /* synthetic */ bi b;

    bk(bi biVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        this.b = biVar;
        this.a = appLovinNativeAdPrecacheListener;
    }

    public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        this.b.a(this.a, appLovinNativeAd, i, false);
    }

    public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
        this.b.a(this.a, appLovinNativeAd, false);
        this.b.a(appLovinNativeAd, this.a);
    }

    public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
    }

    public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
    }
}
