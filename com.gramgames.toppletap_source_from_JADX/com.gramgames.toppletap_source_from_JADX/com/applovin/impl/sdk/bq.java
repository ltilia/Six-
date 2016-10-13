package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import java.util.HashMap;
import java.util.Map;

public class bq {
    private static bq d;
    private final Map a;
    private final Map b;
    private final Object c;

    private bq() {
        this.a = new HashMap(1);
        this.b = new HashMap(1);
        this.c = new Object();
    }

    public static synchronized bq a() {
        bq bqVar;
        synchronized (bq.class) {
            if (d == null) {
                d = new bq();
            }
            bqVar = d;
        }
        return bqVar;
    }

    public Map a(AppLovinAd appLovinAd) {
        Map map;
        AppLovinAdImpl appLovinAdImpl = (AppLovinAdImpl) appLovinAd;
        synchronized (this.c) {
            map = (Map) this.b.remove(appLovinAdImpl);
        }
        return map;
    }

    public void a(AppLovinAd appLovinAd, String str) {
        AppLovinAdImpl appLovinAdImpl = (AppLovinAdImpl) appLovinAd;
        synchronized (this.c) {
            this.a.put(appLovinAdImpl, str);
        }
    }

    public void a(AppLovinAd appLovinAd, Map map) {
        AppLovinAdImpl appLovinAdImpl = (AppLovinAdImpl) appLovinAd;
        synchronized (this.c) {
            this.b.put(appLovinAdImpl, map);
        }
    }

    public String b(AppLovinAd appLovinAd) {
        String str;
        AppLovinAdImpl appLovinAdImpl = (AppLovinAdImpl) appLovinAd;
        synchronized (this.c) {
            str = (String) this.a.remove(appLovinAdImpl);
        }
        return str;
    }
}
