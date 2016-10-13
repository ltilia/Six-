package com.chartboost.sdk;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.c;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Libraries.k;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a.b;
import com.chartboost.sdk.Model.a.e;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.ae;
import com.chartboost.sdk.impl.au;
import com.chartboost.sdk.impl.ax;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.bb;
import com.chartboost.sdk.impl.m;
import com.google.android.gms.games.Games;

public final class Chartboost {
    protected static boolean a;
    protected static boolean b;
    protected static volatile Handler c;
    protected static k d;
    private static Chartboost e;
    private static CBImpressionActivity f;
    private static com.chartboost.sdk.Model.a g;
    private static ax h;
    private static az i;
    public static boolean isFirstHardBootup;
    private static m j;
    private static com.chartboost.sdk.Tracking.a k;
    private static boolean l;
    private static SparseBooleanArray m;
    private static f n;
    private static d o;
    private static boolean p;
    private static boolean q;
    private static ActivityLifecycleCallbacks r;
    private static boolean s;
    private static Runnable t;
    private static Runnable u;

    static class 10 implements Runnable {
        final /* synthetic */ String a;

        10(String str) {
            this.a = str;
        }

        public void run() {
            if (TextUtils.isEmpty(this.a)) {
                CBLogging.b("Chartboost", "cacheMoreApps location cannot be empty");
                if (c.h() != null) {
                    c.h().didFailToLoadMoreApps(this.a, CBImpressionError.INVALID_LOCATION);
                    return;
                }
                return;
            }
            au.i().b(this.a);
        }
    }

    static class 11 implements Runnable {
        final /* synthetic */ String a;

        11(String str) {
            this.a = str;
        }

        public void run() {
            if (TextUtils.isEmpty(this.a)) {
                CBLogging.b("Chartboost", "showMoreApps location cannot be empty");
                if (c.h() != null) {
                    c.h().didFailToLoadMoreApps(this.a, CBImpressionError.INVALID_LOCATION);
                    return;
                }
                return;
            }
            au.i().a(this.a);
        }
    }

    static class 12 implements Runnable {
        final /* synthetic */ Activity a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;

        12(Activity activity, String str, String str2) {
            this.a = activity;
            this.b = str;
            this.c = str2;
        }

