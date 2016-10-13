package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class z implements AppLovinAdClickListener {
    final /* synthetic */ ah a;
    final /* synthetic */ x b;

    z(x xVar, ah ahVar) {
        this.b = xVar;
        this.a = ahVar;
    }

    public void adClicked(AppLovinAd appLovinAd) {
        AppLovinAdClickListener e = this.a.e();
        if (e != null) {
            e.adClicked(appLovinAd);
        }
    }
}
