package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.e.b;
import com.chartboost.sdk.Libraries.j;
import com.chartboost.sdk.g;
import com.mopub.mobileads.VastIconXmlManager;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.volley.DefaultRetryPolicy;

public class ag extends g {
    protected j l;
    protected j m;
    protected com.chartboost.sdk.Libraries.e.a n;
    protected String o;
    protected float p;
    private j q;
    private j r;
    private j s;
    private j t;

    public class a extends com.chartboost.sdk.g.a {
        protected bi b;
        protected bj c;
        protected bj d;
        protected ImageView e;
        final /* synthetic */ ag f;
        private boolean g;

        class 1 extends bj {
            final /* synthetic */ ag a;
            final /* synthetic */ a b;

            1(a aVar, Context context, ag agVar) {
                this.b = aVar;
                this.a = agVar;
                super(context);
            }

            protected void a(MotionEvent motionEvent) {
                this.b.a(motionEvent.getX(), motionEvent.getY(), (float) this.b.d.getWidth(), (float) this.b.d.getHeight());
            }
        }

        class 2 extends bj {
            final /* synthetic */ a a;

            2(a aVar, Context context) {
                this.a = aVar;
                super(context);
            }

            protected void a(MotionEvent motionEvent) {
                this.a.e();
            }
        }

        protected a(ag agVar, Context context) {
            this.f = agVar;
            super(agVar, context);
            this.g = false;
            setBackgroundColor(0);
            setLayoutParams(new LayoutParams(-1, -1));
            this.b = new bi(context);
            addView(this.b, new LayoutParams(-1, -1));
            this.d = new 1(this, context, agVar);
            a(this.d);
            this.e = new ImageView(context);
            this.e.setBackgroundColor(CtaButton.BACKGROUND_COLOR);
            addView(this.e);
            addView(this.d);
        }

        protected void d() {
            this.c = new 2(this, getContext());
            addView(this.c);
        }

        protected void a(float f, float f2, float f3, float f4) {
            ag agVar = this.f;
            b[] bVarArr = new b[1];
            bVarArr[0] = e.a("click_coordinates", e.a(e.a("x", Float.valueOf(f)), e.a("y", Float.valueOf(f2)), e.a("w", Float.valueOf(f3)), e.a("h", Float.valueOf(f4))));
            agVar.c = e.a(bVarArr);
            this.f.a(null, this.f.c);
        }

