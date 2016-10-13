package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinSdkUtils;

class bh implements AppLovinNativeAdPrecacheListener {
    final /* synthetic */ bg a;

    bh(bg bgVar) {
        this.a = bgVar;
    }

    public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        this.a.b(NativeAdImpl.SPEC_NATIVE, i);
    }

    public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
        if (!AppLovinSdkUtils.isValidString(appLovinNativeAd.getVideoUrl())) {
            this.a.c((bd) appLovinNativeAd);
        }
    }

    public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        this.a.b.w("NativeAdPreloadManager", "Video failed to cache during native ad preload. " + i);
        this.a.c((bd) appLovinNativeAd);
    }

    public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
        this.a.c((bd) appLovinNativeAd);
    }
}
