package com.chartboost.sdk.Tracking;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.b;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.c;
import com.chartboost.sdk.impl.ay;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.games.Games;
import gs.gram.mopub.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;

public class a implements com.chartboost.sdk.Libraries.a {
    private static final String b;
    private static a i;
    private String c;
    private JSONArray d;
    private long e;
    private long f;
    private long g;
    private h h;

    static {
        b = a.class.getSimpleName();
    }

    private a() {
        this.g = System.currentTimeMillis();
        this.d = new JSONArray();
        this.h = new h(false);
    }

    public static a a() {
        if (i == null) {
            synchronized (Chartboost.class) {
                if (i == null) {
                    i = new a();
                }
            }
        }
        return i;
    }

    public static void b() {
        a(TtmlNode.START);
        a("did-become-active");
    }

    public static void a(String str) {
        i.a("session", str, null, null, null, null, "session", false);
    }

    public void c() {
        a(false);
    }

    public void a(boolean z) {
        com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
        a.a("complete", Boolean.valueOf(z));
        i.a("session", TtmlNode.END, null, null, null, null, a.e(), "session", false);
        a("did-become-active");
    }

    public static void a(String str, String str2, String str3, String str4) {
        i.a("webview-track", str, str2, str3, str4, null, null, "system", false);
    }

    public static void a(JSONObject jSONObject) {
        i.a("folder", c.H().booleanValue() ? AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, null, null, null, null, jSONObject, "system", false);
    }

    public static void a(String str, String str2, String str3, boolean z) {
        i.a("ad-get", str, str2, TextUtils.isEmpty(str3) ? "empty-adid" : str3, b(z), "single", null, "system", false);
    }

    public static void a(String str, String str2, String str3) {
        i.a("ad-show", str, str2, str3, null, null, "system", false);
    }

    public static void b(String str, String str2, String str3) {
        i.a("ad-click", str, str2, str3, null, null, "system", false);
    }

    public static void c(String str, String str2, String str3) {
        i.a("ad-close", str, str2, str3, null, null, "system", false);
    }

    public static void d(String str, String str2, String str3) {
        i.a("ad-dismiss", str, str2, str3, null, null, "system", false);
    }

    public static void a(String str, String str2, String str3, CBImpressionError cBImpressionError) {
        i.a("ad-error", str, str2, TextUtils.isEmpty(str3) ? "empty-adid" : str3, cBImpressionError != null ? cBImpressionError.toString() : BuildConfig.FLAVOR, null, "system", false);
    }

    public static void a(String str, String str2, String str3, String str4, boolean z) {
        i.a("ad-error", str, str2, TextUtils.isEmpty(str3) ? "empty-adid" : str3, str4, null, "system", z);
    }

    public static void b(String str, String str2, String str3, String str4) {
        i.a("ad-warning", str, str2, TextUtils.isEmpty(str3) ? "empty-adid" : str3, str4, null, "system", false);
    }

    public static void a(String str, String str2) {
        i.a("asset-prefetcher", TtmlNode.START, c.H().booleanValue() ? AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, str, str2, null, null, "system", false);
    }

    public static void c(String str, String str2, String str3, String str4) {
        i.a("asset-prefetcher", "failure", c.H().booleanValue() ? AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, str, str2, str4, null, "system", false);
    }

    public static void e(String str, String str2, String str3) {
        i.a("asset-prefetcher", GraphResponse.SUCCESS_KEY, c.H().booleanValue() ? AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, str, str2, str3, null, "system", false);
    }

    public static void b(String str, String str2) {
        i.a("playback-complete", str, str2, null, null, null, "system", false);
    }

    public static void c(String str, String str2) {
        i.a("replay", str, str2, null, null, null, "system", false);
    }

    public static void d(String str, String str2) {
        i.a("playback-start", str, str2, null, null, null, "system", false);
    }

    public static void e(String str, String str2) {
        i.a("playback-stop", str, str2, null, null, null, "system", false);
    }

