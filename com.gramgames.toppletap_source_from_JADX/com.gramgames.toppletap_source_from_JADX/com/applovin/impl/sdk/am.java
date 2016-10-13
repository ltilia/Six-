package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import java.util.Map;

class am implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ Map b;
    final /* synthetic */ ae c;

    am(ae aeVar, AppLovinAd appLovinAd, Map map) {
        this.c = aeVar;
        this.a = appLovinAd;
        this.b = map;
    }

    public void run() {
        this.c.f.userRewardRejected(this.a, this.b);
    }
}
