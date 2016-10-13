package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

class aa implements AppLovinAdVideoPlaybackListener {
    final /* synthetic */ ah a;
    final /* synthetic */ x b;

    aa(x xVar, ah ahVar) {
        this.b = xVar;
        this.a = ahVar;
    }

    public void videoPlaybackBegan(AppLovinAd appLovinAd) {
        AppLovinAdVideoPlaybackListener c = this.a.c();
        if (c != null) {
            c.videoPlaybackBegan(appLovinAd);
        }
    }

    public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
        AppLovinAdVideoPlaybackListener c = this.a.c();
        if (c != null) {
            c.videoPlaybackEnded(appLovinAd, d, z);
        }
    }
}
