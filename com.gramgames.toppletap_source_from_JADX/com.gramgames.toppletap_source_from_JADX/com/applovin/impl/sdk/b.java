package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class b extends bv {
    public b(AppLovinSdkImpl appLovinSdkImpl) {
        super(appLovinSdkImpl);
    }

    private cd a(AppLovinAdType appLovinAdType, AppLovinAdSize appLovinAdSize) {
        return appLovinAdSize.equals(AppLovinAdSize.BANNER) ? cb.ab : appLovinAdSize.equals(AppLovinAdSize.MREC) ? cb.ac : appLovinAdSize.equals(AppLovinAdSize.INTERSTITIAL) ? cb.ad : appLovinAdSize.equals(AppLovinAdSize.LEADER) ? cb.ae : cb.ab;
    }

    c a(bd bdVar) {
        return new c((AppLovinAd) bdVar);
    }

    ca a(c cVar) {
        ca crVar = new cr(cVar.a(), cVar.b(), this, this.a);
        crVar.a(true);
        return crVar;
    }

    Map a() {
        Map hashMap = new HashMap(5);
        for (AppLovinAdSize appLovinAdSize : AppLovinAdSize.allSizes()) {
            hashMap.put(new c(appLovinAdSize, AppLovinAdType.REGULAR), new bw(((Integer) this.a.a(a(AppLovinAdType.REGULAR, appLovinAdSize))).intValue()));
        }
        hashMap.put(new c(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED), new bw(((Integer) this.a.a(cb.af)).intValue()));
        return hashMap;
    }

    public void a(c cVar, int i) {
        b(cVar, i);
    }

    void a(Object obj, bd bdVar) {
        ((AppLovinAdLoadListener) obj).adReceived((AppLovinAd) bdVar);
    }

    void a(Object obj, c cVar, int i) {
        if (obj instanceof w) {
            ((w) obj).a(cVar, i);
        }
        if (obj instanceof AppLovinAdLoadListener) {
            ((AppLovinAdLoadListener) obj).failedToReceiveAd(i);
        }
    }

    public void adReceived(AppLovinAd appLovinAd) {
        c((bd) appLovinAd);
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
    }

    public void onNativeAdsLoaded(List list) {
    }
}
