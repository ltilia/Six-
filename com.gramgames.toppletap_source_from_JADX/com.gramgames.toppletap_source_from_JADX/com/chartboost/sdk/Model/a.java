package com.chartboost.sdk.Model;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.g;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.ae;
import com.chartboost.sdk.impl.ag;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.au;
import com.chartboost.sdk.impl.av;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.ba;
import com.chartboost.sdk.impl.bo;
import com.chartboost.sdk.impl.bs;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer.util.MimeTypes;
import com.mopub.mobileads.ChartboostShared;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.util.Date;
import org.json.simple.parser.Yytoken;

public final class a {
    private d A;
    private Runnable B;
    public b a;
    public Date b;
    public e c;
    public a d;
    public String e;
    public c f;
    public int g;
    public String h;
    public String i;
    public boolean j;
    public boolean k;
    public bo l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public ay r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    private com.chartboost.sdk.Libraries.e.a w;
    private boolean x;
    private Boolean y;
    private g z;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[a.values().length];
            try {
                a[a.INTERSTITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.REWARDED_VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.MORE_APPS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum a {
        INTERSTITIAL,
        MORE_APPS,
        REWARDED_VIDEO,
        NONE
    }

    public enum b {
        NATIVE,
        WEB
    }

    public enum c {
        INTERSTITIAL,
        INTERSTITIAL_VIDEO,
        INTERSTITIAL_REWARD_VIDEO,
        NONE
    }

    public interface d {
        void a(a aVar);

        void a(a aVar, CBImpressionError cBImpressionError);

        void a(a aVar, String str, com.chartboost.sdk.Libraries.e.a aVar2);

        void b(a aVar);

        void c(a aVar);

        void d(a aVar);
    }

    public enum e {
        LOADING,
        LOADED,
        DISPLAYED,
        CACHED,
        DISMISSING,
        NONE
    }

    public a(a aVar, boolean z, String str, boolean z2, b bVar) {
        this.a = b.NATIVE;
        this.y = null;
        this.g = 0;
        this.h = BuildConfig.FLAVOR;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.t = false;
        this.u = false;
        this.v = false;
        this.c = e.LOADING;
        this.j = z;
        this.b = new Date();
        this.k = false;
        this.s = false;
        this.u = true;
        this.d = aVar;
        this.m = z2;
        this.w = com.chartboost.sdk.Libraries.e.a.a;
        this.f = c.NONE;
        this.e = str;
        this.x = true;
        this.a = bVar;
        if (this.e == null) {
            this.e = ChartboostShared.LOCATION_DEFAULT;
        }
    }

    public void a(com.chartboost.sdk.Libraries.e.a aVar, d dVar) {
        int i;
        int i2 = 0;
        if (aVar == null) {
            aVar = com.chartboost.sdk.Libraries.e.a.a();
        }
        this.w = aVar;
        this.c = e.LOADING;
        this.A = dVar;
        Object e = this.w.e(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
        if (TextUtils.isEmpty(e) || !e.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
            this.a = b.WEB;
        } else {
            this.a = b.NATIVE;
        }
        if (this.a == b.NATIVE) {
            i = 1;
        } else {
            i = 0;
        }
        if (i != 0) {
            switch (1.a[this.d.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    if (!aVar.a("media-type").equals(MimeTypes.BASE_TYPE_VIDEO)) {
                        this.f = c.INTERSTITIAL;
                        this.z = new ag(this);
                        break;
                    }
                    this.f = c.INTERSTITIAL_VIDEO;
                    this.z = new ah(this);
                    this.x = false;
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    this.f = c.INTERSTITIAL_REWARD_VIDEO;
                    this.z = new ah(this);
                    this.x = false;
                    if (aVar.c()) {
                        this.g = aVar.f("reward");
                        this.h = aVar.e("currency-name");
                        break;
                    }
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    this.z = new av(this);
                    this.x = false;
                    break;
            }
        }
        switch (1.a[this.d.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                if (!aVar.a("media-type").c() || !aVar.a("media-type").equals(MimeTypes.BASE_TYPE_VIDEO)) {
                    if (!aVar.a("media-type").c() || !aVar.a("media-type").equals("image")) {
                        CBLogging.b("CBImpression", "Unknown media type in the response, so have issues determining which ad type to create the view for.");
                        a(CBImpressionError.ERROR_CREATING_VIEW);
                        break;
                    }
                    this.f = c.INTERSTITIAL;
                    break;
                }
                this.f = c.INTERSTITIAL_VIDEO;
                this.x = false;
                break;
                break;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                this.f = c.INTERSTITIAL_REWARD_VIDEO;
                this.x = false;
                if (aVar.c()) {
                    this.g = aVar.f("reward");
                }
                if (this.g <= 0) {
                    try {
                        com.chartboost.sdk.Libraries.e.a a = aVar.a("webview");
                        if (a.c() && a.a("elements").c()) {
                            a = a.a("elements");
                            if (a.p() > 0) {
                                while (i2 < a.p()) {
                                    com.chartboost.sdk.Libraries.e.a c = a.c(i2);
                                    Object e2 = c.e("param");
                                    if (!TextUtils.isEmpty(e2) && e2.contains("reward_amount")) {
                                        this.g = Integer.valueOf(c.f("value")).intValue();
                                    }
                                    if (!TextUtils.isEmpty(e2) && e2.contains("reward_currency")) {
                                        this.h = c.e("value");
                                    }
                                    i2++;
                                }
                                break;
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        break;
                    }
                }
                break;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                this.x = false;
                break;
        }
        this.z = new bs(this);
        this.z.a(aVar);
    }

    public boolean a() {
        return this.x;
    }

    public boolean b() {
        if (com.chartboost.sdk.c.b() != null) {
            return true;
        }
        return false;
    }

    public void c() {
        if (this.A != null) {
            this.u = true;
            this.A.b(this);
        }
    }

    public void d() {
        if (this.A != null) {
            this.A.a(this);
        }
    }

    public boolean a(String str, com.chartboost.sdk.Libraries.e.a aVar) {
        if (this.c != e.DISPLAYED || this.o) {
            return false;
        }
        if (str == null) {
            str = this.w.e(ShareConstants.WEB_DIALOG_PARAM_LINK);
        }
        String e = this.w.e("deep-link");
        if (!TextUtils.isEmpty(e)) {
            try {
                if (ba.a(e)) {
                    try {
                        this.y = new Boolean(true);
                        str = e;
                    } catch (Exception e2) {
                        str = e;
                    }
                } else {
                    this.y = new Boolean(false);
                }
            } catch (Exception e3) {
            }
        }
        if (this.s) {
            return false;
        }
        this.s = true;
        this.u = false;
        this.A.a(this, str, aVar);
        return true;
    }

    public boolean e() {
        return this.y != null;
    }

    public boolean f() {
        return this.y.booleanValue();
    }

    public void a(CBImpressionError cBImpressionError) {
        if (this.A != null) {
            this.A.a(this, cBImpressionError);
        }
    }

    public void g() {
        if (this.A != null) {
            this.A.c(this);
        }
    }

    public void h() {
        if (this.A != null) {
            this.A.d(this);
        }
    }

    public boolean i() {
        if (this.z != null) {
            this.z.b();
            if (this.z.e() != null) {
                return true;
            }
        }
        CBLogging.b("CBImpression", "reinitializing -- no view protocol exists!!");
        CBLogging.e("CBImpression", "reinitializing -- view not yet created");
        return false;
    }

    public void j() {
        k();
        if (this.k) {
            if (this.z != null) {
                this.z.d();
            }
            this.z = null;
            CBLogging.e("CBImpression", "Destroying the view and view data");
        }
    }

    public void k() {
        if (this.l != null) {
            this.l.d();
            try {
                if (!(this.z == null || this.z.e() == null || this.z.e().getParent() == null)) {
                    this.l.removeView(this.z.e());
                }
            } catch (Throwable e) {
                CBLogging.b("CBImpression", "Exception raised while cleaning up views", e);
            }
            this.l = null;
        }
        if (this.z != null) {
            this.z.f();
        }
        CBLogging.e("CBImpression", "Destroying the view");
    }

    public CBImpressionError l() {
        if (this.z != null) {
            return this.z.c();
        }
        return CBImpressionError.ERROR_CREATING_VIEW;
    }

    public com.chartboost.sdk.g.a m() {
        if (this.z != null) {
            return this.z.e();
        }
        return null;
    }

    public void n() {
        if (this.z != null && this.z.e() != null) {
            this.z.e().setVisibility(0);
        }
    }

    public void o() {
        if (this.z != null && this.z.e() != null) {
            this.z.e().setVisibility(8);
        }
    }

    public void p() {
        if (this.z != null && this.z.e() != null) {
            ViewParent parent = this.z.e().getParent();
            if (parent == null || !(parent instanceof View)) {
                o();
            } else {
                ((View) parent).setVisibility(0);
            }
        }
    }

    public void q() {
        if (this.z != null && this.z.e() != null) {
            ViewParent parent = this.z.e().getParent();
            if (parent == null || !(parent instanceof View)) {
                n();
            } else {
                ((View) parent).setVisibility(8);
            }
        }
    }

    public void a(Runnable runnable) {
        this.B = runnable;
    }

    public void r() {
        this.o = true;
    }

    public void s() {
        if (this.B != null) {
            this.B.run();
            this.B = null;
        }
        this.o = false;
        this.n = false;
    }

    public String t() {
        return this.w.e("ad_id");
    }

    public com.chartboost.sdk.e u() {
        switch (1.a[this.d.ordinal()]) {
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return ae.k();
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return au.i();
            default:
                return ad.i();
        }
    }

    public void v() {
        u().j(this);
    }

    public boolean w() {
        if (this.z != null) {
            return this.z.l();
        }
        return false;
    }

    public void x() {
        this.s = false;
        if (this.z != null && this.t) {
            this.t = false;
            this.z.m();
        }
    }

    public void y() {
        this.s = false;
    }

    public void z() {
        if (this.z != null && !this.t) {
            this.t = true;
            this.z.n();
        }
    }

    public com.chartboost.sdk.Libraries.e.a A() {
        return this.w == null ? com.chartboost.sdk.Libraries.e.a.a : this.w;
    }

    public void a(com.chartboost.sdk.Libraries.e.a aVar) {
        if (aVar == null) {
            aVar = com.chartboost.sdk.Libraries.e.a.a;
        }
        this.w = aVar;
    }

    public g B() {
        return this.z;
    }

    public boolean C() {
        return this.u;
    }
}
