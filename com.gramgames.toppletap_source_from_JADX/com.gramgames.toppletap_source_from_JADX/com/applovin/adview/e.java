package com.applovin.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.mopub.volley.DefaultRetryPolicy;

class e implements Runnable {
    final /* synthetic */ AppLovinInterstitialActivity a;

    e(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void run() {
        try {
            if (!this.a.o && this.a.A != null) {
                this.a.o = true;
                this.a.A.setVisibility(0);
                Animation alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                alphaAnimation.setDuration((long) this.a.e.e());
                alphaAnimation.setRepeatCount(0);
                this.a.A.startAnimation(alphaAnimation);
                if (this.a.m() && this.a.B != null) {
                    this.a.B.setVisibility(0);
                    this.a.B.bringToFront();
                }
            }
        } catch (Throwable th) {
            this.a.d.w("AppLovinInterstitialActivity", "Unable to show skip button: " + th);
        }
    }
}
