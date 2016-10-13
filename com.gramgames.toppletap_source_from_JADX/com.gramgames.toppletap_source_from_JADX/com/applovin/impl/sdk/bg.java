package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinAd;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bg extends bv {
    public bg(AppLovinSdkImpl appLovinSdkImpl) {
        super(appLovinSdkImpl);
    }

    c a(bd bdVar) {
        return NativeAdImpl.SPEC_NATIVE;
    }

    ca a(c cVar) {
        ca ctVar = new ct(this.a, 1, this);
        ctVar.a(true);
        return ctVar;
    }

    Map a() {
        Map hashMap = new HashMap(1);
        hashMap.put(NativeAdImpl.SPEC_NATIVE, new bw(((Integer) this.a.a(cb.aJ)).intValue()));
        return hashMap;
    }

    public void a(c cVar, int i) {
    }

    void a(Object obj, bd bdVar) {
        AppLovinNativeAdLoadListener appLovinNativeAdLoadListener = (AppLovinNativeAdLoadListener) obj;
        appLovinNativeAdLoadListener.onNativeAdsLoaded(Arrays.asList(new AppLovinNativeAd[]{(AppLovinNativeAd) bdVar}));
    }

    void a(Object obj, c cVar, int i) {
        ((AppLovinNativeAdLoadListener) obj).onNativeAdsFailedToLoad(i);
    }

    public void adReceived(AppLovinAd appLovinAd) {
    }

    public /* bridge */ /* synthetic */ bd b(c cVar) {
        return super.b(cVar);
    }

    public /* bridge */ /* synthetic */ void b(c cVar, Object obj) {
        super.b(cVar, obj);
    }

    public /* bridge */ /* synthetic */ boolean c(c cVar) {
        return super.c(cVar);
    }

    public /* bridge */ /* synthetic */ void d(c cVar) {
        super.d(cVar);
    }

    public /* bridge */ /* synthetic */ boolean e(c cVar) {
        return super.e(cVar);
    }

    public /* bridge */ /* synthetic */ void f(c cVar) {
        super.f(cVar);
    }

    public void failedToReceiveAd(int i) {
    }

    public void onNativeAdsFailedToLoad(int i) {
        b(NativeAdImpl.SPEC_NATIVE, i);
    }

    public void onNativeAdsLoaded(List list) {
        AppLovinNativeAd appLovinNativeAd = (AppLovinNativeAd) list.get(0);
        if (((Boolean) this.a.a(cb.br)).booleanValue()) {
            this.a.getNativeAdService().precacheResources(appLovinNativeAd, new bh(this));
        } else {
            c((bd) appLovinNativeAd);
        }
    }
}
