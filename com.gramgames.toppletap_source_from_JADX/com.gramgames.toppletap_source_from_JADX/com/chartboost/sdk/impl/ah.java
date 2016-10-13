package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Libraries.j;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a.c;
import com.chartboost.sdk.g;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.gms.games.stats.PlayerStats;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.volley.DefaultRetryPolicy;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import org.json.simple.parser.Yytoken;

public class ah extends ag {
    protected boolean A;
    protected int B;
    protected j C;
    protected j D;
    protected j E;
    protected j F;
    protected j G;
    protected j H;
    protected j I;
    protected j J;
    protected boolean K;
    protected boolean L;
    protected boolean M;
    private boolean N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    protected b q;
    protected int r;
    protected String s;
    protected String t;
    protected int u;
    protected int v;
    protected boolean w;
    protected boolean x;
    protected boolean y;
    protected boolean z;

    class 1 extends com.chartboost.sdk.impl.bk.b {
        final /* synthetic */ ah a;

        1(ah ahVar) {
            this.a = ahVar;
        }

        public void a(bk bkVar) {
            a r = this.a.r();
            if (r != null) {
                r.i.e();
            }
        }

        public void a(bk bkVar, int i) {
            a r = this.a.r();
            if (i != 1) {
                if (r != null) {
                    r.d(false);
                    r.i.h();
                }
                this.a.h();
            } else if (r != null) {
                r.i.e();
            }
        }
    }

