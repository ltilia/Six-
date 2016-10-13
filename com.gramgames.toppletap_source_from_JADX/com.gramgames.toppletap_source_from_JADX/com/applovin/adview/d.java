package com.applovin.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.mopub.volley.DefaultRetryPolicy;

class d implements Runnable {
    final /* synthetic */ AppLovinInterstitialActivity a;

    d(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void run() {
        try {
            if (this.a.n) {
                this.a.y.setVisibility(0);
                return;
            }
            this.a.n = true;
            if (this.a.m() && this.a.z != null) {
                this.a.z.setVisibility(0);
                this.a.z.bringToFront();
            }
            this.a.y.setVisibility(0);
            this.a.y.bringToFront();
            Animation alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            alphaAnimation.setDuration((long) this.a.e.e());
            alphaAnimation.setRepeatCount(0);
            this.a.y.startAnimation(alphaAnimation);
        } catch (Throwable th) {
            this.a.dismiss();
        }
    }
}
