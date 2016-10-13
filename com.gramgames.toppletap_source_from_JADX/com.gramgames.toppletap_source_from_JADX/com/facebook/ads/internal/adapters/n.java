package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAd.Image;
import com.facebook.ads.NativeAd.Rating;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.internal.util.b;
import com.facebook.ads.internal.util.c;
import com.facebook.ads.internal.util.e;
import com.facebook.ads.internal.util.f;
import com.facebook.ads.internal.util.g;
import com.facebook.ads.internal.util.o;
import com.facebook.ads.internal.util.s;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class n implements a {
    private static final String a;
    private final String A;
    private final Image B;
    private final String C;
    private final String D;
    private final a E;
    private final NativeAdViewAttributes F;
    private final String G;
    private boolean H;
    private boolean I;
    private boolean J;
    private long K;
    private com.facebook.ads.internal.util.b.a L;
    private final Uri b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final Image h;
    private final Image i;
    private final Rating j;
    private final String k;
    private final String l;
    private final String m;
    private final String n;
    private final e o;
    private final String p;
    private final Collection<String> q;
    private final boolean r;
    private final boolean s;
    private final boolean t;
    private final int u;
    private final int v;
    private final int w;
    private final int x;
    private final String y;
    private final String z;

    class 1 implements Runnable {
        final /* synthetic */ Map a;
        final /* synthetic */ Map b;
        final /* synthetic */ n c;

        1(n nVar, Map map, Map map2) {
            this.c = nVar;
            this.a = map;
            this.b = map2;
        }

        public void run() {
            new o(this.a, this.b).execute(new String[]{this.c.l});
        }
    }

    private static class a {
        Map<String, List<String>> a;

        a(JSONArray jSONArray) {
            this.a = new HashMap();
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    String optString = optJSONObject.optString(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
                    if (!s.a(optString)) {
                        JSONArray optJSONArray = optJSONObject.optJSONArray("urls");
                        if (optJSONArray != null) {
                            List arrayList = new ArrayList(optJSONArray.length());
                            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                arrayList.add(optJSONArray.optString(i2));
                            }
                            this.a.put(optString, arrayList);
                        }
                    }
                }
            }
        }

        void a(String str) {
            List<String> list = (List) this.a.get(str);
            if (list != null && !list.isEmpty()) {
                for (String str2 : list) {
                    new o().execute(new String[]{str2});
                }
            }
        }
    }

    static {
        a = n.class.getSimpleName();
    }

    private n(Uri uri, String str, String str2, String str3, String str4, String str5, Image image, Image image2, Rating rating, String str6, String str7, String str8, e eVar, String str9, Collection<String> collection, boolean z, Image image3, String str10, String str11, a aVar, String str12, boolean z2, boolean z3, int i, int i2, String str13, String str14, String str15, int i3, int i4, NativeAdViewAttributes nativeAdViewAttributes) {
        this.K = 0;
        this.L = null;
        this.b = uri;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = image;
        this.i = image2;
        this.j = rating;
        this.k = str6;
        this.m = str7;
        this.n = str8;
        this.o = eVar;
        this.p = str9;
        this.q = collection;
        this.r = z;
        this.s = z2;
        this.t = z3;
        this.B = image3;
        this.C = str10;
        this.D = str11;
        this.E = aVar;
        this.l = str12;
        this.u = i;
        this.v = i2;
        this.w = i3;
        this.x = i4;
        this.y = str13;
        this.z = str14;
        this.A = str15;
        this.F = nativeAdViewAttributes;
        this.G = UUID.randomUUID().toString();
    }

    private void A() {
        if (!this.J) {
            new o().execute(new String[]{this.n});
            this.J = true;
        }
    }

    private boolean B() {
        return (this.c == null || this.c.length() <= 0 || this.f == null || this.f.length() <= 0 || this.h == null || this.i == null) ? false : true;
    }

    public static n a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        NativeAdViewAttributes nativeAdViewAttributes;
        JSONArray jSONArray;
        Uri parse = Uri.parse(jSONObject.optString("fbad_command"));
        String optString = jSONObject.optString(ShareConstants.WEB_DIALOG_PARAM_TITLE);
        String optString2 = jSONObject.optString("subtitle");
        String optString3 = jSONObject.optString(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY);
        String optString4 = jSONObject.optString("call_to_action");
        String optString5 = jSONObject.optString("social_context");
        Image fromJSONObject = Image.fromJSONObject(jSONObject.optJSONObject("icon"));
        Image fromJSONObject2 = Image.fromJSONObject(jSONObject.optJSONObject("image"));
        Rating fromJSONObject3 = Rating.fromJSONObject(jSONObject.optJSONObject("star_rating"));
        String optString6 = jSONObject.optString("impression_report_url");
        String optString7 = jSONObject.optString("native_view_report_url");
        String optString8 = jSONObject.optString("click_report_url");
        String optString9 = jSONObject.optString("used_report_url");
        boolean optBoolean = jSONObject.optBoolean("manual_imp");
        boolean optBoolean2 = jSONObject.optBoolean("enable_view_log");
        boolean optBoolean3 = jSONObject.optBoolean("enable_snapshot_log");
        int optInt = jSONObject.optInt("snapshot_log_delay_second", 4);
        int optInt2 = jSONObject.optInt("snapshot_compress_quality", 0);
        int optInt3 = jSONObject.optInt("viewability_check_initial_delay", 0);
        int optInt4 = jSONObject.optInt("viewability_check_interval", AdError.NETWORK_ERROR_CODE);
        Image image = null;
        JSONObject optJSONObject = jSONObject.optJSONObject("ad_choices_icon");
        JSONObject optJSONObject2 = jSONObject.optJSONObject("native_ui_config");
        if (optJSONObject2 == null) {
            nativeAdViewAttributes = null;
        } else {
            NativeAdViewAttributes nativeAdViewAttributes2 = new NativeAdViewAttributes(optJSONObject2);
        }
        if (optJSONObject != null) {
            image = Image.fromJSONObject(optJSONObject);
        }
        String optString10 = jSONObject.optString("ad_choices_link_url");
        String optString11 = jSONObject.optString("request_id");
        e a = e.a(jSONObject.optString("invalidation_behavior"));
        String optString12 = jSONObject.optString("invalidation_report_url");
        try {
            jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
        } catch (JSONException e) {
            e.printStackTrace();
            jSONArray = null;
        }
        n nVar = new n(parse, optString, optString2, optString3, optString4, optString5, fromJSONObject, fromJSONObject2, fromJSONObject3, optString6, optString8, optString9, a, optString12, f.a(jSONArray), optBoolean, image, optString10, optString11, new a(jSONObject.optJSONArray("trackers")), optString7, optBoolean2, optBoolean3, optInt, optInt2, jSONObject.optString(BaseVideoPlayerActivity.VIDEO_URL), jSONObject.optString("video_play_report_url"), jSONObject.optString("video_time_report_url"), optInt3, optInt4, nativeAdViewAttributes);
        return !nVar.B() ? null : nVar;
    }

    private void a(String str, Map<String, String> map, Map<String, Object> map2) {
        if (map2.containsKey(str)) {
            map.put(str, String.valueOf(map2.get(str)));
        }
    }

    private void a(Map<String, String> map, Map<String, Object> map2) {
        if (map2.containsKey("mil")) {
            boolean booleanValue = ((Boolean) map2.get("mil")).booleanValue();
            map2.remove("mil");
            if (!booleanValue) {
                return;
            }
        }
        map.put("mil", String.valueOf(true));
    }

    private Map<String, String> b(Map<String, Object> map) {
        Map<String, String> hashMap = new HashMap();
        if (map.containsKey("view")) {
            hashMap.put("view", String.valueOf(map.get("view")));
        }
        if (map.containsKey("snapshot")) {
            hashMap.put("snapshot", String.valueOf(map.get("snapshot")));
        }
        return hashMap;
    }

    private void b(Map<String, String> map, Map<String, Object> map2) {
        a("nti", map, map2);
        a("nhs", map, map2);
        a("nmv", map, map2);
    }

    public e a() {
        return this.o;
    }

    public void a(int i) {
        if (i == 0 && this.K > 0 && this.L != null) {
            c.a(b.a(this.K, this.L, this.D));
            this.K = 0;
            this.L = null;
        }
    }

    public void a(Context context, Map<String, Object> map) {
        if (!this.I) {
            Map hashMap = new HashMap();
            if (map != null) {
                a(hashMap, (Map) map);
                b(hashMap, map);
                hashMap.put(ServerProtocol.FALLBACK_DIALOG_DISPLAY_VALUE_TOUCH, g.a((Map) map));
            }
            new o(hashMap).execute(new String[]{this.m});
            this.E.a("click");
            this.I = true;
            g.a(context, "Click logged");
        }
        com.facebook.ads.internal.action.a a = com.facebook.ads.internal.action.b.a(context, this.b);
        if (a != null) {
            try {
                this.K = System.currentTimeMillis();
                this.L = a.a();
                a.b();
            } catch (Throwable e) {
                Log.e(a, "Error executing action", e);
            }
        }
    }

    public void a(Map<String, Object> map) {
        if (!this.H) {
            Map hashMap = new HashMap();
            if (map != null) {
                a(hashMap, (Map) map);
                b(hashMap, map);
            }
            new o(hashMap).execute(new String[]{this.k});
            if (p() || q()) {
                try {
                    new Handler().postDelayed(new 1(this, hashMap, b(map)), (long) (this.u * AdError.NETWORK_ERROR_CODE));
                } catch (Exception e) {
                }
            }
            this.E.a("impression");
            this.H = true;
        }
    }

    public String b() {
        return this.p;
    }

    public Collection<String> c() {
        return this.q;
    }

    public Image d() {
        return this.h;
    }

    public Image e() {
        return this.i;
    }

    public String f() {
        A();
        return this.c;
    }

    public String g() {
        A();
        return this.d;
    }

    public String h() {
        A();
        return this.e;
    }

    public String i() {
        A();
        return this.f;
    }

    public String j() {
        A();
        return this.g;
    }

    public Rating k() {
        A();
        return this.j;
    }

    public String l() {
        return this.y;
    }

    public String m() {
        return this.z;
    }

    public String n() {
        return this.A;
    }

    public boolean o() {
        return this.r;
    }

    public boolean p() {
        return this.s;
    }

    public boolean q() {
        return this.t;
    }

    public int r() {
        return (this.v < 0 || this.v > 100) ? 0 : this.v;
    }

    public String s() {
        return this.G;
    }

    public Image t() {
        return this.B;
    }

    public String u() {
        return this.C;
    }

    public String v() {
        return this.D;
    }

    public NativeAdViewAttributes w() {
        return this.F;
    }

    public boolean x() {
        return this.F != null;
    }

    public int y() {
        return this.w;
    }

    public int z() {
        return this.x;
    }
}
