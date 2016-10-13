package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.g.k;
import com.chartboost.sdk.Model.CBError;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.actions.SearchIntents;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ay {
    protected static e b;
    private static Map<String, Object> g;
    private static com.chartboost.sdk.Libraries.e.a i;
    protected com.chartboost.sdk.Libraries.e.a a;
    private String c;
    private String d;
    private Map<String, Object> e;
    private Map<String, Object> f;
    private String h;
    private c j;
    private boolean k;
    private boolean l;
    private com.chartboost.sdk.Libraries.g.a m;
    private az n;
    private int o;
    private boolean p;
    private boolean q;
    private com.chartboost.sdk.impl.l.a r;

    public interface c {
        void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar);

        void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, CBError cBError);
    }

    public static abstract class d implements c {
        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, CBError cBError) {
        }
    }

    private static class a implements c {
        private d a;
        private b b;

        public a(d dVar, b bVar) {
            this.a = dVar;
            this.b = bVar;
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar) {
            if (this.a != null) {
                this.a.a(aVar, ayVar);
            }
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, CBError cBError) {
            if (this.b != null) {
                this.b.a(aVar, ayVar, cBError);
            }
        }
    }

    public static abstract class b implements c {
    }

    class e {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public String l;
        public String m;
        public String n;
        public String o;
        public String p;
        public com.chartboost.sdk.Libraries.e.a q;
        public boolean r;
        public String s;
        public Integer t;
        final /* synthetic */ ay u;

        public e(ay ayVar) {
            int width;
            int height;
            Throwable th;
            DisplayMetrics displayMetrics;
            int i;
            Object obj = null;
            int i2 = 0;
            this.u = ayVar;
            Context y = com.chartboost.sdk.c.y();
            this.o = com.chartboost.sdk.c.f();
            if (ServerProtocol.DIALOG_PARAM_SDK_VERSION.equals(Build.PRODUCT)) {
                this.a = "Android Simulator";
            } else {
                this.a = Build.MODEL;
            }
            this.p = Build.MANUFACTURER + " " + Build.MODEL;
            this.b = "Android " + VERSION.RELEASE;
            this.c = Locale.getDefault().getCountry();
            this.d = Locale.getDefault().getLanguage();
            this.g = "6.4.1";
            this.m = String.valueOf(Long.valueOf(new Date().getTime() / 1000).intValue());
            this.n = BuildConfig.FLAVOR + y.getResources().getDisplayMetrics().density;
            try {
                String packageName = y.getPackageName();
                this.e = y.getPackageManager().getPackageInfo(packageName, RadialCountdown.BACKGROUND_ALPHA).versionName;
                this.f = packageName;
            } catch (Throwable e) {
                CBLogging.b("CBRequest", "Exception raised getting package mager object", e);
            }
            if (ay.i == null) {
                TelephonyManager telephonyManager = (TelephonyManager) y.getSystemService("phone");
                if (telephonyManager == null || telephonyManager.getPhoneType() == 0) {
                    ay.i = com.chartboost.sdk.Libraries.e.a.a();
                } else {
                    Object obj2;
                    String simOperator = telephonyManager.getSimOperator();
                    if (TextUtils.isEmpty(simOperator)) {
                        obj2 = null;
                    } else {
                        obj2 = simOperator.substring(0, 3);
                        obj = simOperator.substring(3);
                    }
                    ay.i = com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("carrier-name", telephonyManager.getNetworkOperatorName()), com.chartboost.sdk.Libraries.e.a("mobile-country-code", obj2), com.chartboost.sdk.Libraries.e.a("mobile-network-code", obj), com.chartboost.sdk.Libraries.e.a("iso-country-code", telephonyManager.getNetworkCountryIso()), com.chartboost.sdk.Libraries.e.a("phone-type", Integer.valueOf(telephonyManager.getPhoneType())));
                }
            }
            this.q = ay.i;
            this.r = CBUtility.f();
            this.s = CBUtility.g();
            this.t = ax.d();
            try {
                if (y instanceof Activity) {
                    Activity activity = (Activity) y;
                    Rect rect = new Rect();
                    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                    width = rect.width();
                    try {
                        height = rect.height();
                        i2 = width;
                    } catch (Throwable e2) {
                        Throwable th2 = e2;
                        height = width;
                        th = th2;
                        CBLogging.c("CBRequest", "Exception getting activity size", th);
                        i2 = height;
                        height = 0;
                        displayMetrics = y.getResources().getDisplayMetrics();
                        width = displayMetrics.widthPixels;
                        i = displayMetrics.heightPixels;
                        this.j = BuildConfig.FLAVOR + width;
                        this.k = BuildConfig.FLAVOR + i;
                        this.l = BuildConfig.FLAVOR + displayMetrics.densityDpi;
                        i2 = width;
                        height = i;
                        this.h = BuildConfig.FLAVOR + i2;
                        this.i = BuildConfig.FLAVOR + height;
                    }
                }
                height = 0;
            } catch (Throwable e22) {
                th = e22;
                height = 0;
                CBLogging.c("CBRequest", "Exception getting activity size", th);
                i2 = height;
                height = 0;
                displayMetrics = y.getResources().getDisplayMetrics();
                width = displayMetrics.widthPixels;
                i = displayMetrics.heightPixels;
                this.j = BuildConfig.FLAVOR + width;
                this.k = BuildConfig.FLAVOR + i;
                this.l = BuildConfig.FLAVOR + displayMetrics.densityDpi;
                i2 = width;
                height = i;
                this.h = BuildConfig.FLAVOR + i2;
                this.i = BuildConfig.FLAVOR + height;
            }
            displayMetrics = y.getResources().getDisplayMetrics();
            width = displayMetrics.widthPixels;
            i = displayMetrics.heightPixels;
            this.j = BuildConfig.FLAVOR + width;
            this.k = BuildConfig.FLAVOR + i;
            this.l = BuildConfig.FLAVOR + displayMetrics.densityDpi;
            if (i2 <= 0 || i2 > width) {
                i2 = width;
            }
            if (height <= 0 || height > i) {
                height = i;
            }
            this.h = BuildConfig.FLAVOR + i2;
            this.i = BuildConfig.FLAVOR + height;
        }
    }

    static {
        i = null;
    }

    public ay(String str) {
        this.j = null;
        this.k = false;
        this.l = false;
        this.m = null;
        this.p = false;
        this.q = true;
        this.r = com.chartboost.sdk.impl.l.a.NORMAL;
        this.c = str;
        this.h = UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST;
        this.n = az.a(com.chartboost.sdk.c.y());
        a(0);
        if (b == null) {
            b = new e(this);
        }
    }

    protected void a() {
        if (this.f == null) {
            this.f = new HashMap();
        }
        this.f.put("Accept", WebRequest.CONTENT_TYPE_JSON);
        this.f.put("X-Chartboost-Client", CBUtility.d());
        this.f.put("X-Chartboost-API", "6.4.1");
        this.f.put("X-Chartboost-Client", CBUtility.d());
    }

    public static Map<String, Object> b() {
        if (g == null) {
            g = new HashMap();
            g.put("X-Chartboost-Client", CBUtility.d());
            g.put("X-Chartboost-App", com.chartboost.sdk.c.f());
        }
        g.put("X-Chartboost-Reachability", Integer.valueOf(ax.a().b()));
        return g;
    }

    protected String c() {
        return WebRequest.CONTENT_TYPE_JSON;
    }

    public void a(String str, Object obj) {
        if (this.a == null) {
            this.a = com.chartboost.sdk.Libraries.e.a.a();
        }
        this.a.a(str, obj);
    }

    public void a(String str, String str2) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        this.f.put(str, str2);
    }

    public void a(String str, com.chartboost.sdk.Libraries.e.a aVar) {
        if (aVar != null && aVar.c(str)) {
            a(str, aVar.e(str));
        }
    }

    protected void d() {
        int i = 0;
        a("app", b.o);
        a("model", b.a);
        a("device_type", b.p);
        a("os", b.b);
        a("country", b.c);
        a("language", b.d);
        a(ServerProtocol.DIALOG_PARAM_SDK_VERSION, b.g);
        a("timestamp", b.m);
        a("session", Integer.valueOf(CBUtility.a().getInt("cbPrefSessionCount", 0)));
        a("reachability", Integer.valueOf(ax.a().b()));
        a("scale", b.n);
        String str = "is_portrait";
        if (CBUtility.c().a()) {
            i = 1;
        }
        a(str, Integer.valueOf(i));
        a("bundle", b.e);
        a("bundle_id", b.f);
        a("carrier", b.q);
        a("custom_id", com.chartboost.sdk.c.p());
        a("mediation", com.chartboost.sdk.c.e());
        if (com.chartboost.sdk.c.b() != null) {
            a("framework_version", com.chartboost.sdk.c.d());
            a("wrapper_version", com.chartboost.sdk.c.c());
        }
        a("rooted_device", Boolean.valueOf(b.r));
        a("timezone", b.s);
        a("mobile_network", b.t);
        a("dw", b.j);
        a("dh", b.k);
        a("dpi", b.l);
        a("w", b.h);
        a("h", b.i);
        a("identity", com.chartboost.sdk.Libraries.c.b());
        com.chartboost.sdk.Libraries.c.a c = com.chartboost.sdk.Libraries.c.c();
        if (c.b()) {
            a("tracking", Integer.valueOf(c.a()));
        }
    }

    public void e() {
        String f = com.chartboost.sdk.c.f();
        String g = com.chartboost.sdk.c.g();
        g = com.chartboost.sdk.Libraries.b.b(com.chartboost.sdk.Libraries.b.a(String.format(Locale.US, "%s %s\n%s\n%s", new Object[]{this.h, f(), g, g()}).getBytes()));
        a("X-Chartboost-App", f);
        a("X-Chartboost-Signature", g);
    }

    public String f() {
        return h() + CBUtility.a(this.e);
    }

    public String g() {
        return this.a.toString();
    }

    public String h() {
        if (this.c == null) {
            return "/";
        }
        return (this.c.startsWith("/") ? BuildConfig.FLAVOR : "/") + this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public boolean i() {
        return h().equals("/api/track");
    }

    public com.chartboost.sdk.Libraries.e.a j() {
        return this.a;
    }

    public Map<String, Object> k() {
        return this.f;
    }

    public boolean l() {
        return this.l;
    }

    public void a(boolean z) {
        this.l = z;
    }

    public com.chartboost.sdk.Libraries.g.a m() {
        return this.m;
    }

    public boolean n() {
        return this.p;
    }

    public void b(boolean z) {
        this.p = z;
    }

    public void a(com.chartboost.sdk.Libraries.g.a aVar) {
        if (!g.c(aVar)) {
            CBLogging.b("CBRequest", "Validation predicate must be a dictionary style -- either VDictionary, VDictionaryExact, VDictionaryWithValues, or just a list of KV pairs.");
        }
        this.m = aVar;
    }

    public void a(k... kVarArr) {
        this.m = g.a(kVarArr);
    }

    public void b(String str) {
        this.d = str;
    }

    public void a(com.chartboost.sdk.impl.l.a aVar) {
        this.r = aVar;
    }

    public com.chartboost.sdk.impl.l.a o() {
        return this.r;
    }

    public int p() {
        return this.o;
    }

    public void a(int i) {
        this.o = i;
    }

    public boolean q() {
        return this.q;
    }

    public void c(boolean z) {
        this.q = z;
    }

    public boolean r() {
        return this.k;
    }

    public void d(boolean z) {
        this.k = z;
    }

    public c s() {
        return this.j;
    }

    public void t() {
        a(null, null);
    }

    public void a(c cVar) {
        if (!com.chartboost.sdk.c.n()) {
            this.l = false;
            this.p = false;
        }
        this.j = cVar;
        d(true);
        this.n.a(this, cVar);
    }

    public void a(d dVar, b bVar) {
        if (!com.chartboost.sdk.c.n()) {
            this.l = false;
            this.p = false;
        }
        d(true);
        this.j = new a(dVar, bVar);
        this.n.a(this, this.j);
    }

    public static ay a(com.chartboost.sdk.Libraries.e.a aVar) {
        try {
            ay ayVar = new ay(aVar.e("path"));
            ayVar.h = aVar.e("method");
            ayVar.e = aVar.a(SearchIntents.EXTRA_QUERY).f();
            ayVar.a = aVar.a(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY);
            ayVar.f = aVar.a("headers").f();
            ayVar.l = aVar.j("ensureDelivery");
            ayVar.d = aVar.e("eventType");
            ayVar.c = aVar.e("path");
            ayVar.o = aVar.f("retryCount");
            if (aVar.a("callback") instanceof c) {
                ayVar.j = (c) aVar.a("callback");
            }
            return ayVar;
        } catch (Throwable e) {
            CBLogging.d("CBRequest", "Unable to deserialize failed request", e);
            return null;
        }
    }

    public com.chartboost.sdk.Libraries.e.a u() {
        return com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("path", this.c), com.chartboost.sdk.Libraries.e.a("method", this.h), com.chartboost.sdk.Libraries.e.a(SearchIntents.EXTRA_QUERY, com.chartboost.sdk.Libraries.e.a(this.e)), com.chartboost.sdk.Libraries.e.a(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY, this.a), com.chartboost.sdk.Libraries.e.a("eventType", this.d), com.chartboost.sdk.Libraries.e.a("headers", com.chartboost.sdk.Libraries.e.a(this.f)), com.chartboost.sdk.Libraries.e.a("ensureDelivery", Boolean.valueOf(this.l)), com.chartboost.sdk.Libraries.e.a("retryCount", Integer.valueOf(this.o)), com.chartboost.sdk.Libraries.e.a("callback", this.j));
    }
}
