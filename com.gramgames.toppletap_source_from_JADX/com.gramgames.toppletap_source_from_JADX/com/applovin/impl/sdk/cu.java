package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.internal.AnalyticsEvents;

class cu implements Runnable {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Context c;

    cu(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.c = appLovinSdkImpl.getApplicationContext();
        this.b = appLovinSdkImpl.getLogger();
    }

    private void c() {
        String str = (String) this.a.a(cb.D);
        if (str.length() > 0) {
            for (String fromString : str.split(",")) {
                AppLovinAdSize fromString2 = AppLovinAdSize.fromString(fromString);
                if (fromString2 != null) {
                    this.a.c().d(new c(fromString2, AppLovinAdType.REGULAR));
                }
            }
        }
        if (((Boolean) this.a.a(cb.E)).booleanValue()) {
            this.a.c().d(new c(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED));
        }
        if (((Boolean) this.a.a(cb.aE)).booleanValue()) {
            this.a.d().d(NativeAdImpl.SPEC_NATIVE);
        }
    }

    boolean a() {
        if (r.a("android.permission.INTERNET", this.c)) {
            return true;
        }
        this.b.userError("TaskInitializeSdk", "Unable to enable AppLovin SDK: no android.permission.INTERNET");
        return false;
    }

    void b() {
        this.a.a().a(new ci(this.a), cw.MAIN, 500);
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        this.b.d("TaskInitializeSdk", "Initializing AppLovin SDK 6.3.0...");
        try {
            if (a()) {
                cg b = this.a.b();
                b.c();
                b.c("ad_imp_session");
                a.b(this.a);
                this.a.getFileManager().e(this.c);
                this.a.getFileManager().d(this.c);
                c();
                b();
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.c);
                if (!AppLovinSdkUtils.isValidString(defaultSharedPreferences.getString("com.applovin.sdk.impl.isFirstRun", null))) {
                    defaultSharedPreferences.edit().putString("com.applovin.sdk.impl.isFirstRun", Boolean.toString(true)).commit();
                }
                this.a.getPersistentPostbackManager().a();
                this.a.getEventService().trackEvent("landing");
                this.a.a(true);
            } else {
                this.a.a(false);
            }
            this.b.d("TaskInitializeSdk", "AppLovin SDK 6.3.0 initialization " + (this.a.isEnabled() ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : "failed") + " in " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        } catch (Throwable th) {
            Throwable th2 = th;
            this.b.d("TaskInitializeSdk", "AppLovin SDK 6.3.0 initialization " + (this.a.isEnabled() ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : "failed") + " in " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        }
    }
}