    static /* synthetic */ class 2 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[b.values().length];
            try {
                a[b.REWARD_OFFER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.VIDEO_PLAYING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.POST_VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public class a extends com.chartboost.sdk.impl.ag.a {
        final /* synthetic */ ah g;
        private bj h;
        private an i;
        private ak j;
        private View k;
        private af l;
        private ai m;
        private bj n;

        class 1 extends bj {
            final /* synthetic */ ah a;
            final /* synthetic */ a b;

            1(a aVar, Context context, ah ahVar) {
                this.b = aVar;
                this.a = ahVar;
                super(context);
            }

            protected void a(MotionEvent motionEvent) {
                if (this.b.g.g.f == c.INTERSTITIAL_REWARD_VIDEO) {
                    this.b.m.a(false);
                }
                if (this.b.g.q == b.VIDEO_PLAYING) {
                    this.b.d(false);
                }
                this.b.c(true);
            }
        }

        class 2 extends bj {
            final /* synthetic */ ah a;
            final /* synthetic */ a b;

            2(a aVar, Context context, ah ahVar) {
                this.b = aVar;
                this.a = ahVar;
                super(context);
            }

            protected void a(MotionEvent motionEvent) {
                this.b.e();
            }
        }

        class 3 implements Runnable {
            final /* synthetic */ a a;

            3(a aVar) {
                this.a = aVar;
            }

            public void run() {
                boolean z;
                String str = "InterstitialVideoViewProtocol";
                String str2 = "controls %s automatically from timer";
                Object[] objArr = new Object[1];
                objArr[0] = this.a.g.w ? "hidden" : "shown";
                CBLogging.c(str, String.format(str2, objArr));
                an b = this.a.i;
                if (this.a.g.w) {
                    z = false;
                } else {
                    z = true;
                }
                b.a(z, true);
                this.a.g.i.remove(Integer.valueOf(this.a.i.hashCode()));
            }
        }

        class 4 implements Runnable {
            final /* synthetic */ a a;

            4(a aVar) {
                this.a = aVar;
            }

            public void run() {
                this.a.m.a(false);
            }
        }

        class 5 implements Runnable {
            final /* synthetic */ a a;

            5(a aVar) {
                this.a = aVar;
            }

            public void run() {
                this.a.g.h();
            }
        }

        private a(ah ahVar, Context context) {
            this.g = ahVar;
            super(ahVar, context);
            if (ahVar.L) {
                this.k = new View(context);
                this.k.setBackgroundColor(CtaButton.BACKGROUND_COLOR);
                this.k.setVisibility(8);
                addView(this.k);
            }
            if (ahVar.g.f == c.INTERSTITIAL_REWARD_VIDEO) {
                this.j = new ak(context, ahVar);
                this.j.setVisibility(8);
                addView(this.j);
            }
            this.i = new an(context, ahVar);
            this.i.setVisibility(8);
            addView(this.i);
            this.l = new af(context, ahVar);
            this.l.setVisibility(8);
            addView(this.l);
            if (ahVar.g.f == c.INTERSTITIAL_REWARD_VIDEO) {
                this.m = new ai(context, ahVar);
                this.m.setVisibility(8);
                addView(this.m);
            }
            this.h = new 1(this, getContext(), ahVar);
            this.h.setVisibility(8);
            addView(this.h);
            this.n = new 2(this, getContext(), ahVar);
            this.n.setVisibility(8);
            addView(this.n);
            if (ahVar.n.a(NotificationCompatApi21.CATEGORY_PROGRESS).c("background-color") && ahVar.n.a(NotificationCompatApi21.CATEGORY_PROGRESS).c("border-color") && ahVar.n.a(NotificationCompatApi21.CATEGORY_PROGRESS).c("progress-color") && ahVar.n.a(NotificationCompatApi21.CATEGORY_PROGRESS).c("radius")) {
                ahVar.K = true;
                aj c = this.i.c();
                c.a(g.a(ahVar.n.a(NotificationCompatApi21.CATEGORY_PROGRESS).e("background-color")));
                c.b(g.a(ahVar.n.a(NotificationCompatApi21.CATEGORY_PROGRESS).e("border-color")));
                c.c(g.a(ahVar.n.a(NotificationCompatApi21.CATEGORY_PROGRESS).e("progress-color")));
                c.b(ahVar.n.a(NotificationCompatApi21.CATEGORY_PROGRESS).a("radius").k());
            }
            if (ahVar.n.a("video-controls-background").c(TtmlNode.ATTR_TTS_COLOR)) {
                this.i.a(g.a(ahVar.n.a("video-controls-background").e(TtmlNode.ATTR_TTS_COLOR)));
            }
            if (ahVar.g.f == c.INTERSTITIAL_REWARD_VIDEO && ahVar.y) {
                this.l.a(ahVar.n.a("post-video-toaster").e(ShareConstants.WEB_DIALOG_PARAM_TITLE), ahVar.n.a("post-video-toaster").e("tagline"));
            }
            if (ahVar.g.f == c.INTERSTITIAL_REWARD_VIDEO && ahVar.x) {
                this.j.a(ahVar.n.a("confirmation").e(MimeTypes.BASE_TYPE_TEXT), g.a(ahVar.n.a("confirmation").e(TtmlNode.ATTR_TTS_COLOR)));
            }
            if (ahVar.g.f == c.INTERSTITIAL_REWARD_VIDEO && ahVar.z) {
                this.m.a(ahVar.n.a("post-video-reward-toaster").a("position").equals("inside-top") ? com.chartboost.sdk.impl.al.a.TOP : com.chartboost.sdk.impl.al.a.BOTTOM);
                this.m.a(ahVar.n.a("post-video-reward-toaster").e(MimeTypes.BASE_TYPE_TEXT));
                if (ahVar.H.e()) {
                    this.m.a(ahVar.J);
                }
            }
            if (ahVar.f.a("video-click-button").b()) {
                this.i.d();
            }
            this.i.c(ahVar.n.j("video-progress-timer-enabled"));
            if (ahVar.M || ahVar.L) {
                this.e.setVisibility(4);
            }
            ahVar.t = ahVar.f.a(ahVar.a().a() ? "video-portrait" : "video-landscape").e(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
            if (TextUtils.isEmpty(ahVar.t)) {
                ahVar.a(CBImpressionError.VIDEO_ID_MISSING);
                return;
            }
            if (ahVar.s == null) {
                ahVar.s = h.a(ahVar.t);
            }
            if (ahVar.s == null) {
                ahVar.a(CBImpressionError.VIDEO_UNAVAILABLE);
            } else {
                this.i.a(ahVar.s);
            }
        }

        protected void d() {
            super.d();
            if (this.g.q != b.REWARD_OFFER || (this.g.x && !this.g.o())) {
                a(this.g.q, false);
            } else {
                c(false);
            }
        }

        public void f() {
            d(true);
            this.i.h();
            ah ahVar = this.g;
            ahVar.r++;
            if (this.g.r <= 1 && !this.g.R && this.g.u >= 1) {
                this.g.g.g();
            }
        }

        protected void a(int i, int i2) {
            super.a(i, i2);
            a(this.g.q, false);
            boolean a = this.g.a().a();
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
            LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
            LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.b.getLayoutParams();
            this.g.a(layoutParams2, a ? this.g.D : this.g.C, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            Point b = this.g.b(a ? "replay-portrait" : "replay-landscape");
            int round = Math.round(((((float) layoutParams6.leftMargin) + (((float) layoutParams6.width) / 2.0f)) + ((float) b.x)) - (((float) layoutParams2.width) / 2.0f));
            int round2 = Math.round((((((float) layoutParams6.height) / 2.0f) + ((float) layoutParams6.topMargin)) + ((float) b.y)) - (((float) layoutParams2.height) / 2.0f));
            layoutParams2.leftMargin = Math.min(Math.max(0, round), i - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, round2), i2 - layoutParams2.height);
            this.h.bringToFront();
            if (a) {
                this.h.a(this.g.D);
            } else {
                this.h.a(this.g.C);
            }
            layoutParams6 = (RelativeLayout.LayoutParams) this.d.getLayoutParams();
            if (this.g.t()) {
                LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
                j jVar = a ? this.g.l : this.g.m;
                this.g.a(layoutParams7, jVar, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                layoutParams7.leftMargin = 0;
                layoutParams7.topMargin = 0;
                layoutParams7.addRule(11);
                this.n.setLayoutParams(layoutParams7);
                this.n.a(jVar);
            } else {
                layoutParams3.width = layoutParams6.width;
                layoutParams3.height = layoutParams6.height;
                layoutParams3.leftMargin = layoutParams6.leftMargin;
                layoutParams3.topMargin = layoutParams6.topMargin;
                layoutParams4.width = layoutParams6.width;
                layoutParams4.height = layoutParams6.height;
                layoutParams4.leftMargin = layoutParams6.leftMargin;
                layoutParams4.topMargin = layoutParams6.topMargin;
            }
            layoutParams5.width = layoutParams6.width;
            layoutParams5.height = 72;
            layoutParams5.leftMargin = layoutParams6.leftMargin;
            layoutParams5.topMargin = (layoutParams6.height + layoutParams6.topMargin) - 72;
            if (this.g.L) {
                this.k.setLayoutParams(layoutParams);
            }
            if (this.g.g.f == c.INTERSTITIAL_REWARD_VIDEO) {
                this.j.setLayoutParams(layoutParams3);
            }
            this.i.setLayoutParams(layoutParams4);
            this.l.setLayoutParams(layoutParams5);
            this.h.setLayoutParams(layoutParams2);
            if (this.g.g.f == c.INTERSTITIAL_REWARD_VIDEO) {
                this.j.a();
            }
            this.i.a();
        }

        private void c(boolean z) {
            if (this.g.q != b.VIDEO_PLAYING) {
                if (this.g.x) {
                    a(b.REWARD_OFFER, z);
                    return;
                }
                a(b.VIDEO_PLAYING, z);
                if (this.g.r >= 1 || !this.g.n.a("timer").c("delay")) {
                    this.i.a(!this.g.w);
                } else {
                    String str = "InterstitialVideoViewProtocol";
                    String str2 = "controls starting %s, setting timer";
                    Object[] objArr = new Object[1];
                    objArr[0] = this.g.w ? "visible" : "hidden";
                    CBLogging.c(str, String.format(str2, objArr));
                    this.i.a(this.g.w);
                    this.g.a(this.i, new 3(this), Math.round(1000.0d * this.g.n.a("timer").h("delay")));
                }
                this.i.e();
                if (this.g.r <= 1) {
                    this.g.g.h();
                }
            }
        }

        private void d(boolean z) {
            this.i.f();
            if (this.g.q == b.VIDEO_PLAYING && z) {
                if (this.g.r < 1 && this.g.n.c("post-video-reward-toaster") && this.g.z && this.g.H.e() && this.g.I.e()) {
                    e(true);
                }
                a(b.POST_VIDEO, true);
                if (CBUtility.c().a()) {
                    requestLayout();
                }
            }
        }

        private void e(boolean z) {
            if (z) {
                this.m.a(true);
            } else {
                this.m.setVisibility(0);
            }
            g.a.postDelayed(new 4(this), 2500);
        }

        private void a(b bVar, boolean z) {
            boolean z2;
            boolean z3 = true;
            this.g.q = bVar;
            switch (2.a[bVar.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    this.g.a(!this.g.t(), this.d, z);
                    if (this.g.g.f == c.INTERSTITIAL_REWARD_VIDEO) {
                        this.g.a(true, this.j, z);
                    }
                    if (this.g.L) {
                        this.g.a(false, this.k, z);
                    }
                    this.g.a(false, this.i, z);
                    this.g.a(false, this.h, z);
                    this.g.a(false, this.l, z);
                    this.d.setEnabled(false);
                    this.h.setEnabled(false);
                    this.i.setEnabled(false);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    this.g.a(false, this.d, z);
                    if (this.g.g.f == c.INTERSTITIAL_REWARD_VIDEO) {
                        this.g.a(false, this.j, z);
                    }
                    if (this.g.L) {
                        this.g.a(true, this.k, z);
                    }
                    this.g.a(true, this.i, z);
                    this.g.a(false, this.h, z);
                    this.g.a(false, this.l, z);
                    this.d.setEnabled(true);
                    this.h.setEnabled(false);
                    this.i.setEnabled(true);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    this.g.a(true, this.d, z);
                    if (this.g.g.f == c.INTERSTITIAL_REWARD_VIDEO) {
                        this.g.a(false, this.j, z);
                    }
                    if (this.g.L) {
                        this.g.a(false, this.k, z);
                    }
                    this.g.a(false, this.i, z);
                    this.g.a(true, this.h, z);
                    z2 = this.g.I.e() && this.g.H.e() && this.g.y;
                    this.g.a(z2, this.l, z);
                    this.h.setEnabled(true);
                    this.d.setEnabled(true);
                    this.i.setEnabled(false);
                    if (this.g.A) {
                        e(false);
                        break;
                    }
                    break;
            }
            z2 = g();
            View b = b(true);
            b.setEnabled(z2);
            this.g.a(z2, b, z);
            View b2 = b(false);
            b2.setEnabled(false);
            this.g.a(false, b2, z);
            if (this.g.M || this.g.L) {
                this.g.a(!this.g.t(), this.e, z);
            }
            ah ahVar = this.g;
            if (this.g.t()) {
                z2 = false;
            } else {
                z2 = true;
            }
            ahVar.a(z2, this.b, z);
            if (bVar == b.REWARD_OFFER) {
                z3 = false;
            }
            a(z3);
        }

        protected boolean g() {
            if (this.g.q == b.VIDEO_PLAYING && this.g.r < 1) {
                float a = this.g.f.a("close-" + (this.g.a().a() ? DeviceInfo.ORIENTATION_PORTRAIT : DeviceInfo.ORIENTATION_LANDSCAPE)).a("delay").a((float) PlayerStats.UNSET_VALUE);
                int round = a >= 0.0f ? Math.round(a * 1000.0f) : -1;
                this.g.B = round;
                if (round < 0) {
                    return false;
                }
                if (round > this.i.b().d()) {
                    return false;
                }
            }
            return true;
        }

        public void b() {
            this.g.n();
            super.b();
        }

        protected void e() {
            if (this.g.q == b.VIDEO_PLAYING && this.g.n.a("cancel-popup").c(ShareConstants.WEB_DIALOG_PARAM_TITLE) && this.g.n.a("cancel-popup").c(MimeTypes.BASE_TYPE_TEXT) && this.g.n.a("cancel-popup").c("cancel") && this.g.n.a("cancel-popup").c("confirm")) {
                this.i.g();
                if (this.g.r < 1) {
                    this.g.p();
                    return;
                }
            }
            if (this.g.q == b.VIDEO_PLAYING) {
                d(false);
                this.i.h();
                if (this.g.r < 1) {
                    ah ahVar = this.g;
                    ahVar.r++;
                    this.g.g.g();
                }
            }
            g.a.post(new 5(this));
        }

        protected void a(float f, float f2, float f3, float f4) {
            if ((!this.g.w || this.g.q != b.VIDEO_PLAYING) && this.g.q != b.REWARD_OFFER) {
                b(f, f2, f3, f4);
            }
        }

        protected void b(float f, float f2, float f3, float f4) {
            if (this.g.q == b.VIDEO_PLAYING) {
                d(false);
            }
            ah ahVar = this.g;
            com.chartboost.sdk.Libraries.e.b[] bVarArr = new com.chartboost.sdk.Libraries.e.b[1];
            bVarArr[0] = e.a("click_coordinates", e.a(e.a("x", Float.valueOf(f)), e.a("y", Float.valueOf(f2)), e.a("w", Float.valueOf(f3)), e.a("h", Float.valueOf(f4))));
            ahVar.c = e.a(bVarArr);
            this.g.a(null, this.g.c);
        }

        protected void h() {
            this.g.x = false;
            c(true);
        }

        public bj b(boolean z) {
            return (!(this.g.t() && z) && (this.g.t() || z)) ? this.c : this.n;
        }
    }

    protected enum b {
        REWARD_OFFER,
        VIDEO_PLAYING,
        POST_VIDEO
    }

    public /* synthetic */ com.chartboost.sdk.g.a e() {
        return r();
    }

    public ah(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
        this.q = b.REWARD_OFFER;
        this.N = true;
        this.O = false;
        this.P = false;
        this.u = 0;
        this.v = 0;
        this.Q = false;
        this.R = false;
        this.A = false;
        this.B = 0;
        this.K = false;
        this.L = false;
        this.M = false;
        this.q = b.REWARD_OFFER;
        this.C = new j(this);
        this.D = new j(this);
        this.E = new j(this);
        this.F = new j(this);
        this.G = new j(this);
        this.H = new j(this);
        this.I = new j(this);
        this.J = new j(this);
        this.r = 0;
    }

    public boolean o() {
        return this.g.f == c.INTERSTITIAL_VIDEO;
    }

    public void p() {
        com.chartboost.sdk.impl.bk.a aVar = new com.chartboost.sdk.impl.bk.a();
        aVar.a(this.n.a("cancel-popup").e(ShareConstants.WEB_DIALOG_PARAM_TITLE)).b(this.n.a("cancel-popup").e(MimeTypes.BASE_TYPE_TEXT)).d(this.n.a("cancel-popup").e("confirm")).c(this.n.a("cancel-popup").e("cancel"));
        aVar.a(r().getContext(), new 1(this));
    }

    protected com.chartboost.sdk.g.a b(Context context) {
        return new a(context, null);
    }

    public boolean l() {
        if (!(r().b(true).getVisibility() == 4 || r().b(true).getVisibility() == 8)) {
            r().e();
        }
        return true;
    }

    public void m() {
        super.m();
        if (this.q == b.VIDEO_PLAYING && this.O) {
            r().i.b().a(this.u);
            if (!this.P) {
                r().i.e();
            }
        }
        this.P = false;
        this.O = false;
    }

    public void n() {
        super.n();
        if (this.q == b.VIDEO_PLAYING && !this.O) {
            if (!r().i.i()) {
                this.P = true;
            }
            this.O = true;
            r().i.g();
        }
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        boolean z = false;
        if (!super.a(aVar)) {
            return false;
        }
        if (this.f.b("video-landscape") || this.f.b("replay-landscape")) {
            this.k = false;
        }
        this.C.a("replay-landscape");
        this.D.a("replay-portrait");
        this.G.a("video-click-button");
        this.H.a("post-video-reward-icon");
        this.I.a("post-video-button");
        this.E.a("video-confirmation-button");
        this.F.a("video-confirmation-icon");
        this.J.a("post-video-reward-icon");
        this.w = aVar.a("ux").j("video-controls-togglable");
        this.L = aVar.a("fullscreen").b() ? false : aVar.a("fullscreen").n();
        if (!aVar.a("preroll_popup_fullscreen").b()) {
            z = aVar.a("preroll_popup_fullscreen").n();
        }
        this.M = z;
        if (this.g.f == c.INTERSTITIAL_REWARD_VIDEO && this.n.a("post-video-toaster").c(ShareConstants.WEB_DIALOG_PARAM_TITLE) && this.n.a("post-video-toaster").c("tagline")) {
            this.y = true;
        }
        if (this.g.f == c.INTERSTITIAL_REWARD_VIDEO && this.n.a("confirmation").c(MimeTypes.BASE_TYPE_TEXT) && this.n.a("confirmation").c(TtmlNode.ATTR_TTS_COLOR)) {
            this.x = true;
        }
        if (this.g.f == c.INTERSTITIAL_REWARD_VIDEO && this.n.c("post-video-reward-toaster")) {
            this.z = true;
        }
        return true;
    }

    protected void i() {
        if (this.x && !(this.E.e() && this.F.e())) {
            this.x = false;
        }
        if (this.N) {
            super.i();
        } else {
            a(CBImpressionError.ERROR_DISPLAYING_VIEW);
        }
    }

    public void d() {
        super.d();
        this.C.d();
        this.D.d();
        this.G.d();
        this.H.d();
        this.I.d();
        this.E.d();
        this.F.d();
        this.J.d();
        this.C = null;
        this.D = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.E = null;
        this.F = null;
        this.J = null;
    }

    public boolean q() {
        return this.q == b.VIDEO_PLAYING;
    }

    public a r() {
        return (a) super.e();
    }

    protected void s() {
        this.g.v();
    }

    protected boolean t() {
        boolean z = true;
        if (this.q == b.POST_VIDEO) {
            return false;
        }
        boolean a = CBUtility.c().a();
        if (this.q == b.REWARD_OFFER) {
            if (this.M || a) {
                return true;
            }
            return false;
        } else if (this.q != b.VIDEO_PLAYING) {
            if (!a || this.q == b.POST_VIDEO) {
                z = false;
            }
            return z;
        } else if (this.L || a) {
            return true;
        } else {
            return false;
        }
    }

    public boolean u() {
        return this.Q;
    }

    public void a(boolean z) {
        this.Q = z;
    }

    public void v() {
        this.R = true;
        a(CBImpressionError.ERROR_PLAYING_VIDEO);
    }

    public float j() {
        return (float) this.v;
    }

    public float k() {
        return (float) this.u;
    }
}
