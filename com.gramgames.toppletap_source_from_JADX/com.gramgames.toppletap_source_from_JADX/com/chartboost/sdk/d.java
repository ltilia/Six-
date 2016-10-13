package com.chartboost.sdk;

import android.app.Activity;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a.c;
import com.chartboost.sdk.Model.a.e;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.ba;
import com.chartboost.sdk.impl.bo;
import com.chartboost.sdk.impl.bs;
import com.facebook.share.internal.ShareConstants;
import com.mopub.mobileads.GooglePlayServicesInterstitial;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import org.json.simple.parser.Yytoken;

public final class d {
    private static final String c;
    private static d d;
    public com.chartboost.sdk.Model.a.d a;
    public com.chartboost.sdk.impl.ba.a b;
    private ba e;

    public interface b {
        void a();
    }

    public interface a {
        void a(boolean z);
    }

    class 1 implements a {
        final /* synthetic */ com.chartboost.sdk.Model.a a;
        final /* synthetic */ String b;
        final /* synthetic */ b c;
        final /* synthetic */ d d;

        class 1 implements Runnable {
            final /* synthetic */ boolean a;
            final /* synthetic */ 1 b;

            1(1 1, boolean z) {
                this.b = 1;
                this.a = z;
            }

            public void run() {
                if (this.b.a != null) {
                    if (this.b.a.b()) {
                        this.b.a.p();
                    }
                    if (!this.a) {
                        this.b.a.x();
                    }
                    if (this.b.a.a() && this.b.a.c == e.DISPLAYED) {
                        f h = Chartboost.h();
                        if (h != null) {
                            h.b(this.b.a);
                        }
                    }
                }
                if (this.a) {
                    this.b.d.a(this.b.a, this.b.b, this.b.c);
                } else {
                    this.b.d.b.a(this.b.a, false, this.b.b, CBClickError.AGE_GATE_FAILURE, this.b.c);
                }
            }
        }

        1(d dVar, com.chartboost.sdk.Model.a aVar, String str, b bVar) {
            this.d = dVar;
            this.a = aVar;
            this.b = str;
            this.c = bVar;
        }

        public void a(boolean z) {
            Chartboost.a(new 1(this, z));
        }
    }

    class 2 implements com.chartboost.sdk.Model.a.d {
        final /* synthetic */ d a;

        2(d dVar) {
            this.a = dVar;
        }

        public void a(com.chartboost.sdk.Model.a aVar) {
            synchronized (this.a) {
                boolean z = aVar.j;
            }
            if (aVar.c == e.LOADING) {
                aVar.c = e.LOADED;
                if (aVar.a == com.chartboost.sdk.Model.a.b.WEB) {
                    aVar.u().g(aVar);
                    aVar.u().n(aVar);
                    return;
                } else if (z) {
                    aVar.u().a(aVar);
                } else {
                    aVar.u().p(aVar);
                }
            }
            if (!z || aVar.c == e.DISPLAYED) {
                aVar.u().g(aVar);
            }
            aVar.u().n(aVar);
        }

        public void b(com.chartboost.sdk.Model.a aVar) {
            f h;
            if (aVar.c == e.DISPLAYED) {
                h = Chartboost.h();
                if (h != null) {
                    h.b(aVar);
                }
            } else if (aVar.a == com.chartboost.sdk.Model.a.b.WEB && aVar.c == e.LOADED) {
                h = Chartboost.h();
                if (h != null) {
                    h.d(aVar);
                }
            }
            if (aVar.C()) {
                com.chartboost.sdk.Tracking.a.c(aVar.u().f(), aVar.e, aVar.t());
            } else {
                com.chartboost.sdk.Tracking.a.d(aVar.u().f(), aVar.e, aVar.t());
            }
        }

