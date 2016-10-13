package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a;
import com.chartboost.sdk.Model.b;
import com.chartboost.sdk.c;
import com.chartboost.sdk.e;
import com.mopub.mobileads.GooglePlayServicesInterstitial;

public class au extends e {
    private static final String e;
    private static au f;
    protected int d;
    private a g;
    private boolean h;
    private boolean i;

    class 1 implements a {
        final /* synthetic */ au a;

        1(au auVar) {
            this.a = auVar;
        }

        public void a(a aVar) {
            if (c.h() != null) {
                c.h().didClickMoreApps(aVar.e);
            }
        }

        public void b(a aVar) {
            if (c.h() != null) {
                c.h().didCloseMoreApps(aVar.e);
            }
        }

        public void c(a aVar) {
            if (c.h() != null) {
                c.h().didDismissMoreApps(aVar.e);
            }
        }

        public void d(a aVar) {
            if (c.h() != null) {
                c.h().didCacheMoreApps(aVar.e);
            }
        }

        public void a(a aVar, CBImpressionError cBImpressionError) {
            if (c.h() != null) {
                c.h().didFailToLoadMoreApps(aVar.e, cBImpressionError);
            }
        }

        public void e(a aVar) {
            this.a.d = 0;
            this.a.j();
            if (c.h() != null) {
                c.h().didDisplayMoreApps(aVar.e);
            }
        }

        public boolean f(a aVar) {
            if (c.h() != null) {
                return c.h().shouldDisplayMoreApps(aVar.e);
            }
            return true;
        }

        public boolean g(a aVar) {
            if (c.h() != null) {
                return c.h().shouldRequestMoreApps(aVar.e);
            }
            return true;
        }

        public boolean h(a aVar) {
            return true;
        }
    }

    static {
        e = au.class.getSimpleName();
    }

    private au() {
        this.g = null;
    }

    public static au i() {
        if (f == null) {
            synchronized (au.class) {
                if (f == null) {
                    f = new au();
                }
            }
        }
        return f;
    }

    public void a(String str) {
        this.d = 0;
        j();
        super.a(str);
    }

    protected void a(a aVar, com.chartboost.sdk.Libraries.e.a aVar2) {
        if (!this.h && this.i) {
            this.i = false;
            this.d = aVar2.a("cells").p();
        }
        super.a(aVar, aVar2);
    }

    protected a a(String str, boolean z) {
        return new a(a.a.MORE_APPS, z, str, false, g());
    }

    protected ay e(a aVar) {
        ay ayVar = new ay("/more/get");
        ayVar.a(l.a.HIGH);
        ayVar.a(b.d);
        return ayVar;
    }

    protected ay l(a aVar) {
        ay ayVar = new ay("/more/show");
        if (aVar.e != null) {
            ayVar.a(GooglePlayServicesInterstitial.LOCATION_KEY, aVar.e);
        }
        if (aVar.A().c("cells")) {
            ayVar.a("cells", aVar.A().a("cells"));
        }
        return ayVar;
    }

    public void a() {
        this.g = null;
    }

    protected a e(String str) {
        return this.g;
    }

    protected void f(String str) {
        this.g = null;
    }

    protected void p(a aVar) {
        this.g = aVar;
    }

    protected a c() {
        return new 1(this);
    }

    protected void j() {
    }

    public String f() {
        return "more-apps";
    }
}
