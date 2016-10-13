package com.applovin.impl.sdk;

import android.content.SharedPreferences.Editor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

class cg {
    private final AppLovinSdkImpl a;
    private final Map b;

    cg(AppLovinSdkImpl appLovinSdkImpl) {
        this.b = new HashMap();
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = appLovinSdkImpl;
    }

    void a() {
        synchronized (this.b) {
            this.b.clear();
        }
        d();
    }

    void a(String str) {
        a(str, 1);
    }

    void a(String str, long j) {
        synchronized (this.b) {
            Long l = (Long) this.b.get(str);
            if (l == null) {
                l = Long.valueOf(0);
            }
            this.b.put(str, Long.valueOf(l.longValue() + j));
        }
        d();
    }

    long b(String str) {
        long longValue;
        synchronized (this.b) {
            Long l = (Long) this.b.get(str);
            if (l == null) {
                l = Long.valueOf(0);
            }
            longValue = l.longValue();
        }
        return longValue;
    }

    JSONObject b() {
        JSONObject jSONObject;
        synchronized (this.b) {
            jSONObject = new JSONObject();
            for (Entry entry : this.b.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    void b(String str, long j) {
        synchronized (this.b) {
            this.b.put(str, Long.valueOf(j));
        }
        d();
    }

    void c() {
        try {
            JSONObject jSONObject = new JSONObject(this.a.getSettingsManager().a().getString("stats", "{}"));
            synchronized (this.b) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    try {
                        String str = (String) keys.next();
                        this.b.put(str, Long.valueOf(jSONObject.getLong(str)));
                    } catch (JSONException e) {
                    }
                }
            }
        } catch (Throwable th) {
            this.a.getLogger().e("StatsManager", "Unable to load stats", th);
        }
    }

    void c(String str) {
        synchronized (this.b) {
            this.b.remove(str);
        }
        d();
    }

    void d() {
        try {
            Editor edit = this.a.getSettingsManager().a().edit();
            edit.putString("stats", b().toString());
            edit.commit();
        } catch (Throwable e) {
            this.a.getLogger().e("StatsManager", "Unable to save stats", e);
        }
    }
}
