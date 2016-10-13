package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a;
import com.chartboost.sdk.Model.a.c;
import com.chartboost.sdk.Model.a.e;
import com.chartboost.sdk.impl.bg;
import com.chartboost.sdk.impl.bg.b;
import com.chartboost.sdk.impl.bo;
import org.json.simple.parser.Yytoken;

public final class f {
    private static f c;
    private bo a;
    private a b;
    private int d;

    class 1 implements bg.a {
        final /* synthetic */ f a;

        1(f fVar) {
            this.a = fVar;
        }

        public void a(a aVar) {
            aVar.s();
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ a a;
        final /* synthetic */ Activity b;
        final /* synthetic */ f c;

        class 1 implements bg.a {
            final /* synthetic */ 2 a;

            class 1 implements Runnable {
                final /* synthetic */ a a;
                final /* synthetic */ 1 b;

                1(1 1, a aVar) {
                    this.b = 1;
                    this.a = aVar;
                }

                public void run() {
                    this.b.a.c.d(this.a);
                }
            }

            1(2 2) {
                this.a = 2;
            }

            public void a(a aVar) {
                CBUtility.e().post(new 1(this, aVar));
                aVar.o();
                CBUtility.b(this.a.b, aVar.a);
                if (VERSION.SDK_INT >= 11 && this.a.c.d != -1) {
                    if (aVar.f == c.INTERSTITIAL_VIDEO || aVar.f == c.INTERSTITIAL_REWARD_VIDEO) {
                        this.a.b.getWindow().getDecorView().setSystemUiVisibility(this.a.c.d);
                        this.a.c.d = -1;
                    }
                }
            }
        }

        2(f fVar, a aVar, Activity activity) {
            this.c = fVar;
            this.a = aVar;
            this.b = activity;
        }

        public void run() {
            this.a.c = e.DISMISSING;
            b bVar = b.CBAnimationTypePerspectiveRotate;
            if (this.a.a == a.b.WEB) {
                bVar = b.CBAnimationTypeFade;
            }
            if (this.a.d == a.a.MORE_APPS) {
                bVar = b.CBAnimationTypePerspectiveZoom;
            }
            b a = b.a(this.a.A().f("animation"));
            if (a != null) {
                bVar = a;
            }
            if (c.j()) {
                bVar = b.CBAnimationTypeNone;
            }
            bg.b(bVar, this.a, new 1(this));
        }
    }

