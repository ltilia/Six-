package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.ArrayList;
import java.util.List;

class bn implements AppLovinNativeAdLoadListener {
    final /* synthetic */ bm a;

    bn(bm bmVar) {
        this.a = bmVar;
    }

    public void onNativeAdsFailedToLoad(int i) {
        if (this.a.b != null) {
            this.a.b.onNativeAdsFailedToLoad(i);
        }
    }

    public void onNativeAdsLoaded(List list) {
        if (this.a.b != null) {
            List arrayList = new ArrayList();
            arrayList.addAll(this.a.a);
            arrayList.addAll(this.a.c);
            this.a.b.onNativeAdsLoaded(arrayList);
        }
    }
}
