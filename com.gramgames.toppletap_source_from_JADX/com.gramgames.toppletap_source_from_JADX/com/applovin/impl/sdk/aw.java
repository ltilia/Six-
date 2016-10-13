package com.applovin.impl.sdk;

import android.app.AlertDialog;
import com.applovin.adview.AppLovinInterstitialActivity;

public class aw {
    private final AppLovinSdkImpl a;
    private final AppLovinInterstitialActivity b;
    private AlertDialog c;

    public aw(AppLovinSdkImpl appLovinSdkImpl, AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinSdkImpl;
        this.b = appLovinInterstitialActivity;
    }

    public void a() {
        this.b.runOnUiThread(new ax(this));
    }

    public void b() {
        this.b.runOnUiThread(new ay(this));
    }

    public boolean c() {
        return this.c != null ? this.c.isShowing() : false;
    }
}
