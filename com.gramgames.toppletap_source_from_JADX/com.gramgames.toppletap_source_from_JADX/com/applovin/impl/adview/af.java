package com.applovin.impl.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.applovin.impl.sdk.cf;
import com.mopub.volley.DefaultRetryPolicy;

class af implements Runnable {
    final /* synthetic */ x a;

    af(x xVar) {
        this.a = xVar;
    }

    public void run() {
        try {
            this.a.g.setVisibility(0);
            this.a.g.bringToFront();
            cf cfVar = new cf(this.a.b);
            Animation alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            alphaAnimation.setDuration((long) cfVar.e());
            alphaAnimation.setRepeatCount(0);
            this.a.g.startAnimation(alphaAnimation);
        } catch (Throwable th) {
            this.a.dismiss();
        }
    }
}
