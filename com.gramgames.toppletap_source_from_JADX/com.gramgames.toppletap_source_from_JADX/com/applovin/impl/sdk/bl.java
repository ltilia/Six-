package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;

class bl implements AppLovinNativeAdPrecacheListener {
    final /* synthetic */ AppLovinNativeAdPrecacheListener a;
    final /* synthetic */ bi b;

    bl(bi biVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        this.b = biVar;
        this.a = appLovinNativeAdPrecacheListener;
    }

    public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
    }

    public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
    }

    public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        this.b.a(this.a, appLovinNativeAd, i, true);
    }

    public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
        this.b.a(this.a, appLovinNativeAd, true);
    }
}
