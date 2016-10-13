package com.chartboost.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.chartboost.sdk.Chartboost.CBFramework;
import com.chartboost.sdk.Chartboost.CBMediation;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.ay;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.games.Games;
import com.mopub.mobileads.ChartboostShared;
import com.mopub.mobileads.VungleRewardedVideo;
import gs.gram.mopub.BuildConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public final class c {
    private static float A;
    private static boolean B;
    private static boolean C;
    private static boolean D;
    private static boolean E;
    private static boolean F;
    private static boolean G;
    protected static boolean a;
    public static com.chartboost.sdk.d.a b;
    public static boolean c;
    public static boolean d;
    private static final String e;
    private static String f;
    private static String g;
    private static a h;
    private static boolean i;
    private static boolean j;
    private static CBFramework k;
    private static String l;
    private static String m;
    private static String n;
    private static CBMediation o;
    private static String p;
    private static String q;
    private static SharedPreferences r;
    private static boolean s;
    private static volatile boolean t;
    private static Context u;
    private static Application v;
    private static boolean w;
    private static boolean x;
    private static boolean y;
    private static boolean z;

    public interface a {
        void a();
    }

    static class 1 implements com.chartboost.sdk.impl.ay.c {
        final /* synthetic */ a a;

        1(a aVar) {
            this.a = aVar;
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar) {
            Chartboost.a = false;
            if (aVar.c()) {
                com.chartboost.sdk.Libraries.e.a a = aVar.a("response");
                if (a.c()) {
                    c.a(a);
                }
            }
            if (this.a != null) {
                this.a.a();
            }
            if (!Chartboost.b) {
                if (c.h != null) {
                    c.h.didInitialize();
                }
                Chartboost.b = true;
            }
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, CBError cBError) {
            Chartboost.a = false;
            if (this.a != null) {
                this.a.a();
            }
            if (!Chartboost.b) {
                if (c.h != null) {
                    c.h.didInitialize();
                }
                Chartboost.b = true;
            }
        }
    }

    static {
        e = c.class.getSimpleName();
        a = true;
        i = false;
        j = false;
        k = null;
        l = null;
        m = null;
        n = null;
        o = null;
        p = null;
        q = null;
        r = null;
        s = true;
        t = false;
        u = null;
        v = null;
        w = false;
        x = true;
        y = false;
        z = true;
        A = 250.0f;
        c = true;
        d = VERSION.SDK_INT >= 23;
        B = false;
        C = true;
        D = true;
        E = true;
        F = true;
        G = true;
    }

    private c() {
    }

    private static SharedPreferences X() {
        if (r == null) {
            r = CBUtility.a();
        }
        return r;
    }

    public static boolean a() {
        return C;
    }

    public static void a(Boolean bool) {
        c = bool.booleanValue();
    }

    public static void a(CBFramework cBFramework) {
        if (cBFramework == null) {
            CBLogging.b(e, "Pass a valid CBFramework enum value");
        } else {
            k = cBFramework;
        }
    }

    public static void a(CBFramework cBFramework, String str) {
        if (t()) {
            a(cBFramework);
            l = str;
        }
    }

    public static CBFramework b() {
        t();
        return k == null ? null : k;
    }

    public static String c() {
        if (k == null) {
            return BuildConfig.FLAVOR;
        }
        return String.format("%s %s", new Object[]{k, l});
    }

    public static void a(String str) {
        if (k == null) {
            CBLogging.b(e, "Set a valid CBFramework first");
        } else if (TextUtils.isEmpty(str)) {
            CBLogging.b(e, "Invalid Version String");
        } else {
            m = str;
        }
    }

    public static String d() {
        t();
        return m;
    }

    public static void a(CBMediation cBMediation, String str) {
        o = cBMediation;
        p = str;
        n = o + " " + p;
    }

    public static String e() {
        t();
        return n;
    }

    public static String f() {
        if (!t()) {
            return BuildConfig.FLAVOR;
        }
        f = X().getString(VungleRewardedVideo.APP_ID_KEY, f);
        return f;
    }

    public static void b(String str) {
        f = str;
        X().edit().putString(VungleRewardedVideo.APP_ID_KEY, str).commit();
    }

    public static String g() {
        if (!t()) {
            return BuildConfig.FLAVOR;
        }
        g = X().getString(ChartboostShared.APP_SIGNATURE_KEY, g);
        return g;
    }

    public static void c(String str) {
        g = str;
        X().edit().putString(ChartboostShared.APP_SIGNATURE_KEY, str).commit();
    }

    public static a h() {
        if (t()) {
            return h;
        }
        return null;
    }

    public static void a(a aVar) {
        if (t()) {
            h = aVar;
        }
    }

    public static boolean i() {
        if (b() == null && a) {
            return true;
        }
        return false;
    }

    public static boolean j() {
        if (t()) {
            return j;
        }
        return false;
    }

    public static boolean k() {
        return s;
    }

    public static void a(boolean z) {
        if (t()) {
            s = z;
        }
    }

    public static List<String> l() {
        Object string = X().getString("webview", BuildConfig.FLAVOR);
        try {
            if (!TextUtils.isEmpty(string)) {
                com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
                if (k.c() && !TextUtils.isEmpty(k.e("directories"))) {
                    return k.a("directories").h();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject m() {
        if (!t()) {
            return null;
        }
        Object string = X().getString("trackingLevels", BuildConfig.FLAVOR);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
        if (k.c()) {
            return k.e();
        }
        return null;
    }

    public static boolean n() {
        t();
        return X().getBoolean("retriesEnabled", true);
    }

    public static void a(Level level) {
        if (t()) {
            CBLogging.a = level;
        }
    }

    public static Level o() {
        t();
        return CBLogging.a;
    }

    public static String p() {
        if (t()) {
            return q;
        }
        return BuildConfig.FLAVOR;
    }

    public static void d(String str) {
        q = str;
    }

    public static void a(com.chartboost.sdk.Libraries.e.a aVar) {
        try {
            if (aVar.c()) {
                Map f = aVar.f();
                if (f != null) {
                    Editor edit = X().edit();
                    for (String str : f.keySet()) {
                        Object obj = f.get(str);
                        if (obj instanceof String) {
                            edit.putString(str, (String) obj);
                        } else if (obj instanceof Integer) {
                            edit.putInt(str, ((Integer) obj).intValue());
                        } else if (obj instanceof Float) {
                            edit.putFloat(str, ((Float) obj).floatValue());
                        } else if (obj instanceof Long) {
                            edit.putLong(str, ((Long) obj).longValue());
                        } else if (obj instanceof Boolean) {
                            edit.putBoolean(str, ((Boolean) obj).booleanValue());
                        } else if (obj != null) {
                            edit.putString(str, com.chartboost.sdk.Libraries.e.a.a((HashMap) obj).toString());
                        }
                    }
                    edit.commit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(a aVar) {
        Chartboost.a = true;
        ay ayVar = new ay("/api/config");
        ayVar.a(false);
        ayVar.b(false);
        ayVar.a(com.chartboost.sdk.impl.l.a.HIGH);
        ayVar.a(g.a(g.a(Games.EXTRA_STATUS, com.chartboost.sdk.Libraries.a.a)));
        ayVar.a(new 1(aVar));
    }

    public static boolean q() {
        return t;
    }

    protected static void b(boolean z) {
        t = z;
    }

    public static boolean r() {
        if (t() && s()) {
            return true;
        }
        return false;
    }

    public static boolean s() {
        if (Chartboost.d != null) {
            return true;
        }
        try {
            throw new Exception("Chartboost Weak Activity reference is null");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean a(Activity activity) {
        if (activity != null) {
            return true;
        }
        try {
            throw new Exception("Invalid activity context: Host Activity object is null, Please send a valid activity object");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean t() {
        try {
            if (z() == null) {
                throw new Exception("SDK Initialization error. Activity context seems to be not initialized properly, host activity or application context is being sent as null");
            } else if (TextUtils.isEmpty(f)) {
                throw new Exception("SDK Initialization error. AppId is missing");
            } else if (!TextUtils.isEmpty(g)) {
                return true;
            } else {
                throw new Exception("SDK Initialization error. AppSignature is missing");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void c(boolean z) {
        if (b != null) {
            b.a(z);
        }
    }

    protected static void d(boolean z) {
        if (t()) {
            w = z;
        }
    }

    public static boolean u() {
        return w;
    }

    public static void e(boolean z) {
        if (t()) {
            x = z;
        }
    }

    public static boolean v() {
        return x;
    }

    public static void f(boolean z) {
        if (t()) {
            y = z;
        }
    }

    public static boolean w() {
        return y;
    }

    public static void g(boolean z) {
        if (t()) {
            z = z;
        }
    }

    public static boolean x() {
        return z && !X().getBoolean("prefetchDisable", false);
    }

    public static void a(Context context) {
        u = context;
    }

    public static Context y() {
        return u;
    }

    public static Application z() {
        return v;
    }

    public static void a(Application application) {
        v = application;
    }

    public static boolean b(Activity activity) {
        if (activity == null) {
            try {
                throw new RuntimeException("Invalid activity context passed during intitalization");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        int checkSelfPermission;
        int checkSelfPermission2;
        int checkSelfPermission3;
        int checkSelfPermission4;
        int checkSelfPermission5;
        if (d) {
            checkSelfPermission = activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            checkSelfPermission2 = activity.checkSelfPermission("android.permission.ACCESS_NETWORK_STATE");
            checkSelfPermission3 = activity.checkSelfPermission("android.permission.INTERNET");
            checkSelfPermission4 = activity.checkSelfPermission("android.permission.READ_PHONE_STATE");
            checkSelfPermission5 = activity.checkSelfPermission("android.permission.ACCESS_WIFI_STATE");
        } else {
            checkSelfPermission = activity.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            checkSelfPermission2 = activity.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
            checkSelfPermission3 = activity.checkCallingOrSelfPermission("android.permission.INTERNET");
            checkSelfPermission4 = activity.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE");
            checkSelfPermission5 = activity.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE");
        }
        if (checkSelfPermission != 0) {
            C = true;
        } else {
            C = false;
        }
        if (checkSelfPermission3 != 0) {
            D = true;
            throw new RuntimeException("Please add the permission : android.permission.INTERNET in your android manifest.xml");
        }
        D = false;
        if (checkSelfPermission2 != 0) {
            E = true;
            throw new RuntimeException("Please add the permission :  android.permission.ACCESS_NETWORK_STATE in your android manifest.xml");
        }
        E = false;
        if (checkSelfPermission4 == 0) {
            F = false;
        } else {
            F = true;
        }
        if (checkSelfPermission5 == 0) {
            G = false;
            return true;
        }
        G = true;
        return true;
    }

    public static boolean c(Activity activity) {
        try {
            if (activity.getPackageManager().queryIntentActivities(new Intent(activity, CBImpressionActivity.class), ExecutionOptions.MAX_TRACKING_TAG_STRING_LENGTH).size() > 0) {
                B = true;
            } else {
                B = false;
            }
            if (B) {
                return true;
            }
            throw new RuntimeException("Please add             <activity android:name=\"com.chartboost.sdk.CBImpressionActivity\"\n                  android:excludeFromRecents=\"true\"\n                  android:theme=\"@android:style/Theme.Translucent.NoTitleBar.Fullscreen\"\n                  android:configChanges=\"keyboardHidden|orientation|screenSize\"/> in your android manifest.xml");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String A() {
        if (!H().booleanValue()) {
            return "/interstitial/get";
        }
        return String.format("%s/%s%s", new Object[]{"webview", D(), "/interstitial/get"});
    }

    public static String B() {
        if (!H().booleanValue()) {
            return "/reward/get";
        }
        return String.format("%s/%s%s", new Object[]{"webview", D(), "/reward/get"});
    }

    public static String C() {
        if (!H().booleanValue()) {
            return "/api/video-prefetch";
        }
        return String.format("%s/%s/%s", new Object[]{"webview", D(), "prefetch"});
    }

    public static String D() {
        Object string = X().getString("webview", "v2");
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && !k.a(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION).b()) {
                return k.e(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
            }
        }
        return "v2";
    }

    public static int E() {
        Float e = e("cacheTTLs");
        if (e != null) {
            return (int) TimeUnit.SECONDS.toDays(e.longValue());
        }
        return 7;
    }

    public static int F() {
        Float e = e("cacheMaxUnits");
        if (e == null || e.floatValue() <= 0.0f) {
            return 10;
        }
        return e.intValue();
    }

    public static int G() {
        Float e = e("cacheMaxBytes");
        if (e != null) {
            return e.intValue();
        }
        return 104857600;
    }

    private static Float e(String str) {
        Object string = X().getString("webview", BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && !k.a(str).b()) {
                return Float.valueOf(k.g(str));
            }
        }
        return null;
    }

    public static Boolean H() {
        Object string = X().getString("webview", BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("enabled").c() && VERSION.SDK_INT >= 14) {
                return Boolean.valueOf(k.j("enabled"));
            }
        }
        return Boolean.valueOf(VERSION.SDK_INT >= 14);
    }

    public static boolean I() {
        Object string = X().getString("webview", BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("interstitialEnabled").c() && VERSION.SDK_INT >= 14) {
                return k.j("interstitialEnabled");
            }
        }
        return true;
    }

    public static boolean J() {
        Object string = X().getString("webview", BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("rewardVideoEnabled").c() && VERSION.SDK_INT >= 14) {
                return k.j("rewardVideoEnabled");
            }
        }
        return true;
    }

    public static boolean K() {
        Object string = X().getString("webview", BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("moreAppsEnabled").c() && VERSION.SDK_INT >= 14) {
                return k.j("moreAppsEnabled");
            }
        }
        return true;
    }

    public static boolean L() {
        Object string = X().getString("webview", BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("inplayEnabled").c() && VERSION.SDK_INT >= 14) {
                return k.j("inplayEnabled");
            }
        }
        return true;
    }

    public static boolean M() {
        Object string = X().getString("webview", BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("lockOrientation").c() && VERSION.SDK_INT >= 14) {
                return k.j("lockOrientation");
            }
        }
        return true;
    }

    public static boolean N() {
        Object string = X().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("enabled").c()) {
                return k.j("enabled");
            }
        }
        return true;
    }

    public static boolean O() {
        Object string = X().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("interstitialEnabled").c()) {
                return k.j("interstitialEnabled");
            }
        }
        return true;
    }

    public static boolean P() {
        Object string = X().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("rewardVideoEnabled").c()) {
                return k.j("rewardVideoEnabled");
            }
        }
        return true;
    }

    public static boolean Q() {
        Object string = X().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("moreAppsEnabled").c()) {
                return k.j("moreAppsEnabled");
            }
        }
        return true;
    }

    public static boolean R() {
        Object string = X().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("inplayEnabled").c()) {
                return k.j("inplayEnabled");
            }
        }
        return true;
    }

    public static boolean S() {
        Object string = X().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("lockOrientation").c()) {
                return k.j("lockOrientation");
            }
        }
        return false;
    }

    public static Boolean T() {
        return Boolean.valueOf(X().getBoolean("publisherDisable", false));
    }

    public static int U() {
        Float e = e("prefetchSession");
        if (e == null || e.floatValue() <= 0.0f) {
            return 3;
        }
        return e.intValue();
    }

    public static int V() {
        Object string = X().getString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(string)) {
            com.chartboost.sdk.Libraries.e.a k = com.chartboost.sdk.Libraries.e.a.k(string);
            if (k.c() && k.a("prefetchSession").c()) {
                return k.f("prefetchSession");
            }
        }
        return 3;
    }
}
