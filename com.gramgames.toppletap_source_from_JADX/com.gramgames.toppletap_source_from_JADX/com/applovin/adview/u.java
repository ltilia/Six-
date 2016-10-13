package com.applovin.adview;

import android.app.Activity;
import com.applovin.impl.adview.InterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinSdk;

final class u implements Runnable {
    final /* synthetic */ AppLovinSdk a;
    final /* synthetic */ Activity b;
    final /* synthetic */ String c;

    u(AppLovinSdk appLovinSdk, Activity activity, String str) {
        this.a = appLovinSdk;
        this.b = activity;
        this.c = str;
    }

    public void run() {
        new InterstitialAdDialogCreatorImpl().createInterstitialAdDialog(this.a, this.b).show(this.c);
    }
}