    static /* synthetic */ class 3 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[e.values().length];
            try {
                a[e.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    private f() {
        this.a = null;
        this.d = -1;
    }

    public static f a() {
        if (c == null) {
            c = new f();
        }
        return c;
    }

    public void a(a aVar) {
        switch (3.a[aVar.c.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                if (aVar.m && c.w()) {
                    f(aVar);
                }
            default:
                e(aVar);
        }
    }

    private void e(a aVar) {
        if (this.a == null || this.a.h() == aVar) {
            Object obj = aVar.c != e.DISPLAYED ? 1 : null;
            aVar.c = e.DISPLAYED;
            Activity f = Chartboost.f();
            CBImpressionError cBImpressionError = f == null ? CBImpressionError.NO_HOST_ACTIVITY : null;
            if (cBImpressionError == null) {
                cBImpressionError = aVar.l();
            }
            if (cBImpressionError != null) {
                CBLogging.b("CBViewController", "Unable to create the view while trying th display the impression");
                aVar.a(cBImpressionError);
                return;
            }
            if (this.a == null) {
                this.a = new bo(f, aVar);
                f.addContentView(this.a, new LayoutParams(-1, -1));
            }
            CBUtility.a(f, aVar.a);
            if (VERSION.SDK_INT >= 11 && this.d == -1 && (aVar.f == c.INTERSTITIAL_VIDEO || aVar.f == c.INTERSTITIAL_REWARD_VIDEO)) {
                this.d = f.getWindow().getDecorView().getSystemUiVisibility();
                CBUtility.a(f);
            }
            this.a.a();
            CBLogging.e("CBViewController", "Displaying the impression");
            aVar.l = this.a;
            if (obj != null) {
                if (aVar.a == a.b.NATIVE) {
                    this.a.e().a();
                }
                b bVar = b.CBAnimationTypePerspectiveRotate;
                if (aVar.a == a.b.WEB) {
                    bVar = b.CBAnimationTypeFade;
                }
                if (aVar.d == a.a.MORE_APPS) {
                    bVar = b.CBAnimationTypePerspectiveZoom;
                }
                b a = b.a(aVar.A().f("animation"));
                if (a != null) {
                    bVar = a;
                }
                if (c.j()) {
                    bVar = b.CBAnimationTypeNone;
                }
                aVar.r();
                bg.a(bVar, aVar, new 1(this));
                b.h();
                h.f();
                if (c.h() != null && (aVar.f == c.INTERSTITIAL_VIDEO || aVar.f == c.INTERSTITIAL_REWARD_VIDEO)) {
                    c.h().willDisplayVideo(aVar.e);
                }
                if (aVar.u().b() != null) {
                    aVar.u().b().e(aVar);
                    return;
                }
                return;
            }
            return;
        }
        CBLogging.b("CBViewController", "Impression already visible");
        aVar.a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
    }

    public void b(a aVar) {
        CBLogging.e("CBViewController", "Dismissing impression");
        Runnable 2 = new 2(this, aVar, Chartboost.f());
        if (aVar.o) {
            aVar.a(2);
        } else {
            2.run();
        }
    }

    private void f(a aVar) {
        Context f = Chartboost.f();
        if (f == null) {
            CBLogging.d(this, "No host activity to display loading view");
            return;
        }
        if (this.a == null) {
            this.a = new bo(f, aVar);
            f.addContentView(this.a, new LayoutParams(-1, -1));
        }
        this.a.b();
        this.b = aVar;
    }

    public void a(a aVar, boolean z) {
        if (aVar == null) {
            return;
        }
        if (aVar == this.b || aVar == d.a().c()) {
            this.b = null;
            CBLogging.e("CBViewController", "Dismissing loading view");
            if (c()) {
                this.a.c();
                if (z && this.a != null && this.a.h() != null) {
                    d(this.a.h());
                }
            }
        }
    }

    public void c(a aVar) {
        CBLogging.e("CBViewController", "Removing impression silently");
        if (c()) {
            a(aVar, false);
        }
        aVar.k();
        try {
            ((ViewGroup) this.a.getParent()).removeView(this.a);
        } catch (Throwable e) {
            CBLogging.b("CBViewController", "Exception removing impression silently", e);
        }
        this.a = null;
    }

    public void d(a aVar) {
        CBLogging.e("CBViewController", "Removing impression");
        aVar.c = e.NONE;
        if (this.a != null) {
            try {
                ((ViewGroup) this.a.getParent()).removeView(this.a);
            } catch (Throwable e) {
                CBLogging.b("CBViewController", "Exception removing impression ", e);
            }
            aVar.j();
            this.a = null;
            b.i();
            h.g();
            if (c.i()) {
                b();
            }
            aVar.u().b().c(aVar);
            if (aVar.C()) {
                aVar.u().b().b(aVar);
            }
        } else if (c.i()) {
            b();
        }
    }

    public void b() {
        CBLogging.e("CBViewController", "Attempting to close impression activity");
        Activity f = Chartboost.f();
        if (f != null && (f instanceof CBImpressionActivity)) {
            CBLogging.e("CBViewController", "Closing impression activity");
            Chartboost.g();
            f.finish();
        }
    }

    public boolean c() {
        return this.a != null && this.a.g();
    }

    public boolean d() {
        return d.a().c() != null;
    }

    public bo e() {
        return this.a;
    }
}
