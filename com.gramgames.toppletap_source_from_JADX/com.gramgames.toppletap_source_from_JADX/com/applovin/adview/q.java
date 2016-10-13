package com.applovin.adview;

import android.view.View;
import android.view.View.OnClickListener;

class q implements OnClickListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    q(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
