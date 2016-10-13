package com.applovin.adview;

import android.view.View;
import android.view.View.OnClickListener;

class t implements OnClickListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    t(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void onClick(View view) {
        this.a.A.performClick();
    }
}