        public void run() {
            if (Chartboost.e == null) {
                synchronized (Chartboost.class) {
                    if (Chartboost.e == null) {
                        if (this.a == null && !(this.a instanceof Activity)) {
                            CBLogging.b("Chartboost", "Activity object is null. Please pass a valid activity object");
                            return;
                        } else if (!c.b(this.a)) {
                            CBLogging.b("Chartboost", "Permissions not set correctly");
                            return;
                        } else if (!c.c(this.a)) {
                            CBLogging.b("Chartboost", "CBImpression Activity not added in your manifest.xml");
                            return;
                        } else if (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.c)) {
                            CBLogging.b("Chartboost", "AppId or AppSignature is null. Please pass a valid id's");
                            return;
                        } else {
                            Chartboost.e = new Chartboost(this.b, this.c, null);
                        }
                    }
                }
            }
        }
    }

    static class 13 implements Runnable {
        final /* synthetic */ CBMediation a;
        final /* synthetic */ String b;

        13(CBMediation cBMediation, String str) {
            this.a = cBMediation;
            this.b = str;
        }

        public void run() {
            c.a(this.a, this.b);
        }
    }

    static class 14 implements Runnable {
        final /* synthetic */ CBFramework a;

        14(CBFramework cBFramework) {
            this.a = cBFramework;
        }

        public void run() {
            c.a(this.a);
        }
    }

    static class 15 implements Runnable {
        final /* synthetic */ CBFramework a;
        final /* synthetic */ String b;

        15(CBFramework cBFramework, String str) {
            this.a = cBFramework;
            this.b = str;
        }

        public void run() {
            c.a(this.a, this.b);
        }
    }

    static class 16 implements Runnable {
        final /* synthetic */ String a;

        16(String str) {
            this.a = str;
        }

        public void run() {
            c.a(this.a);
        }
    }

    static class 17 implements Runnable {
        final /* synthetic */ String a;

        17(String str) {
            this.a = str;
        }

        public void run() {
            c.d(this.a);
        }
    }

    static class 18 implements Runnable {
        final /* synthetic */ Level a;

        18(Level level) {
            this.a = level;
        }

        public void run() {
            c.a(this.a);
        }
    }

    static class 19 implements Runnable {
        final /* synthetic */ ChartboostDelegate a;

        19(ChartboostDelegate chartboostDelegate) {
            this.a = chartboostDelegate;
        }

        public void run() {
            c.a(this.a);
        }
    }

    class 1 implements com.chartboost.sdk.c.a {
        final /* synthetic */ Chartboost a;

        1(Chartboost chartboost) {
            this.a = chartboost;
        }

        public void a() {
            ay ayVar = new ay("api/install");
            ayVar.a(true);
            ayVar.a(g.a(Games.EXTRA_STATUS, com.chartboost.sdk.Libraries.a.a));
            ayVar.t();
            Chartboost.q();
            Chartboost.isFirstHardBootup = false;
        }
    }

    static class 20 implements Runnable {
        final /* synthetic */ boolean a;

        20(boolean z) {
            this.a = z;
        }

        public void run() {
            c.a(this.a);
        }
    }

    static class 21 implements Runnable {
        final /* synthetic */ boolean a;

        21(boolean z) {
            this.a = z;
        }

        public void run() {
            c.e(this.a);
        }
    }

    static class 22 implements Runnable {
        final /* synthetic */ boolean a;

        22(boolean z) {
            this.a = z;
        }

        public void run() {
            c.f(this.a);
        }
    }

    static class 23 implements Runnable {
        final /* synthetic */ Activity a;

        23(Activity activity) {
            this.a = activity;
        }

        public void run() {
            Chartboost.k(this.a);
        }
    }

    static class 24 implements Runnable {
        final /* synthetic */ boolean a;

        24(boolean z) {
            this.a = z;
        }

        public void run() {
            c.g(this.a);
            boolean booleanValue = c.H().booleanValue();
            if (this.a && Chartboost.t()) {
                if (booleanValue) {
                    b.b();
                } else {
                    h.b();
                }
            } else if (booleanValue) {
                b.e();
            } else {
                h.e();
            }
        }
    }

    static class 25 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        25(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        public void run() {
            ad.i().b(this.a, this.b);
        }
    }

    static class 26 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        26(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        public void run() {
            au.i().b(this.a, this.b);
        }
    }

    static class 27 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        27(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        public void run() {
            ae.k().b(this.a, this.b);
        }
    }

    static class 28 implements Runnable {
        final /* synthetic */ boolean a;

        28(boolean z) {
            this.a = z;
        }

        public void run() {
            if (Chartboost.f == null) {
                return;
            }
            if (this.a) {
                Chartboost.f.forwardTouchEvents(Chartboost.getHostActivity());
            } else {
                Chartboost.f.forwardTouchEvents(null);
            }
        }
    }

    static class 29 implements ActivityLifecycleCallbacks {
        29() {
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            CBLogging.a("Chartboost", "######## onActivityCreated callback called");
            if (!(activity instanceof CBImpressionActivity)) {
                Chartboost.j(activity);
            }
        }

        public void onActivityStarted(Activity activity) {
            if (activity instanceof CBImpressionActivity) {
                CBLogging.a("Chartboost", "######## onActivityStarted callback called from CBImpressionactivity");
                Chartboost.a(activity);
                return;
            }
            CBLogging.a("Chartboost", "######## onActivityStarted callback called from developer side");
            Chartboost.l(activity);
        }

        public void onActivityResumed(Activity activity) {
            if (activity instanceof CBImpressionActivity) {
                CBLogging.a("Chartboost", "######## onActivityResumed callback called from CBImpressionactivity");
                Chartboost.a(k.a(activity));
                return;
            }
            CBLogging.a("Chartboost", "######## onActivityResumed callback called from developer side");
            Chartboost.m(activity);
        }

        public void onActivityPaused(Activity activity) {
            if (activity instanceof CBImpressionActivity) {
                CBLogging.a("Chartboost", "######## onActivityPaused callback called from CBImpressionactivity");
                Chartboost.b(k.a(activity));
                return;
            }
            CBLogging.a("Chartboost", "######## onActivityPaused callback called from developer side");
            Chartboost.n(activity);
        }

        public void onActivityStopped(Activity activity) {
            if (activity instanceof CBImpressionActivity) {
                CBLogging.a("Chartboost", "######## onActivityStopped callback called from CBImpressionactivity");
                Chartboost.c(k.a(activity));
                return;
            }
            CBLogging.a("Chartboost", "######## onActivityStopped callback called from developer side");
            Chartboost.o(activity);
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityDestroyed(Activity activity) {
            if (activity instanceof CBImpressionActivity) {
                CBLogging.a("Chartboost", "######## onActivityDestroyed callback called from CBImpressionactivity");
                Chartboost.b(activity);
                return;
            }
            CBLogging.a("Chartboost", "######## onActivityDestroyed callback called from developer side");
            Chartboost.p(activity);
        }
    }

    static class 2 implements Runnable {
        final /* synthetic */ d a;

        2(d dVar) {
            this.a = dVar;
        }

        public void run() {
            this.a.b();
        }
    }

    static class 30 implements Runnable {
        final /* synthetic */ Activity a;

        30(Activity activity) {
            this.a = activity;
        }

        public void run() {
            Chartboost.c.removeCallbacks(Chartboost.t);
            if (!(Chartboost.d == null || Chartboost.d.b(this.a) || !Chartboost.s())) {
                Chartboost.f(Chartboost.d);
                Chartboost.c(Chartboost.d, false);
            }
            Chartboost.b(this.a, true);
            Chartboost.d = k.a(this.a);
            Chartboost.a();
            if (c.d) {
                c.b(this.a);
            }
            if (!Chartboost.isFirstHardBootup) {
                f a = f.a();
                if (!(a == null || a.d())) {
                    b.g();
                }
                b.a(h.b());
                ad.i().d();
                ae.k().d();
            }
            Chartboost.a(this.a);
        }
    }

    static class 31 implements Runnable {
        31() {
        }

        public void run() {
            Chartboost.c();
        }
    }

    static class 32 implements com.chartboost.sdk.c.a {
        32() {
        }

        public void a() {
            ay ayVar = new ay("api/install");
            ayVar.a(true);
            ayVar.a(g.a(Games.EXTRA_STATUS, com.chartboost.sdk.Libraries.a.a));
            ayVar.t();
            Chartboost.q();
        }
    }

    static class 33 implements Runnable {
        final /* synthetic */ Activity a;

        33(Activity activity) {
            this.a = activity;
        }

        public void run() {
            k a = k.a(this.a);
            if (Chartboost.d(a)) {
                Chartboost.a(a);
            } else if (CBUtility.a(CBFramework.CBFrameworkUnity)) {
                Chartboost.a();
            }
        }
    }

    static class 34 implements Runnable {
        final /* synthetic */ Activity a;

        34(Activity activity) {
            this.a = activity;
        }

        public void run() {
            k a = k.a(this.a);
            if (Chartboost.d(a)) {
                Chartboost.b(a);
            }
        }
    }

    static class 35 implements Runnable {
        final /* synthetic */ Activity a;

        35(Activity activity) {
            this.a = activity;
        }

        public void run() {
            k a = k.a(this.a);
            if (Chartboost.d(a)) {
                Chartboost.f(a);
            }
        }
    }

    static class 3 implements Runnable {
        final /* synthetic */ f a;
        final /* synthetic */ d b;

        3(f fVar, d dVar) {
            this.a = fVar;
            this.b = dVar;
        }

        public void run() {
            this.a.a(this.b.c(), true);
        }
    }

    static class 4 implements Runnable {
        final /* synthetic */ Activity a;

        4(Activity activity) {
            this.a = activity;
        }

        public void run() {
            if (Chartboost.d == null || Chartboost.d.b(this.a)) {
                Chartboost.t = new a();
                Chartboost.t.run();
            }
            Chartboost.b(this.a);
        }
    }

    static class 5 implements Runnable {
        final /* synthetic */ String a;

        5(String str) {
            this.a = str;
        }

        public void run() {
            if (TextUtils.isEmpty(this.a)) {
                CBLogging.b("Chartboost", "cacheRewardedVideo location cannot be empty");
                if (c.h() != null) {
                    c.h().didFailToLoadRewardedVideo(this.a, CBImpressionError.INVALID_LOCATION);
                    return;
                }
                return;
            }
            ae.k().b(this.a);
        }
    }

    static class 6 implements Runnable {
        final /* synthetic */ String a;

        6(String str) {
            this.a = str;
        }

        public void run() {
            if (TextUtils.isEmpty(this.a)) {
                CBLogging.b("Chartboost", "showRewardedVideo location cannot be empty");
                if (c.h() != null) {
                    c.h().didFailToLoadRewardedVideo(this.a, CBImpressionError.INVALID_LOCATION);
                    return;
                }
                return;
            }
            ae.k().a(this.a);
        }
    }

    static class 7 implements Runnable {
        final /* synthetic */ String a;

        7(String str) {
            this.a = str;
        }

        public void run() {
            if (TextUtils.isEmpty(this.a)) {
                CBLogging.b("Chartboost", "cacheInterstitial location cannot be empty");
                if (c.h() != null) {
                    c.h().didFailToLoadInterstitial(this.a, CBImpressionError.INVALID_LOCATION);
                    return;
                }
                return;
            }
            ad.i().b(this.a);
        }
    }

    static class 8 implements Runnable {
        final /* synthetic */ String a;

        8(String str) {
            this.a = str;
        }

        public void run() {
            if (TextUtils.isEmpty(this.a)) {
                CBLogging.b("Chartboost", "showInterstitial location cannot be empty");
                if (c.h() != null) {
                    c.h().didFailToLoadInterstitial(this.a, CBImpressionError.INVALID_LOCATION);
                    return;
                }
                return;
            }
            ad.i().a(this.a);
        }
    }

    static class 9 implements Runnable {
        9() {
        }

        public void run() {
            if (c.r()) {
                Chartboost.e();
            }
        }
    }

    public enum CBFramework {
        CBFrameworkUnity("Unity"),
        CBFrameworkCorona("Corona"),
        CBFrameworkAir("AIR"),
        CBFrameworkGameSalad("GameSalad"),
        CBFrameworkCordova("Cordova"),
        CBFrameworkCocoonJS("CocoonJS"),
        CBFrameworkCocos2dx("Cocos2dx"),
        CBFrameworkPrime31Unreal("Prime31Unreal"),
        CBFrameworkWeeby("Weeby"),
        CBFrameworkOther("Other");
        
        private final String a;

        private CBFramework(String s) {
            this.a = s;
        }

        public String toString() {
            return this.a;
        }

        public boolean doesWrapperUseCustomShouldDisplayBehavior() {
            return this == CBFrameworkAir || this == CBFrameworkCocos2dx;
        }

        public boolean doesWrapperUseCustomBackgroundingBehavior() {
            return this == CBFrameworkAir;
        }
    }

    public enum CBMediation {
        CBMediationAdMarvel("AdMarvel"),
        CBMediationAdMob("AdMob"),
        CBMediationFuse("Fuse"),
        CBMediationFyber("Fyber"),
        CBMediationHeyZap("HeyZap"),
        CBMediationMoPub("MoPub"),
        CBMediationSupersonic("Supersonic"),
        CBMediationOther("Other");
        
        private final String a;

        private CBMediation(String s) {
            this.a = s;
        }

        public String toString() {
            return this.a;
        }
    }

    private static class a implements Runnable {
        private int a;
        private int b;
        private int c;

        private a a() {
            return c.h();
        }

        private a() {
            int i = -1;
            a a = a();
            this.a = Chartboost.f == null ? -1 : Chartboost.f.hashCode();
            this.b = Chartboost.d == null ? -1 : Chartboost.d.hashCode();
            if (a != null) {
                i = a.hashCode();
            }
            this.c = i;
        }

        public void run() {
            a a = a();
            if (Chartboost.d != null && Chartboost.d.hashCode() == this.b) {
                Chartboost.d = null;
            }
            if (a != null && a.hashCode() == this.c) {
                c.a(null);
            }
        }
    }

    static {
        boolean z = true;
        e = null;
        f = null;
        g = null;
        h = null;
        i = null;
        j = null;
        k = null;
        l = false;
        m = new SparseBooleanArray();
        n = null;
        o = null;
        a = true;
        b = false;
        isFirstHardBootup = true;
        p = false;
        if (VERSION.SDK_INT < 14) {
            z = false;
        }
        q = z;
        s = false;
        c = new Handler(Looper.getMainLooper());
        d = null;
        u = new 31();
        if (q) {
            r = new 29();
        }
    }

    private Chartboost(Activity app, String appId, String appSignature) {
        e = this;
        CBUtility.a(c);
        c.a(app.getApplication());
        if (i()) {
            app.getApplication().registerActivityLifecycleCallbacks(r);
        }
        c.a(app.getApplicationContext());
        c.b(appId);
        c.c(appSignature);
        d = k.a(app);
        h = ax.a();
        n = f.a();
        i = az.a(c.y());
        j = i.a();
        o = d.a();
        k = com.chartboost.sdk.Tracking.a.a();
        h.a(c.y());
        b.a();
        b.g();
        h.a();
        t = new a();
        c.a();
        c.b(true);
        c.a(new 1(this));
    }

    public static void startWithAppId(Activity activity, String appId, String appSignature) {
        a(new 12(activity, appId, appSignature));
    }

    public static void onCreate(Activity activity) {
        if (!i()) {
            j(activity);
        }
    }

    private static void j(Activity activity) {
        if (c.t() && c.a(activity)) {
            a(new 23(activity));
        }
    }

    private static void k(Activity activity) {
        if (!(d == null || d.b(activity) || !s())) {
            f(d);
            c(d, false);
        }
        c.removeCallbacks(t);
        d = k.a(activity);
        az.a((Context) activity).d();
    }

    public static void onStart(Activity activity) {
        if (!i()) {
            l(activity);
        }
    }

    private static void l(Activity activity) {
        if (c.t() && c.a(activity)) {
            a(new 30(activity));
        }
    }

    protected static void a(Activity activity) {
        boolean z;
        h.b(c.y());
        if (!(activity instanceof CBImpressionActivity)) {
            j.a();
            i.e();
        }
        c.a(activity.getApplicationContext());
        if (activity instanceof CBImpressionActivity) {
            a((CBImpressionActivity) activity);
        } else {
            d = k.a(activity);
            c(d, true);
        }
        c.removeCallbacks(t);
        if (c.b() == null || !c.b().doesWrapperUseCustomBackgroundingBehavior()) {
            z = false;
        } else {
            z = true;
        }
        if (activity == null) {
            return;
        }
        if (z || q(activity)) {
            b(k.a(activity), true);
            if (activity instanceof CBImpressionActivity) {
                s = false;
            }
            if (o.a(activity, g)) {
                g = null;
            }
            com.chartboost.sdk.Model.a c = o.c();
            if (c != null) {
                c.y();
            }
        }
    }

    protected static void a() {
        if (c.y() == null) {
            CBLogging.b("Chartboost", "The context must be set through the Chartboost method onCreate() before calling startSession().");
        } else {
            p();
        }
    }

    protected static void b() {
        if (c.i()) {
            c.postDelayed(u, 500);
        } else {
            c();
        }
    }

    private static void p() {
        if (k == null) {
            k = com.chartboost.sdk.Tracking.a.a();
        }
        k.f();
        com.chartboost.sdk.Tracking.a.b();
        if (!isFirstHardBootup) {
            c.a(new 32());
        }
    }

    private static void q() {
        c.C();
        try {
            if (c.H().booleanValue()) {
                b.b();
            } else if (c.N()) {
                h.b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void c() {
        if (k == null) {
            k = com.chartboost.sdk.Tracking.a.a();
        }
        k.c();
    }

    public static void onResume(Activity activity) {
        if (!i()) {
            m(activity);
        }
    }

    private static void m(Activity activity) {
        if (c.t() && c.a(activity)) {
            if (!b) {
                if (c.h() != null) {
                    c.h().didInitialize();
                }
                b = true;
            }
            a(new 33(activity));
        }
    }

    protected static void a(k kVar) {
        com.chartboost.sdk.Model.a c = d.a().c();
        if (CBUtility.a(CBFramework.CBFrameworkUnity)) {
            a();
        }
        if (c != null) {
            c.x();
        }
    }

    public static void onPause(Activity activity) {
        if (!i()) {
            n(activity);
        }
    }

    private static void n(Activity activity) {
        if (c.t() && c.a(activity)) {
            a(new 34(activity));
        }
    }

    protected static void b(k kVar) {
        com.chartboost.sdk.Model.a c = d.a().c();
        if (c != null) {
            c.z();
        }
    }

    public static void onStop(Activity activity) {
        if (!i()) {
            o(activity);
        }
    }

    private static void o(Activity activity) {
        if (c.t() && c.a(activity)) {
            a(new 35(activity));
        }
    }

    private static void f(k kVar) {
        if (!c.i()) {
            c(kVar);
        }
        if (!(kVar.get() instanceof CBImpressionActivity)) {
            c(kVar, false);
        }
        b();
    }

    protected static void c(k kVar) {
        com.chartboost.sdk.Model.a c = d.a().c();
        if (c != null && c.a == b.NATIVE) {
            f h = h();
            if (g(kVar) && h != null) {
                if (c != null) {
                    h.c(c);
                    g = c;
                }
                b(kVar, false);
            }
            if (!(kVar.get() instanceof CBImpressionActivity)) {
                c(kVar, false);
            }
        }
        h.c(c.y());
        if (!(kVar.get() instanceof CBImpressionActivity)) {
            j.b();
            i.f();
        }
    }

    public static boolean onBackPressed() {
        return r();
    }

    private static boolean r() {
        if (!c.t()) {
            return false;
        }
        if (d == null) {
            CBLogging.b("Chartboost", "The Chartboost methods onCreate(), onStart(), onStop(), and onDestroy() must be called in the corresponding methods of your activity in order for Chartboost to function properly.");
            return false;
        } else if (!c.i()) {
            return d();
        } else {
            if (!s) {
                return false;
            }
            s = false;
            d();
            return true;
        }
    }

    protected static boolean d() {
        return e();
    }

    protected static boolean e() {
        d a = d.a();
        com.chartboost.sdk.Model.a c = a.c();
        if (c == null || c.c != e.DISPLAYED) {
            f h = h();
            if (h == null || !h.c()) {
                return false;
            }
            a(new 3(h, a));
            return true;
        } else if (c.w()) {
            return true;
        } else {
            a(new 2(a));
            return true;
        }
    }

    public static void onDestroy(Activity activity) {
        if (!i()) {
            p(activity);
        }
    }

    private static void p(Activity activity) {
        if (c.t() && c.a(activity)) {
            a(new 4(activity));
        }
    }

    protected static void b(Activity activity) {
        b(k.a(activity), false);
        com.chartboost.sdk.Model.a c = d.a().c();
        f h = h();
        if (!(h == null || c == null)) {
            h.d(c);
        }
        g = null;
    }

    public static void didPassAgeGate(boolean pass) {
        c.c(pass);
    }

    public static void setShouldPauseClickForConfirmation(boolean shouldPause) {
        c.d(shouldPause);
    }

    public static void clearCache() {
        if (c.r()) {
            bb.a().b();
            ae.k().a();
            ad.i().a();
            au.i().a();
            com.chartboost.sdk.InPlay.a.b();
        }
    }

    public static boolean hasRewardedVideo(String location) {
        if (c.r()) {
            return ae.k().d(location);
        }
        return false;
    }

    public static void cacheRewardedVideo(String location) {
        if (c.r() && t()) {
            Runnable 5 = new 5(location);
            if (c.H().booleanValue() && c.J()) {
                a(5);
            } else if (c.N() && c.P()) {
                a(5);
            } else {
                c.h().didFailToLoadRewardedVideo(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    public static void showRewardedVideo(String location) {
        if (c.r() && t()) {
            Runnable 6 = new 6(location);
            if (c.H().booleanValue() && c.J()) {
                a(6);
            } else if (c.N() && c.P()) {
                a(6);
            } else {
                c.h().didFailToLoadRewardedVideo(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    public static boolean hasInterstitial(String location) {
        if (c.r()) {
            return ad.i().d(location);
        }
        return false;
    }

    public static void cacheInterstitial(String location) {
        if (c.r() && t()) {
            Runnable 7 = new 7(location);
            if (c.H().booleanValue() && c.I()) {
                a(7);
            } else if (c.N() && c.O()) {
                a(7);
            } else {
                c.h().didFailToLoadInterstitial(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    public static void showInterstitial(String location) {
        if (c.r() && t()) {
            Runnable 8 = new 8(location);
            if (c.H().booleanValue() && c.I()) {
                a(8);
            } else if (c.N() && c.O()) {
                a(8);
            } else {
                c.h().didFailToLoadInterstitial(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    public static void closeImpression() {
        a(new 9());
    }

    public static boolean hasMoreApps(String location) {
        if (c.r()) {
            return au.i().d(location);
        }
        return false;
    }

    public static void cacheMoreApps(String location) {
        if (c.r() && t()) {
            Runnable 10 = new 10(location);
            if (c.H().booleanValue() && c.K()) {
                a(10);
            } else if (c.N() && c.Q()) {
                a(10);
            } else {
                c.h().didFailToLoadMoreApps(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    public static void showMoreApps(String location) {
        if (c.r() && t()) {
            Runnable 11 = new 11(location);
            if (c.H().booleanValue() && c.K()) {
                a(11);
            } else if (c.N() && c.Q()) {
                a(11);
            } else {
                c.h().didFailToLoadMoreApps(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    public static boolean isAnyViewVisible() {
        f h = h();
        if (h == null) {
            return false;
        }
        return h.d();
    }

    public static void setMediation(CBMediation mediation, String libraryVersion) {
        a(new 13(mediation, libraryVersion));
    }

    public static void setFramework(CBFramework framework) {
        a(new 14(framework));
    }

    public static void setFramework(CBFramework framework, String version) {
        a(new 15(framework, version));
    }

    public static void setFrameworkVersion(String version) {
        a(new 16(version));
    }

    public static String getCustomId() {
        return c.p();
    }

    public static void setCustomId(String customID) {
        a(new 17(customID));
    }

    public static void setLoggingLevel(Level lvl) {
        a(new 18(lvl));
    }

    public static Level getLoggingLevel() {
        return c.o();
    }

    public static a getDelegate() {
        return c.h();
    }

    public static void setDelegate(ChartboostDelegate delegate) {
        a(new 19(delegate));
    }

    public static boolean getAutoCacheAds() {
        return c.k();
    }

    public static void setAutoCacheAds(boolean autoCacheAds) {
        a(new 20(autoCacheAds));
    }

    public static void setShouldRequestInterstitialsInFirstSession(boolean shouldRequest) {
        a(new 21(shouldRequest));
    }

    public static void setShouldDisplayLoadingViewForMoreApps(boolean shouldDisplay) {
        a(new 22(shouldDisplay));
    }

    public static void setShouldPrefetchVideoContent(boolean shouldPrefetch) {
        if (c.r()) {
            a(new 24(shouldPrefetch));
        }
    }

    public static String getSDKVersion() {
        return "6.4.1";
    }

    public static void setShouldHideSystemUI(Boolean hide) {
        c.a(hide);
    }

    public static boolean isWebViewEnabled() {
        return c.H().booleanValue();
    }

    public static void setActivityCallbacks(boolean enabled) {
        Activity hostActivity = getHostActivity();
        if (hostActivity == null) {
            return;
        }
        if (q && !p && enabled) {
            hostActivity.getApplication().registerActivityLifecycleCallbacks(r);
            p = enabled;
        } else if (p && !enabled) {
            hostActivity.getApplication().unregisterActivityLifecycleCallbacks(r);
            p = enabled;
        }
    }

    protected static Activity f() {
        if (c.i()) {
            return f;
        }
        return getHostActivity();
    }

    private static boolean q(Activity activity) {
        if (c.i()) {
            if (f == activity) {
                return true;
            }
            return false;
        } else if (d != null) {
            return d.b(activity);
        } else {
            if (activity != null) {
                return false;
            }
            return true;
        }
    }

    private static boolean g(k kVar) {
        if (c.i()) {
            if (kVar != null) {
                return kVar.b(f);
            }
            if (f == null) {
                return true;
            }
            return false;
        } else if (d != null) {
            return d.a(kVar);
        } else {
            if (kVar != null) {
                return false;
            }
            return true;
        }
    }

    protected static void a(CBImpressionActivity cBImpressionActivity) {
        if (!l) {
            c.a(cBImpressionActivity.getApplicationContext());
            f = cBImpressionActivity;
            l = true;
        }
        c.removeCallbacks(t);
    }

    protected static void g() {
        if (l) {
            f = null;
            l = false;
        }
    }

    protected static void a(com.chartboost.sdk.Model.a aVar) {
        boolean z = true;
        f h = h();
        if (h != null && h.d()) {
            aVar.a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
        } else if (!c.i()) {
            h = h();
            if (h == null || !s()) {
                aVar.a(CBImpressionError.NO_HOST_ACTIVITY);
            } else {
                h.a(aVar);
            }
        } else if (l) {
            if (f() == null || h == null) {
                if (f() == null) {
                    CBLogging.b("Chartboost", "Activity not found. Cannot display the view");
                    aVar.a(CBImpressionError.NO_HOST_ACTIVITY);
                    return;
                }
                CBLogging.b("Chartboost", "Missing view controller to manage the impression activity");
                aVar.a(CBImpressionError.ERROR_DISPLAYING_VIEW);
            } else if (aVar.a == b.WEB) {
                g B = aVar.B();
                if (B != null) {
                    CBImpressionError c = B.c();
                    if (c != null) {
                        CBLogging.b("Chartboost", "Unable to create the view while trying th display the impression");
                        aVar.a(c);
                        h = f.a();
                        if (h != null) {
                            h.d(aVar);
                        }
                    }
                }
            } else {
                h.a(aVar);
            }
        } else if (s()) {
            Context hostActivity = getHostActivity();
            if (hostActivity == null) {
                CBLogging.b("Chartboost", "Failed to display impression as the host activity reference has been lost!");
                aVar.a(CBImpressionError.NO_HOST_ACTIVITY);
            } else if (g == null || g == aVar) {
                g = aVar;
                Intent intent = new Intent(hostActivity, CBImpressionActivity.class);
                boolean z2 = (hostActivity.getWindow().getAttributes().flags & AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT) != 0;
                boolean z3;
                if ((hostActivity.getWindow().getAttributes().flags & AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT) != 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                String str = "paramFullscreen";
                if (!z2 || r3) {
                    z = false;
                }
                intent.putExtra(str, z);
                try {
                    hostActivity.startActivity(intent);
                    s = true;
                } catch (ActivityNotFoundException e) {
                    CBLogging.b("Chartboost", "Chartboost impression activity not declared in manifest. Please add the following inside your manifest's <application> tag: \n<activity android:name=\"com.chartboost.sdk.CBImpressionActivity\" android:theme=\"@android:style/Theme.Translucent.NoTitleBar\" android:excludeFromRecents=\"true\" />");
                }
            } else {
                aVar.a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
            }
        } else {
            aVar.a(CBImpressionError.NO_HOST_ACTIVITY);
        }
    }

    protected static Activity getHostActivity() {
        return d != null ? (Activity) d.get() : null;
    }

    protected static void a(Runnable runnable) {
        if (CBUtility.b()) {
            runnable.run();
        } else {
            c.post(runnable);
        }
    }

    protected static Context getValidContext() {
        return d != null ? d.b() : c.y();
    }

    private static void b(k kVar, boolean z) {
    }

    private static boolean s() {
        return d(d);
    }

    protected static boolean d(k kVar) {
        if (kVar == null) {
            return false;
        }
        Boolean valueOf = Boolean.valueOf(m.get(kVar.a()));
        if (valueOf != null) {
            return valueOf.booleanValue();
        }
        return false;
    }

    private static void b(Activity activity, boolean z) {
        if (activity != null) {
            a(activity.hashCode(), z);
        }
    }

    private static void c(k kVar, boolean z) {
        if (kVar != null) {
            a(kVar.a(), z);
        }
    }

    private static void a(int i, boolean z) {
        m.put(i, z);
    }

    protected static f h() {
        if (f() == null) {
            return null;
        }
        return n;
    }

    public static boolean getImpressionsUseActivities() {
        return c.i();
    }

    public static void setImpressionsUseActivities(boolean impressionsUseActivities) {
        c.a = impressionsUseActivities;
    }

    private static boolean t() {
        if (!c.T().booleanValue()) {
            return true;
        }
        try {
            throw new Exception("Chartboost Integration Warning: your account has been set to advertiser only. This function has been disabled. Please contact support if you expect this call to function.");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void showInterstitialAIR(String location, boolean show) {
        if (c.r() && t()) {
            Runnable 25 = new 25(location, show);
            if (c.H().booleanValue() && c.I()) {
                a(25);
            } else if (c.N() && c.O()) {
                a(25);
            } else {
                c.h().didFailToLoadInterstitial(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    private static void showMoreAppsAIR(String location, boolean show) {
        if (c.r() && t()) {
            Runnable 26 = new 26(location, show);
            if (c.H().booleanValue() && c.K()) {
                a(26);
            } else if (c.N() && c.Q()) {
                a(26);
            } else {
                c.h().didFailToLoadMoreApps(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    private static void showRewardedVideoAIR(String location, boolean show) {
        if (c.r() && t()) {
            Runnable 27 = new 27(location, show);
            if (c.H().booleanValue() && c.J()) {
                a(27);
            } else if (c.N() && c.P()) {
                a(27);
            } else {
                c.h().didFailToLoadRewardedVideo(location, CBImpressionError.END_POINT_DISABLED);
            }
        }
    }

    private static void forwardTouchEventsAIR(boolean forward) {
        a(new 28(forward));
    }

    protected static boolean i() {
        return q && p;
    }
}
