package com.applovin.impl.sdk;

import android.graphics.Point;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.exoplayer.hls.HlsChunkSource;
import com.mopub.common.AdType;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class cr extends ca implements dl {
    private final AppLovinAdSize a;
    private final AppLovinAdType b;
    private final AppLovinAdLoadListener c;
    private boolean d;

    cr(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("FetchNextAd", appLovinSdkImpl);
        this.d = false;
        this.a = appLovinAdSize;
        this.b = appLovinAdType;
        this.c = appLovinAdLoadListener;
    }

    private void a(cg cgVar) {
        if (System.currentTimeMillis() - cgVar.b("ad_session_start") > ((long) ((Integer) this.f.a(cb.r)).intValue()) * HlsChunkSource.DEFAULT_PLAYLIST_BLACKLIST_MS) {
            cgVar.b("ad_session_start", System.currentTimeMillis());
            cgVar.c("ad_imp_session");
        }
    }

    private void b(int i) {
        this.g.e(this.e, "Unable to fetch " + this.a + " ad: server returned " + i);
        try {
            a(i);
        } catch (Throwable th) {
            this.g.userError(this.e, "Unable process a failure to recieve an ad", th);
        }
        q.b(i, this.f);
    }

    private void b(JSONObject jSONObject) {
        q.a(jSONObject, this.f);
        this.f.a().a(a(jSONObject), cw.MAIN);
    }

    private void d(Map map) {
        map.put("api_did", this.f.a(cb.c));
        map.put("sdk_key", this.f.getSdkKey());
        map.put("sdk_version", AppLovinSdk.VERSION);
        map.put("app_version", this.f.getDataCollector().b().b);
        if (!AppLovinSdk.CIS_BUILD_TAG.equals(AppLovinSdk.CIS_BUILD_TAG)) {
            map.put("build_tag", AppLovinSdk.CIS_BUILD_TAG);
        }
        String str = (String) this.f.a(cb.z);
        if (str != null && str.length() > 0) {
            map.put("plugin_version", str);
        }
        map.put("accept", g());
        map.put("v1", Boolean.toString(n.a("android.permission.WRITE_EXTERNAL_STORAGE", this.h)));
        map.put("v2", Boolean.toString(n.a(AppLovinInterstitialActivity.class, this.h)));
        map.put("preloading", String.valueOf(this.d));
        map.put("size", this.a.getLabel());
        map.put("format", AdType.STATIC_NATIVE);
        map.put("ia", Long.toString(this.f.getDataCollector().b().d));
    }

    private void e(Map map) {
        if (((Boolean) this.f.a(cb.F)).booleanValue()) {
            cg b = this.f.b();
            map.put("li", String.valueOf(b.b("ad_imp")));
            map.put("si", String.valueOf(b.b("ad_imp_session")));
        }
    }

    private void f(Map map) {
        if (((Boolean) this.f.a(cb.F)).booleanValue()) {
            Map a = ((m) this.f.getTargetingData()).a();
            if (a != null && !a.isEmpty()) {
                map.putAll(a);
            }
        }
    }

    private String g() {
        String str = "custom_size,launch_app";
        return (n.b() && n.a(AppLovinInterstitialActivity.class, this.h)) ? str + ",video" : str;
    }

    private void g(Map map) {
        Map a = a.a(this.f);
        if (a.isEmpty()) {
            try {
                h(a);
                a.a(a, this.f);
            } catch (Throwable e) {
                this.g.e(this.e, "Unable to populate device information", e);
            }
        }
        map.putAll(a);
        map.put("network", q.a(this.f));
        j(map);
        map.put("vz", dm.a(this.f.getApplicationContext().getPackageName(), this.f));
    }

    private void h(Map map) {
        u a = this.f.getDataCollector().a();
        map.put("brand", dm.c(a.c));
        map.put("carrier", dm.c(a.g));
        map.put("country_code", dm.c(a.f));
        map.put("locale", a.h.toString());
        map.put("model", dm.c(a.a));
        map.put("os", dm.c(a.b));
        map.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_PLATFORM_KEY, "android");
        map.put("revision", dm.c(a.d));
        map.put("orientation_lock", a.i);
        map.put("tz_offset", String.valueOf(a.j));
        map.put("wvvc", String.valueOf(a.k));
        i(map);
    }

    private void i(Map map) {
        Point a = n.a(this.f.getApplicationContext());
        map.put("dx", Integer.toString(a.x));
        map.put("dy", Integer.toString(a.y));
    }

    private void j(Map map) {
        s c = this.f.getDataCollector().c();
        String str = c.b;
        boolean z = c.a;
        if ((!z || ((Boolean) this.f.getSettingsManager().a(cb.aW)).booleanValue()) && AppLovinSdkUtils.isValidString(str)) {
            map.put("idfa", str);
        }
        map.put("dnt", Boolean.toString(z));
    }

    protected ca a(JSONObject jSONObject) {
        return new da(jSONObject, this.c, this.f);
    }

    protected void a(int i) {
        if (this.c == null) {
            return;
        }
        if (this.c instanceof w) {
            ((w) this.c).a(new c(this.a, this.b), i);
        } else {
            this.c.failedToReceiveAd(i);
        }
    }

    protected void a(Map map) {
        f(map);
        g(map);
        e(map);
        d(map);
        b(map);
        c(map);
    }

    public void a(boolean z) {
        this.d = z;
    }

    void b() {
        super.b();
        b(-410);
    }

    protected void b(Map map) {
        if (this.b != null) {
            map.put("require", this.b.getLabel());
        }
    }

    String c() {
        Map hashMap = new HashMap();
        a(hashMap);
        String d = d();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(d);
        stringBuffer.append("?");
        stringBuffer.append(dm.a(hashMap));
        return stringBuffer.toString();
    }

    protected void c(Map map) {
        di a = dg.a().a("tFNA");
        if (a != null) {
            map.put("etf", Long.toString(a.b()));
            map.put("ntf", a.a());
        }
        a = dg.a().a("tRA");
        if (a != null) {
            map.put("etr", Long.toString(a.b()));
            map.put("ntr", a.a());
            map.put("fvr", a.c() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
    }

    protected String d() {
        return q.b("2.0/ad", this.f);
    }

    public String e() {
        return "tFNA";
    }

    public boolean f() {
        return false;
    }

    public void run() {
        if (this.d) {
            this.g.d(this.e, "Preloading next ad...");
        } else {
            this.g.d(this.e, "Fetching next ad...");
        }
        cg b = this.f.b();
        b.a("ad_req");
        a(b);
        try {
            dc csVar = new cs(this, "RepeatFetchNextAd", cb.h, this.f);
            csVar.a(cb.k);
            csVar.run();
        } catch (Throwable th) {
            this.g.e(this.e, "Unable to fetch " + this.a + " ad", th);
            b(0);
        }
    }
}
