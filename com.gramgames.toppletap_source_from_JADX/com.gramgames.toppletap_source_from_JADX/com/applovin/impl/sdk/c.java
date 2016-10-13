package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

public class c {
    private AppLovinAdSize a;
    private AppLovinAdType b;

    public c(AppLovinAd appLovinAd) {
        this.a = appLovinAd.getSize();
        this.b = appLovinAd.getType();
    }

    public c(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        this.a = appLovinAdSize;
        this.b = appLovinAdType;
    }

    public AppLovinAdSize a() {
        return this.a;
    }

    public AppLovinAdType b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (this.a == null ? cVar.a != null : !this.a.equals(cVar.a)) {
            if (this.b != null) {
                if (this.b.equals(cVar.b)) {
                    return true;
                }
            } else if (cVar.b == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.a != null ? this.a.hashCode() : 0) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "AdSpec{size=" + this.a + ", type=" + this.b + '}';
    }
}
