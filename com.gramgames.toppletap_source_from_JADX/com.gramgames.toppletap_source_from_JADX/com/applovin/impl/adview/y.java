package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class y implements AppLovinAdDisplayListener {
    final /* synthetic */ ah a;
    final /* synthetic */ x b;

    y(x xVar, ah ahVar) {
        this.b = xVar;
        this.a = ahVar;
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        super.show();
        if (!this.b.j) {
            AppLovinAdDisplayListener d = this.a.d();
            if (d != null) {
                d.adDisplayed(appLovinAd);
            }
            this.b.j = true;
        }
    }

    public void adHidden(AppLovinAd appLovinAd) {
        this.b.a.runOnUiThread(this.b.f);
        AppLovinAdDisplayListener d = this.a.d();
        if (!(this.b.k || d == null)) {
            d.adHidden(appLovinAd);
            this.b.k = true;
        }
        this.a.a(false);
    }
}
