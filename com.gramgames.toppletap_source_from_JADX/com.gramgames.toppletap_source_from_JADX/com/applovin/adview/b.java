package com.applovin.adview;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class b implements AnimationListener {
    final /* synthetic */ boolean a;
    final /* synthetic */ AppLovinInterstitialActivity b;

    b(AppLovinInterstitialActivity appLovinInterstitialActivity, boolean z) {
        this.b = appLovinInterstitialActivity;
        this.a = z;
    }

    public void onAnimationEnd(Animation animation) {
        if (!this.a) {
            this.b.C.setVisibility(4);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        this.b.C.setVisibility(0);
    }
}
