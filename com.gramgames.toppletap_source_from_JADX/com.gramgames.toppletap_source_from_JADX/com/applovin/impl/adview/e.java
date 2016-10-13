package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

class e implements AppLovinAdVideoPlaybackListener {
    final /* synthetic */ AdViewControllerImpl a;

    e(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    public void videoPlaybackBegan(AppLovinAd appLovinAd) {
        if (this.a.x != null) {
            this.a.x.videoPlaybackBegan(appLovinAd);
        }
    }

    public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
        if (this.a.x != null) {
            this.a.x.videoPlaybackEnded(appLovinAd, d, z);
        }
    }
}
