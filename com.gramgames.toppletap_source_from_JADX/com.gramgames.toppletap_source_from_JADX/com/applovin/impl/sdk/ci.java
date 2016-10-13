package com.applovin.impl.sdk;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.internal.NativeProtocol;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

class ci extends ca {
    ci(AppLovinSdkImpl appLovinSdkImpl) {
        super("SubmitData", appLovinSdkImpl);
    }

    void a(JSONObject jSONObject) {
        try {
            JSONObject a = q.a(jSONObject);
            ce settingsManager = this.f.getSettingsManager();
            settingsManager.a(cb.c, a.getString("device_id"));
            settingsManager.a(cb.e, a.getString("device_token"));
            settingsManager.a(cb.d, a.getString("publisher_id"));
            settingsManager.b();
            q.a(a, this.f);
            if (a.has("adserver_parameters")) {
                settingsManager.a(cb.s, a.getJSONObject("adserver_parameters").toString());
            }
        } catch (Throwable e) {
            this.g.e(this.e, "Unable to parse API response", e);
        }
    }

    void b(JSONObject jSONObject) {
        r dataCollector = this.f.getDataCollector();
        t b = dataCollector.b();
        u a = dataCollector.a();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("model", a.a);
        jSONObject2.put("os", a.b);
        jSONObject2.put("brand", a.c);
        jSONObject2.put("sdk_version", a.e);
        jSONObject2.put("revision", a.d);
        jSONObject2.put("country_code", a.f);
        jSONObject2.put("carrier", a.g);
        jSONObject2.put("orientation_lock", a.i);
        jSONObject2.put("tz_offset", a.j);
        jSONObject2.put("wvvc", a.k);
        jSONObject2.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "android");
        s c = dataCollector.c();
        String str = c.b;
        boolean z = c.a;
        if ((!z || ((Boolean) this.f.getSettingsManager().a(cb.aW)).booleanValue()) && AppLovinSdkUtils.isValidString(str)) {
            jSONObject2.put("idfa", str);
        }
        jSONObject2.put("dnt", z);
        Locale locale = a.h;
        if (locale != null) {
            jSONObject2.put("locale", locale.toString());
        }
        jSONObject.put("device_info", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("package_name", b.c);
        jSONObject3.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, b.a);
        jSONObject3.put("app_version", b.b);
        jSONObject3.put("installed_at", b.d);
        jSONObject3.put("applovin_sdk_version", AppLovinSdk.VERSION);
        jSONObject3.put("ic", this.f.isInitializedInMainActivity());
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.h);
        String string = defaultSharedPreferences.getString("com.applovin.sdk.impl.isFirstRun", null);
        if (AppLovinSdkUtils.isValidString(string)) {
            jSONObject3.put("first_install", string);
            if (string.equalsIgnoreCase(Boolean.toString(true))) {
                defaultSharedPreferences.edit().putString("com.applovin.sdk.impl.isFirstRun", Boolean.toString(false)).apply();
            }
        }
        String str2 = (String) this.f.a(cb.z);
        if (str2 != null && str2.length() > 0) {
            jSONObject3.put("plugin_version", str2);
        }
        jSONObject.put("app_info", jSONObject3);
        if (((Boolean) this.f.a(cb.F)).booleanValue()) {
            Map a2 = ((m) this.f.getTargetingData()).a();
            if (!(a2 == null || a2.isEmpty())) {
                jSONObject.put("targeting", bc.a(a2));
            }
            jSONObject.put("stats", this.f.b().b());
        }
    }

    void c(JSONObject jSONObject) {
        dc cjVar = new cj(this, "Repeat" + this.e, cb.f, this.f, jSONObject);
        cjVar.a(cb.j);
        cjVar.run();
    }

    public void run() {
        try {
            this.g.i(this.e, "Submitting user data...");
            JSONObject jSONObject = new JSONObject();
            b(jSONObject);
            c(jSONObject);
        } catch (Throwable e) {
            this.g.e(this.e, "Unable to build JSON message with collected data", e);
        }
    }
}