        public void a(com.chartboost.sdk.Model.a aVar, String str, com.chartboost.sdk.Libraries.e.a aVar2) {
            aVar.u().b().a(aVar);
            if (!c.u() && aVar.a() && aVar.c == e.DISPLAYED) {
                f h = Chartboost.h();
                if (h != null) {
                    h.b(aVar);
                }
            }
            if (TextUtils.isEmpty(str)) {
                boolean z = false;
            } else {
                int i = 1;
            }
            if (i != 0) {
                com.chartboost.sdk.Libraries.e.a A = aVar.A();
                ay d = this.a.d();
                d.a("ad_id", A);
                d.a(ShareConstants.WEB_DIALOG_PARAM_TO, A);
                d.a("cgn", A);
                d.a("creative", A);
                if (aVar.f == c.INTERSTITIAL_VIDEO || aVar.f == c.INTERSTITIAL_REWARD_VIDEO) {
                    g gVar;
                    if (aVar.a == com.chartboost.sdk.Model.a.b.NATIVE && aVar.m() != null) {
                        gVar = (ah) aVar.B();
                    } else if (aVar.a != com.chartboost.sdk.Model.a.b.WEB || aVar.m() == null) {
                        gVar = null;
                    } else {
                        bs bsVar = (bs) aVar.B();
                    }
                    if (gVar != null) {
                        float k = gVar.k();
                        float j = gVar.j();
                        CBLogging.a(aVar.u().getClass().getSimpleName(), String.format("TotalDuration: %f PlaybackTime: %f", new Object[]{Float.valueOf(j), Float.valueOf(k)}));
                        d.a("total_time", Float.valueOf(j / 1000.0f));
                        if (k <= 0.0f) {
                            d.a("playback_time", Float.valueOf(j / 1000.0f));
                        } else {
                            d.a("playback_time", Float.valueOf(k / 1000.0f));
                        }
                    }
                }
                if (aVar2 != null) {
                    d.a("cgn", aVar2);
                    d.a("creative", aVar2);
                    d.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, aVar2);
                    d.a("more_type", aVar2);
                    Object a = aVar2.a("click_coordinates");
                    if (!a.b()) {
                        d.a("click_coordinates", a);
                    }
                }
                d.a(GooglePlayServicesInterstitial.LOCATION_KEY, aVar.e);
                if (aVar.e()) {
                    d.a("retarget_reinstall", Boolean.valueOf(aVar.f()));
                }
                aVar.r = d;
                this.a.b(aVar, str, null);
            } else {
                this.a.b.a(aVar, false, str, CBClickError.URI_INVALID, null);
            }
            com.chartboost.sdk.Tracking.a.b(aVar.u().f(), aVar.e, aVar.t());
        }

        public void a(com.chartboost.sdk.Model.a aVar, CBImpressionError cBImpressionError) {
            e u = aVar.u();
            com.chartboost.sdk.Tracking.a.a(u.f(), aVar.e, aVar.t(), cBImpressionError);
            u.a(aVar, cBImpressionError);
        }

        public void c(com.chartboost.sdk.Model.a aVar) {
            aVar.p = true;
            if (aVar.d == com.chartboost.sdk.Model.a.a.REWARDED_VIDEO && c.h() != null) {
                c.h().didCompleteRewardedVideo(aVar.e, aVar.g);
            }
            d.b(aVar);
        }

