package com.chartboost.sdk;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a.b;
import com.chartboost.sdk.impl.ax;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.ay.c;
import com.chartboost.sdk.impl.ay.d;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.games.Games;
import com.mopub.mobileads.GooglePlayServicesInterstitial;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public abstract class e {
    protected static Handler a;
    public ConcurrentHashMap<String, com.chartboost.sdk.Model.a> b;
    public b c;
    private Map<String, com.chartboost.sdk.Model.a> d;
    private ConcurrentHashMap<String, com.chartboost.sdk.Model.a> e;
    private ConcurrentHashMap<String, com.chartboost.sdk.Model.a> f;
    private a g;

    class 1 implements Runnable {
        final /* synthetic */ com.chartboost.sdk.Model.a a;
        final /* synthetic */ com.chartboost.sdk.Model.a b;
        final /* synthetic */ com.chartboost.sdk.Model.a c;
        final /* synthetic */ e d;

        1(e eVar, com.chartboost.sdk.Model.a aVar, com.chartboost.sdk.Model.a aVar2, com.chartboost.sdk.Model.a aVar3) {
            this.d = eVar;
            this.a = aVar;
            this.b = aVar2;
            this.c = aVar3;
        }

        public void run() {
            if (this.a != null) {
                if (this.a.c == com.chartboost.sdk.Model.a.e.NONE) {
                    this.a.c = com.chartboost.sdk.Model.a.e.CACHED;
                }
                this.d.g(this.a);
            } else if (this.b == null || !this.b.A().c()) {
                this.d.c(this.c);
            } else {
                this.b.a(this.b.A(), d.a().a);
            }
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ com.chartboost.sdk.Model.a a;
        final /* synthetic */ CBImpressionError b;
        final /* synthetic */ e c;

        2(e eVar, com.chartboost.sdk.Model.a aVar, CBImpressionError cBImpressionError) {
            this.c = eVar;
            this.a = aVar;
            this.b = cBImpressionError;
        }

        public void run() {
            this.c.n(this.a);
            f h = Chartboost.h();
            if (h != null && h.c()) {
                h.a(this.a, true);
            } else if (this.a.c == com.chartboost.sdk.Model.a.e.DISPLAYED && h != null) {
                h.b(this.a);
            }
            this.c.b().a(this.a, this.b);
            String t = this.a.t();
            String f = this.a.u().f();
            String str = this.a.e;
            if (TextUtils.isEmpty(t)) {
                t = BuildConfig.FLAVOR;
            }
            com.chartboost.sdk.Tracking.a.a(f, str, t, this.b);
        }
    }

    class 3 extends d {
        final /* synthetic */ com.chartboost.sdk.Model.a a;
        final /* synthetic */ e b;

        3(e eVar, com.chartboost.sdk.Model.a aVar) {
            this.b = eVar;
            this.a = aVar;
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar) {
            if (c.k() && !this.b.d(this.a.e)) {
                this.b.b(this.a.e);
            }
        }
    }

    class 4 implements c {
        final /* synthetic */ com.chartboost.sdk.Model.a a;
        final /* synthetic */ e b;

        class 1 implements Runnable {
            final /* synthetic */ com.chartboost.sdk.Libraries.e.a a;
            final /* synthetic */ 4 b;

            1(4 4, com.chartboost.sdk.Libraries.e.a aVar) {
                this.b = 4;
                this.a = aVar;
            }

            public void run() {
                try {
                    if (this.a.c()) {
                        this.b.a.v = false;
                        Object e = this.a.e(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
                        if (TextUtils.isEmpty(e) || !e.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                            this.b.b.a(this.b.a, b.WEB);
                        } else {
                            this.b.b.a(this.b.a, b.NATIVE);
                        }
                        this.b.b.a(this.b.a, this.a);
                        return;
                    }
                    this.b.b.a(this.b.a, CBImpressionError.INVALID_RESPONSE);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        class 2 implements Runnable {
            final /* synthetic */ ay a;
            final /* synthetic */ CBError b;
            final /* synthetic */ 4 c;

            2(4 4, ay ayVar, CBError cBError) {
                this.c = 4;
                this.a = ayVar;
                this.b = cBError;
            }

            public void run() {
                this.c.a.v = false;
                String str = "network failure";
                String str2 = "request %s failed with error %s: %s";
                Object[] objArr = new Object[3];
                objArr[0] = this.a.h();
                objArr[1] = this.b.a().name();
                objArr[2] = this.b.b() != null ? this.b.b() : BuildConfig.FLAVOR;
                CBLogging.d(str, String.format(str2, objArr));
                this.c.b.a(this.c.a, this.b.c());
            }
        }

        4(e eVar, com.chartboost.sdk.Model.a aVar) {
            this.b = eVar;
            this.a = aVar;
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar) {
            Chartboost.a(new 1(this, aVar));
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, CBError cBError) {
            Chartboost.a(new 2(this, ayVar, cBError));
        }
    }

    protected interface a {
        void a(com.chartboost.sdk.Model.a aVar);

        void a(com.chartboost.sdk.Model.a aVar, CBImpressionError cBImpressionError);

        void b(com.chartboost.sdk.Model.a aVar);

        void c(com.chartboost.sdk.Model.a aVar);

        void d(com.chartboost.sdk.Model.a aVar);

        void e(com.chartboost.sdk.Model.a aVar);

        boolean f(com.chartboost.sdk.Model.a aVar);

        boolean g(com.chartboost.sdk.Model.a aVar);

        boolean h(com.chartboost.sdk.Model.a aVar);
    }

    protected abstract com.chartboost.sdk.Model.a a(String str, boolean z);

    protected abstract a c();

    protected abstract ay e(com.chartboost.sdk.Model.a aVar);

    public abstract String f();

    protected abstract ay l(com.chartboost.sdk.Model.a aVar);

    static {
        a = CBUtility.e();
    }

    public e() {
        this.c = b.NATIVE;
        this.g = null;
        this.e = new ConcurrentHashMap();
        this.b = new ConcurrentHashMap();
        this.d = new HashMap();
        this.f = new ConcurrentHashMap();
    }

    public void a(String str) {
        if (!g(str)) {
            com.chartboost.sdk.Model.a a = a(str, false);
            f h = Chartboost.h();
            if (h == null || !h.d()) {
                if (!b(a)) {
                    a.post(new 1(this, (com.chartboost.sdk.Model.a) this.e.get(str), (com.chartboost.sdk.Model.a) this.b.get(str), a));
                }
            } else if (b() != null) {
                b().a(a, CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
            }
        }
    }

    public void b(String str) {
        if (!g(str)) {
            com.chartboost.sdk.Model.a aVar = (com.chartboost.sdk.Model.a) this.e.get(str);
            if (aVar != null) {
                b().d(aVar);
                return;
            }
            aVar = (com.chartboost.sdk.Model.a) this.b.get(str);
            if (aVar != null) {
                b().d(aVar);
                return;
            }
            aVar = a(str, true);
            if (!b(aVar)) {
                c(aVar);
            }
        }
    }

    protected void a(com.chartboost.sdk.Model.a aVar) {
        p(aVar);
        b().d(aVar);
        aVar.c = com.chartboost.sdk.Model.a.e.CACHED;
    }

    protected final boolean b(com.chartboost.sdk.Model.a aVar) {
        if (b().h(aVar) || CBUtility.a().getInt("cbPrefSessionCount", 0) != 1) {
            return false;
        }
        a(aVar, CBImpressionError.FIRST_SESSION_INTERSTITIALS_DISABLED);
        return true;
    }

    protected void c(com.chartboost.sdk.Model.a aVar) {
        if (f(aVar) && b().g(aVar)) {
            if (!aVar.j && aVar.d == com.chartboost.sdk.Model.a.a.MORE_APPS && c.w()) {
                aVar.m = true;
                Chartboost.a(aVar);
            }
            if (d(aVar)) {
                ay e = e(aVar);
                if (e != null) {
                    a(e, aVar);
                    o(aVar);
                    com.chartboost.sdk.Tracking.a.a(f(), aVar.e, aVar.t(), aVar.j);
                }
            }
        }
    }

    protected boolean d(com.chartboost.sdk.Model.a aVar) {
        return true;
    }

    private final synchronized boolean g(String str) {
        boolean z = true;
        synchronized (this) {
            com.chartboost.sdk.Model.a c = c(str);
            if (c == null) {
                z = false;
            } else if (b.f()) {
                CBLogging.b(getClass().getSimpleName(), "Some downloads are in progress");
                if (b.a.contains(c)) {
                    CBLogging.b(getClass().getSimpleName(), String.format("%s %s", new Object[]{"Request already in process for impression with location", str}));
                } else {
                    z = false;
                }
            } else if (b.a.contains(c)) {
                CBLogging.b(getClass().getSimpleName(), String.format("%s %s", new Object[]{"Request already in process for impression with location", str}));
            } else {
                b.a.remove(c);
                z = false;
            }
        }
        return z;
    }

    protected void a(com.chartboost.sdk.Model.a aVar, CBImpressionError cBImpressionError) {
        Chartboost.a(new 2(this, aVar, cBImpressionError));
    }

    protected final boolean f(com.chartboost.sdk.Model.a aVar) {
        if (c.q()) {
            f h = Chartboost.h();
            if (aVar.j || h == null || !h.d()) {
                if (ax.a().c()) {
                    return true;
                }
                a(aVar, CBImpressionError.INTERNET_UNAVAILABLE);
                return false;
            } else if (b() == null) {
                return false;
            } else {
                b().a(aVar, CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
                return false;
            }
        }
        a(aVar, CBImpressionError.SESSION_NOT_STARTED);
        return false;
    }

    protected void g(com.chartboost.sdk.Model.a aVar) {
        boolean z = aVar.c != com.chartboost.sdk.Model.a.e.DISPLAYED;
        if (z) {
            if (c.b() != null && c.b().doesWrapperUseCustomShouldDisplayBehavior()) {
                this.f.put(aVar.e == null ? BuildConfig.FLAVOR : aVar.e, aVar);
            }
            if (!b().f(aVar)) {
                return;
            }
        }
        a(aVar, z);
    }

    protected void b(String str, boolean z) {
        if (str == null) {
            str = BuildConfig.FLAVOR;
        }
        com.chartboost.sdk.Model.a aVar = (com.chartboost.sdk.Model.a) this.f.get(str);
        if (aVar != null) {
            this.f.remove(str);
            if (z) {
                a(aVar, true);
            }
        }
    }

    private void a(com.chartboost.sdk.Model.a aVar, boolean z) {
        boolean z2 = aVar.c == com.chartboost.sdk.Model.a.e.CACHED;
        i(aVar);
        f h = Chartboost.h();
        if (h != null) {
            if (h.c()) {
                h.a(aVar, false);
            } else if (!(!aVar.m || z2 || aVar.c == com.chartboost.sdk.Model.a.e.DISPLAYED)) {
                return;
            }
        }
        if (z) {
            h(aVar);
        } else {
            Chartboost.a(aVar);
        }
    }

    protected void h(com.chartboost.sdk.Model.a aVar) {
        Chartboost.a(aVar);
    }

    protected void i(com.chartboost.sdk.Model.a aVar) {
        j(aVar);
    }

    public void j(com.chartboost.sdk.Model.a aVar) {
        if (!aVar.k) {
            aVar.k = true;
            aVar.j = false;
            k(aVar);
            this.b.remove(aVar.e);
            if (e(aVar.e) == aVar) {
                f(aVar.e);
            }
        }
    }

    protected void k(com.chartboost.sdk.Model.a aVar) {
        ay l = l(aVar);
        l.a(true);
        if (aVar.j) {
            l.a("cached", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        } else {
            l.a("cached", AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        Object e = aVar.A().e("ad_id");
        if (e != null) {
            l.a("ad_id", e);
        }
        l.a(GooglePlayServicesInterstitial.LOCATION_KEY, aVar.e);
        l.a(new 3(this, aVar));
        com.chartboost.sdk.Tracking.a.a(f(), aVar.e, aVar.t());
    }

    protected final boolean m(com.chartboost.sdk.Model.a aVar) {
        return TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - aVar.b.getTime()) >= 86400;
    }

    protected void a(com.chartboost.sdk.Model.a aVar, com.chartboost.sdk.Libraries.e.a aVar2) {
        if (aVar2.f(Games.EXTRA_STATUS) == 404) {
            CBLogging.b(aVar.d, "Invalid status code" + aVar2.a(Games.EXTRA_STATUS));
            a(aVar, CBImpressionError.NO_AD_FOUND);
        } else if (aVar2.f(Games.EXTRA_STATUS) != 200) {
            CBLogging.b(aVar.d, "Invalid status code" + aVar2.a(Games.EXTRA_STATUS));
            a(aVar, CBImpressionError.INVALID_RESPONSE);
        } else {
            aVar.a(aVar2, d.a().a);
        }
    }

    protected final void a(ay ayVar, com.chartboost.sdk.Model.a aVar) {
        aVar.v = true;
        ayVar.a(new 4(this, aVar));
    }

    protected synchronized com.chartboost.sdk.Model.a c(String str) {
        com.chartboost.sdk.Model.a aVar;
        if (TextUtils.isEmpty(str)) {
            aVar = null;
        } else {
            aVar = (com.chartboost.sdk.Model.a) this.d.get(str);
        }
        return aVar;
    }

    protected synchronized void n(com.chartboost.sdk.Model.a aVar) {
        if (aVar != null) {
            this.d.remove(aVar.e);
        }
    }

    protected synchronized void o(com.chartboost.sdk.Model.a aVar) {
        if (aVar != null) {
            this.d.put(aVar.e, aVar);
        }
    }

    public boolean d(String str) {
        return e(str) != null;
    }

    protected com.chartboost.sdk.Model.a e(String str) {
        com.chartboost.sdk.Model.a aVar = (com.chartboost.sdk.Model.a) this.e.get(str);
        if (aVar != null && !m(aVar)) {
            return aVar;
        }
        if (!this.b.isEmpty() && this.b.containsKey(str)) {
            aVar = (com.chartboost.sdk.Model.a) this.b.get(str);
            if (!(aVar == null || m(aVar))) {
                return aVar;
            }
        }
        return null;
    }

    protected void f(String str) {
        CBLogging.a(getClass().getSimpleName(), "##### Removing impression-> " + f() + " at location" + str);
        this.e.remove(str);
    }

    protected void a() {
        CBLogging.a(getClass().getSimpleName(), "##### Clearing all cached impressions for ->" + f());
        this.e.clear();
    }

    protected void p(com.chartboost.sdk.Model.a aVar) {
        CBLogging.a(getClass().getSimpleName(), "##### Adding aimpression-> " + f() + " at location" + aVar.e);
        CBLogging.a(getClass().getSimpleName(), "##### Impression should cache:" + aVar.j);
        this.e.put(aVar.e, aVar);
    }

    protected final a b() {
        if (this.g == null) {
            this.g = c();
        }
        return this.g;
    }

    public void d() {
        if (!(this.b == null || this.b.isEmpty())) {
            CBLogging.a(getClass().getSimpleName(), "###### Invalidate Cached Impression for webview");
            for (com.chartboost.sdk.Model.a aVar : this.b.values()) {
                o(aVar);
                this.b.remove(aVar.e);
                a(aVar, aVar.A());
            }
        }
        if (!(this.e == null || this.e.isEmpty())) {
            for (com.chartboost.sdk.Model.a aVar2 : this.e.values()) {
                o(aVar2);
                a(aVar2, aVar2.A());
                this.e.remove(aVar2.e);
            }
        }
        if (this.f != null && !this.f.isEmpty()) {
            for (com.chartboost.sdk.Model.a aVar22 : this.f.values()) {
                o(aVar22);
                a(aVar22, aVar22.A());
                this.f.remove(aVar22.e);
            }
        }
    }

    protected Context e() {
        try {
            Method declaredMethod = Chartboost.class.getDeclaredMethod("getValidContext", new Class[0]);
            declaredMethod.setAccessible(true);
            return (Context) declaredMethod.invoke(null, new Object[0]);
        } catch (Throwable e) {
            CBLogging.b(this, "Error encountered getting valid context", e);
            CBUtility.throwProguardError(e);
            return c.y();
        }
    }

    public void q(com.chartboost.sdk.Model.a aVar) {
    }

    public b g() {
        return this.c;
    }

    public void a(com.chartboost.sdk.Model.a aVar, b bVar) {
        if (aVar != null) {
            aVar.a = bVar;
        }
        this.c = bVar;
    }

    public String h() {
        if (this.c == b.NATIVE) {
            return AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
        }
        return AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB;
    }
}
