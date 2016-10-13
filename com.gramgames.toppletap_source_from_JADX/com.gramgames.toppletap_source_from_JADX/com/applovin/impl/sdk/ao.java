package com.applovin.impl.sdk;

import android.app.Activity;
import android.widget.Toast;

public class ao {
    private final AppLovinSdkImpl a;
    private final String b;
    private final Activity c;

    public ao(AppLovinSdkImpl appLovinSdkImpl, Activity activity, String str) {
        this.a = appLovinSdkImpl;
        this.b = str;
        this.c = activity;
    }

    void a() {
        this.c.runOnUiThread(new ap(this));
    }

    void a(String str, Throwable th) {
        this.a.getLogger().userError("IncentivizedConfirmationManager", "Unable to show incentivized ad reward dialog. Have you defined com.applovin.adview.AppLovinConfirmationActivity in your manifest?", th);
        Toast.makeText(this.c, str, 1).show();
    }

    String b() {
        return this.b.equals("accepted") ? (String) this.a.a(cb.P) : this.b.equals("quota_exceeded") ? (String) this.a.a(cb.Q) : this.b.equals("rejected") ? (String) this.a.a(cb.R) : (String) this.a.a(cb.S);
    }
}
