package com.applovin.impl.sdk;

import android.content.Context;
import com.applovin.sdk.AppLovinLogger;

abstract class ca implements Runnable {
    final String e;
    protected final AppLovinSdkImpl f;
    final AppLovinLogger g;
    final Context h;

    ca(String str, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f = appLovinSdkImpl;
        if (str == null) {
            str = getClass().getSimpleName();
        }
        this.e = str;
        this.g = appLovinSdkImpl.getLogger();
        this.h = appLovinSdkImpl.getApplicationContext();
    }

    String a() {
        return this.e;
    }

    void b() {
    }
}
