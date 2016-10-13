package com.applovin.impl.sdk;

import android.util.Log;
import com.applovin.sdk.AppLovinLogger;
import gs.gram.mopub.BuildConfig;

class k implements AppLovinLogger {
    private ce a;
    private l b;

    k() {
    }

    void a(ce ceVar) {
        this.a = ceVar;
    }

    void a(l lVar) {
        this.b = lVar;
    }

    boolean a() {
        return this.a != null ? ((Boolean) this.a.a(cb.i)).booleanValue() : false;
    }

    public void d(String str, String str2) {
        if (a()) {
            Log.d(AppLovinLogger.SDK_TAG, "[" + str + "] " + str2);
        }
        if (this.b != null) {
            this.b.a("DEBUG  [" + str + "] " + str2);
        }
    }

    public void e(String str, String str2) {
        e(str, str2, null);
    }

    public void e(String str, String str2, Throwable th) {
        if (a()) {
            Log.e(AppLovinLogger.SDK_TAG, "[" + str + "] " + str2, th);
        }
        if (this.b != null) {
            this.b.a("ERROR  [" + str + "] " + str2 + (th != null ? ": " + th.getMessage() : BuildConfig.FLAVOR));
        }
    }

    public void i(String str, String str2) {
        if (a()) {
            Log.i(AppLovinLogger.SDK_TAG, "[" + str + "] " + str2);
        }
        if (this.b != null) {
            this.b.a("INFO  [" + str + "] " + str2);
        }
    }

    public void userError(String str, String str2) {
        userError(str, str2, null);
    }

    public void userError(String str, String str2, Throwable th) {
        Log.e(AppLovinLogger.SDK_TAG, "[" + str + "] " + str2, th);
        if (this.b != null) {
            this.b.a("USER  [" + str + "] " + str2 + (th != null ? ": " + th.getMessage() : BuildConfig.FLAVOR));
        }
    }

    public void w(String str, String str2) {
        w(str, str2, null);
    }

    public void w(String str, String str2, Throwable th) {
        if (a()) {
            Log.w(AppLovinLogger.SDK_TAG, "[" + str + "] " + str2, th);
        }
        if (this.b != null) {
            this.b.a("WARN  [" + str + "] " + str2);
        }
    }
}