        public void d(com.chartboost.sdk.Model.a aVar) {
            aVar.q = true;
        }
    }

    class 3 implements com.chartboost.sdk.impl.ba.a {
        final /* synthetic */ d a;

        3(d dVar) {
            this.a = dVar;
        }

        public void a(com.chartboost.sdk.Model.a aVar, boolean z, String str, CBClickError cBClickError, b bVar) {
            if (aVar != null) {
                aVar.s = false;
                if (aVar.a()) {
                    aVar.c = e.DISMISSING;
                }
            }
            if (z) {
                if (aVar != null && aVar.r != null) {
                    aVar.r.a(true);
                    aVar.r.t();
                } else if (bVar != null) {
                    bVar.a();
                }
            } else if (c.h() != null) {
                c.h().didFailToRecordClick(str, cBClickError);
            }
        }
    }

    static /* synthetic */ class 4 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[e.values().length];
            try {
                a[e.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[e.CACHED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[e.LOADED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[e.DISPLAYED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    static {
        c = d.class.getSimpleName();
    }

    private d() {
        this.a = new 2(this);
        this.b = new 3(this);
        this.e = ba.a(this.b);
    }

    public static d a() {
        if (d == null) {
            d = new d();
        }
        return d;
    }

    public final void a(com.chartboost.sdk.Model.a aVar, String str, b bVar) {
        this.e.a(aVar, str, Chartboost.getHostActivity(), bVar);
    }

    public final void b(com.chartboost.sdk.Model.a aVar, String str, b bVar) {
        c.b = new 1(this, aVar, str, bVar);
        if (!c.u()) {
            a(aVar, str, bVar);
        } else if (c.h() != null) {
            if (aVar != null) {
                if (aVar.b()) {
                    aVar.q();
                }
                aVar.z();
                aVar.s = false;
            }
            if (aVar == null) {
                c.h().didPauseClickForConfirmation(Chartboost.getHostActivity());
            } else {
                c.h().didPauseClickForConfirmation(Chartboost.f());
            }
        }
    }

    protected final boolean b() {
        com.chartboost.sdk.Model.a c = c();
        if (c == null) {
            return false;
        }
        c.u = true;
        this.a.b(c);
        return true;
    }

    private static synchronized void b(com.chartboost.sdk.Model.a aVar) {
        synchronized (d.class) {
            ay ayVar = new ay("/api/video-complete");
            ayVar.a(GooglePlayServicesInterstitial.LOCATION_KEY, aVar.e);
            ayVar.a("reward", Integer.valueOf(aVar.g));
            ayVar.a("currency-name", aVar.h);
            ayVar.a("ad_id", aVar.t());
            ayVar.a("force_close", Boolean.valueOf(false));
            g gVar = null;
            if (aVar.a == com.chartboost.sdk.Model.a.b.NATIVE && aVar.m() != null) {
                gVar = (ah) aVar.B();
            } else if (aVar.a == com.chartboost.sdk.Model.a.b.WEB && aVar.m() != null) {
                bs bsVar = (bs) aVar.B();
            }
            if (gVar != null) {
                float k = gVar.k();
                float j = gVar.j();
                CBLogging.a(aVar.u().getClass().getSimpleName(), String.format("TotalDuration: %f PlaybackTime: %f", new Object[]{Float.valueOf(j), Float.valueOf(k)}));
                ayVar.a("total_time", Float.valueOf(j / 1000.0f));
                if (k <= 0.0f) {
                    ayVar.a("playback_time", Float.valueOf(j / 1000.0f));
                } else {
                    ayVar.a("playback_time", Float.valueOf(k / 1000.0f));
                }
            }
            ayVar.a(true);
            ayVar.t();
        }
    }

    protected final com.chartboost.sdk.Model.a c() {
        f h = Chartboost.h();
        bo e = h == null ? null : h.e();
        if (e == null) {
            return null;
        }
        return e.h();
    }

    public ay d() {
        ay ayVar = new ay("/api/click");
        if (Chartboost.f() == null) {
            Chartboost.getValidContext();
        }
        return ayVar;
    }

    public final boolean a(Activity activity, com.chartboost.sdk.Model.a aVar) {
        if (aVar != null) {
            switch (4.a[aVar.c.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    if (aVar.m) {
                        Chartboost.a(aVar);
                        break;
                    }
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    Chartboost.a(aVar);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    if (!aVar.i()) {
                        if (c.b() == null || !c.b().doesWrapperUseCustomBackgroundingBehavior() || (activity instanceof CBImpressionActivity)) {
                            f h = Chartboost.h();
                            if (h != null) {
                                CBLogging.b(c, "Error onActivityStart " + aVar.c.name());
                                h.d(aVar);
                                break;
                            }
                        }
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}
