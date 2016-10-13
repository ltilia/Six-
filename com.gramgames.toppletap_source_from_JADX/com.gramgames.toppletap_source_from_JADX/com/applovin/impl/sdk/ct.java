package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.Map;
import org.json.JSONObject;

class ct extends cr {
    private int a;
    private final AppLovinNativeAdLoadListener b;

    public ct(AppLovinSdkImpl appLovinSdkImpl, int i, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super(NativeAdImpl.SIZE_NATIVE, NativeAdImpl.TYPE_NATIVE, null, appLovinSdkImpl);
        this.b = appLovinNativeAdLoadListener;
        this.a = i;
    }

    protected ca a(JSONObject jSONObject) {
        return new db(jSONObject, this.f, this.b);
    }

    protected void a(int i) {
        if (this.b != null) {
            this.b.onNativeAdsFailedToLoad(i);
        }
    }

    protected void b(Map map) {
        map.put("slot_count", Integer.toString(this.a));
    }

    protected void c(Map map) {
        di a = dg.a().a("tFNW");
        if (a != null) {
            map.put("etfw", Long.toString(a.b()));
            map.put("ntfw", a.a());
        }
    }

    protected String d() {
        return q.b("nad", this.f);
    }

    public String e() {
        return "tFNW";
    }
}