    public static void a(String str, String str2, String str3, String str4, String str5, String str6, JSONObject jSONObject) {
        i.a(str, str2, str3, str4, str5, str6, jSONObject, "system", false);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        i.a(str, str2, str3, str4, str5, str6, new JSONObject(), str7, z);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, JSONObject jSONObject, String str7, boolean z) {
        JSONObject m = c.m();
        com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
        if (m != null && m.optBoolean(str7)) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this.e;
            currentTimeMillis -= this.g;
            a.a(NotificationCompatApi21.CATEGORY_EVENT, a((Object) str));
            a.a("kingdom", a((Object) str2));
            a.a("phylum", a((Object) str3));
            a.a("class", a((Object) str4));
            a.a("family", a((Object) str5));
            a.a("genus", a((Object) str6));
            String str8 = "meta";
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            a.a(str8, jSONObject);
            a.a("clientTimestamp", Long.valueOf(System.currentTimeMillis() / 1000));
            a.a("session_id", i());
            a.a("totalSessionTime", Long.valueOf(j / 1000));
            a.a("currentSessionTime", Long.valueOf(currentTimeMillis / 1000));
            synchronized (this) {
                this.d.put(a.e());
                Object a2 = com.chartboost.sdk.Libraries.e.a.a();
                a2.a("events", this.d);
                CBLogging.a(b, "###Writing" + a((Object) str) + "to tracking cache dir");
                h();
                if (z || d()) {
                    i.a(com.chartboost.sdk.Libraries.e.a.a(a2)).t();
                    l();
                }
            }
        }
    }

    public boolean d() {
        if (this.d == null || this.d.length() < 50) {
            return false;
        }
        return true;
    }

    public String e() {
        com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
        a.a("startTime", Long.valueOf(System.currentTimeMillis()));
        a.a("deviceID", com.chartboost.sdk.Libraries.c.e());
        this.c = b.b(a.toString().getBytes());
        return this.c;
    }

    public void f() {
        com.chartboost.sdk.Libraries.e.a a = this.h.a(this.h.h(), "cb_previous_session_info");
        if (a != null) {
            this.f = a.i("timestamp");
            this.e = a.i("start_timestamp");
            this.c = a.e("session_id");
            if (System.currentTimeMillis() - this.f > 180000) {
                a(true);
            } else if (!TextUtils.isEmpty(this.c)) {
                h();
                return;
            }
        }
        g();
    }

    public void g() {
        long currentTimeMillis = System.currentTimeMillis();
        this.e = currentTimeMillis;
        this.f = currentTimeMillis;
        this.c = e();
        a(currentTimeMillis, currentTimeMillis);
        SharedPreferences a = CBUtility.a();
        int i = a.getInt("cbPrefSessionCount", 0) + 1;
        Editor edit = a.edit();
        edit.putInt("cbPrefSessionCount", i);
        edit.commit();
    }

    public void h() {
        a(this.e, System.currentTimeMillis());
    }

    public void a(long j, long j2) {
        com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
        a.a("start_timestamp", Long.valueOf(j));
        a.a("timestamp", Long.valueOf(j2));
        a.a("session_id", this.c);
        this.h.a(this.h.h(), "cb_previous_session_info", a);
    }

    public ay a(com.chartboost.sdk.Libraries.e.a aVar) {
        ay ayVar = new ay("/api/track");
        ayVar.a("track", (Object) aVar);
        ayVar.a(g.a(g.a(Games.EXTRA_STATUS, com.chartboost.sdk.Libraries.a.a)));
        ayVar.a(com.chartboost.sdk.impl.l.a.LOW);
        return ayVar;
    }

    public String toString() {
        return "Session [ startTime: " + k() + " sessionEvents: " + j() + " ]";
    }

    public String i() {
        return this.c;
    }

    public JSONArray j() {
        return this.d;
    }

    public long k() {
        return this.e;
    }

    public static String b(boolean z) {
        return z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }

    private static Object a(Object obj) {
        return obj != null ? obj : BuildConfig.FLAVOR;
    }

    public void l() {
        this.d = new JSONArray();
    }
}