        protected void a(int i, int i2) {
            int round;
            int round2;
            if (!this.g) {
                d();
                this.g = true;
            }
            boolean a = this.f.a().a();
            j b = a ? this.f.q : this.f.r;
            j jVar = a ? this.f.l : this.f.m;
            if (!b.e()) {
                if (b == this.f.q) {
                    b = this.f.r;
                } else {
                    b = this.f.q;
                }
            }
            if (!jVar.e()) {
                if (jVar == this.f.l) {
                    jVar = this.f.m;
                } else {
                    jVar = this.f.l;
                }
            }
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            this.f.a(layoutParams, b, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f.p = Math.min(Math.min(((float) i) / ((float) layoutParams.width), ((float) i2) / ((float) layoutParams.height)), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            layoutParams.width = (int) (((float) layoutParams.width) * this.f.p);
            layoutParams.height = (int) (((float) layoutParams.height) * this.f.p);
            Point b2 = this.f.b(a ? "frame-portrait" : "frame-landscape");
            layoutParams.leftMargin = Math.round((((float) (i - layoutParams.width)) / 2.0f) + ((((float) b2.x) / b.g()) * this.f.p));
            layoutParams.topMargin = Math.round(((((float) b2.y) / b.g()) * this.f.p) + (((float) (i2 - layoutParams.height)) / 2.0f));
            this.f.a(layoutParams2, jVar, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            b2 = this.f.b(a ? "close-portrait" : "close-landscape");
            if (b2.x == 0 && b2.y == 0) {
                round = Math.round(((float) (-layoutParams2.width)) / 2.0f) + (layoutParams.leftMargin + layoutParams.width);
                round2 = layoutParams.topMargin + Math.round(((float) (-layoutParams2.height)) / 2.0f);
            } else {
                round = Math.round(((((float) layoutParams.leftMargin) + (((float) layoutParams.width) / 2.0f)) + ((float) b2.x)) - (((float) layoutParams2.width) / 2.0f));
                round2 = Math.round((((float) b2.y) + (((float) layoutParams.topMargin) + (((float) layoutParams.height) / 2.0f))) - (((float) layoutParams2.height) / 2.0f));
            }
            layoutParams2.leftMargin = Math.min(Math.max(0, round), i - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, round2), i2 - layoutParams2.height);
            this.b.setLayoutParams(layoutParams);
            this.c.setLayoutParams(layoutParams2);
            this.b.setScaleType(ScaleType.FIT_CENTER);
            this.b.a(b);
            this.c.a(jVar);
            jVar = a ? this.f.s : this.f.t;
            if (!jVar.e()) {
                if (jVar == this.f.s) {
                    jVar = this.f.t;
                } else {
                    jVar = this.f.s;
                }
            }
            ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-2, -2);
            this.f.a(layoutParams3, jVar, this.f.p);
            Point b3 = this.f.b(a ? "ad-portrait" : "ad-landscape");
            layoutParams3.leftMargin = Math.round((((float) (i - layoutParams3.width)) / 2.0f) + ((((float) b3.x) / jVar.g()) * this.f.p));
            layoutParams3.topMargin = Math.round(((((float) b3.y) / jVar.g()) * this.f.p) + (((float) (i2 - layoutParams3.height)) / 2.0f));
            this.e.setLayoutParams(layoutParams3);
            this.d.setLayoutParams(layoutParams3);
            this.d.a(ScaleType.FIT_CENTER);
            this.d.a(jVar);
        }

        protected void e() {
            this.f.h();
        }

        public void b() {
            super.b();
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
        }
    }

    public ag(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
        this.p = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.q = new j(this);
        this.r = new j(this);
        this.l = new j(this);
        this.m = new j(this);
        this.s = new j(this);
        this.t = new j(this);
    }

    protected com.chartboost.sdk.g.a b(Context context) {
        return new a(this, context);
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        if (!super.a(aVar)) {
            return false;
        }
        this.o = aVar.e("ad_id");
        this.n = aVar.a("ux");
        if (this.f.b("frame-portrait") || this.f.b("close-portrait")) {
            this.j = false;
        }
        if (this.f.b("frame-landscape") || this.f.b("close-landscape")) {
            this.k = false;
        }
        this.r.a("frame-landscape");
        this.q.a("frame-portrait");
        this.m.a("close-landscape");
        this.l.a("close-portrait");
        if (this.f.b("ad-portrait")) {
            this.j = false;
        }
        if (this.f.b("ad-landscape")) {
            this.k = false;
        }
        this.t.a("ad-landscape");
        this.s.a("ad-portrait");
        return true;
    }

    protected Point b(String str) {
        com.chartboost.sdk.Libraries.e.a a = this.f.a(str).a(VastIconXmlManager.OFFSET);
        if (a.c()) {
            return new Point(a.f("x"), a.f("y"));
        }
        return new Point(0, 0);
    }

    public void a(ViewGroup.LayoutParams layoutParams, j jVar, float f) {
        layoutParams.width = (int) ((((float) jVar.b()) / jVar.g()) * f);
        layoutParams.height = (int) ((((float) jVar.c()) / jVar.g()) * f);
    }

    public void d() {
        super.d();
        this.r.d();
        this.q.d();
        this.m.d();
        this.l.d();
        this.t.d();
        this.s.d();
        this.r = null;
        this.q = null;
        this.m = null;
        this.l = null;
        this.t = null;
        this.s = null;
    }
}
