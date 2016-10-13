package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.Libraries.j;
import com.facebook.ads.AdError;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.volley.DefaultRetryPolicy;

public final class an extends RelativeLayout implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private static final CharSequence a;
    private static Handler l;
    private RelativeLayout b;
    private am c;
    private am d;
    private bj e;
    private TextView f;
    private aj g;
    private bf h;
    private ah i;
    private boolean j;
    private boolean k;
    private Runnable m;
    private Runnable n;
    private Runnable o;

    class 1 extends bj {
        final /* synthetic */ an a;

        1(an anVar, Context context) {
            this.a = anVar;
            super(context);
        }

        protected void a(MotionEvent motionEvent) {
            a a = e.a(e.a("paused", Integer.valueOf(1)));
            a.a("click_coordinates", e.a(e.a("x", Float.valueOf(motionEvent.getX())), e.a("y", Float.valueOf(motionEvent.getY())), e.a("w", Integer.valueOf(this.a.e.getWidth())), e.a("h", Integer.valueOf(this.a.e.getHeight()))));
            this.a.i.a(null, a);
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ an a;

        2(an anVar) {
            this.a = anVar;
        }

        public void run() {
            this.a.d(false);
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ an a;

        3(an anVar) {
            this.a = anVar;
        }

        public void run() {
            if (this.a.c != null) {
                this.a.c.setVisibility(8);
            }
            if (this.a.i.K) {
                this.a.g.setVisibility(8);
            }
            this.a.d.setVisibility(8);
            if (this.a.e != null) {
                this.a.e.setEnabled(false);
            }
        }
    }

    class 4 implements Runnable {
        final /* synthetic */ an a;
        private int b;

        4(an anVar) {
            this.a = anVar;
            this.b = 0;
        }

        public void run() {
            if (this.a.h.a().e()) {
                float f;
                int d = this.a.h.a().d();
                if (d > 0) {
                    this.a.i.u = d;
                    f = (float) this.a.i.u;
                    if (this.a.h.a().e() && f / 1000.0f > 0.0f && !this.a.i.u()) {
                        this.a.i.s();
                        this.a.i.a(true);
                    }
                }
                f = ((float) d) / ((float) this.a.h.a().c());
                if (this.a.i.K) {
                    this.a.g.a(f);
                }
                d /= AdError.NETWORK_ERROR_CODE;
                if (this.b != d) {
                    this.b = d;
                    int i = d / 60;
                    d %= 60;
                    this.a.f.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(d)}));
                }
            }
            ah.a r = this.a.i.r();
            if (r.g()) {
                View b = r.b(true);
                if (b.getVisibility() == 8) {
                    this.a.i.a(true, b);
                    b.setEnabled(true);
                }
            }
            an.l.removeCallbacks(this.a.o);
            an.l.postDelayed(this.a.o, 16);
        }
    }

    class 5 implements Runnable {
        final /* synthetic */ an a;

        5(an anVar) {
            this.a = anVar;
        }

        public void run() {
            this.a.h.setVisibility(0);
        }
    }

    static {
        a = "00:00";
        l = CBUtility.e();
    }

    public an(Context context, ah ahVar) {
        super(context);
        this.j = false;
        this.k = false;
        this.m = new 2(this);
        this.n = new 3(this);
        this.o = new 4(this);
        this.i = ahVar;
        a(context);
    }

    private void a(Context context) {
        LayoutParams layoutParams;
        Context context2 = getContext();
        a g = this.i.g();
        float f = getContext().getResources().getDisplayMetrics().density;
        int round = Math.round(f * 10.0f);
        this.h = new bf(context2);
        this.i.r().a(this.h);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(13);
        addView(this.h, layoutParams2);
        this.b = new RelativeLayout(context2);
        if (g.c() && g.a("video-click-button").c()) {
            this.c = new am(context2);
            this.c.setVisibility(8);
            this.e = new 1(this, context2);
            this.e.a(ScaleType.FIT_CENTER);
            j jVar = this.i.G;
            Point b = this.i.b("video-click-button");
            LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams3.leftMargin = Math.round(((float) b.x) / jVar.g());
            layoutParams3.topMargin = Math.round(((float) b.y) / jVar.g());
            this.i.a(layoutParams3, jVar, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.e.a(jVar);
            this.c.addView(this.e, layoutParams3);
            layoutParams = new RelativeLayout.LayoutParams(-1, Math.round(((float) layoutParams3.height) + (10.0f * f)));
            layoutParams.addRule(10);
            this.b.addView(this.c, layoutParams);
        }
        this.d = new am(context2);
        this.d.setVisibility(8);
        layoutParams = new RelativeLayout.LayoutParams(-1, Math.round(32.5f * f));
        layoutParams.addRule(12);
        this.b.addView(this.d, layoutParams);
        this.d.setGravity(16);
        this.d.setPadding(round, round, round, round);
        this.f = new TextView(context2);
        this.f.setTextColor(-1);
        this.f.setTextSize(2, 11.0f);
        this.f.setText(a);
        this.f.setPadding(0, 0, round, 0);
        this.f.setSingleLine();
        this.f.measure(0, 0);
        int measuredWidth = this.f.getMeasuredWidth();
        this.f.setGravity(17);
        this.d.addView(this.f, new LinearLayout.LayoutParams(measuredWidth, -1));
        this.g = new aj(context2);
        this.g.setVisibility(8);
        LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, Math.round(10.0f * f));
        layoutParams4.setMargins(0, CBUtility.a(1, getContext()), 0, 0);
        this.d.addView(this.g, layoutParams4);
        layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams4.addRule(6, this.h.getId());
        layoutParams4.addRule(8, this.h.getId());
        layoutParams4.addRule(5, this.h.getId());
        layoutParams4.addRule(7, this.h.getId());
        addView(this.b, layoutParams4);
        a();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (this.e != null) {
            this.e.setEnabled(enabled);
        }
        if (enabled) {
            a(false);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        l.removeCallbacks(this.o);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent e) {
        if (!this.h.a().e() || e.getActionMasked() != 0) {
            return false;
        }
        if (this.i == null) {
            return true;
        }
        d(true);
        return true;
    }

    public void onCompletion(MediaPlayer arg0) {
        this.i.u = this.h.a().c();
        if (this.i.r() != null) {
            this.i.r().f();
        }
    }

    public void onPrepared(MediaPlayer mp) {
        this.i.v = this.h.a().c();
        this.i.r().a(true);
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        this.i.v();
        return false;
    }

    private void d(boolean z) {
        a(!this.j, z);
    }

    protected void a(boolean z, boolean z2) {
        l.removeCallbacks(this.m);
        l.removeCallbacks(this.n);
        if (this.i.w && this.i.q() && z != this.j) {
            this.j = z;
            Animation alphaAnimation = this.j ? new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) : new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
            alphaAnimation.setDuration(z2 ? 100 : 200);
            alphaAnimation.setFillAfter(true);
            if (!(this.k || this.c == null)) {
                this.c.setVisibility(0);
                this.c.startAnimation(alphaAnimation);
                if (this.e != null) {
                    this.e.setEnabled(true);
                }
            }
            if (this.i.K) {
                this.g.setVisibility(0);
            }
            this.d.setVisibility(0);
            this.d.startAnimation(alphaAnimation);
            if (this.j) {
                l.postDelayed(this.m, 3000);
            } else {
                l.postDelayed(this.n, alphaAnimation.getDuration());
            }
        }
    }

    public void a(boolean z) {
        l.removeCallbacks(this.m);
        l.removeCallbacks(this.n);
        if (z) {
            if (!(this.k || this.c == null)) {
                this.c.setVisibility(0);
            }
            if (this.i.K) {
                this.g.setVisibility(0);
            }
            this.d.setVisibility(0);
            if (this.e != null) {
                this.e.setEnabled(true);
            }
        } else {
            if (this.c != null) {
                this.c.clearAnimation();
                this.c.setVisibility(8);
            }
            this.d.clearAnimation();
            if (this.i.K) {
                this.g.setVisibility(8);
            }
            this.d.setVisibility(8);
            if (this.e != null) {
                this.e.setEnabled(false);
            }
        }
        this.j = z;
    }

    public void b(boolean z) {
        setBackgroundColor(z ? CtaButton.BACKGROUND_COLOR : 0);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (!z) {
            layoutParams.addRule(6, this.h.getId());
            layoutParams.addRule(8, this.h.getId());
            layoutParams.addRule(5, this.h.getId());
            layoutParams.addRule(7, this.h.getId());
        }
        this.b.setLayoutParams(layoutParams);
        if (this.c != null) {
            this.c.setGravity(19);
            this.c.requestLayout();
        }
    }

    public void a() {
        b(CBUtility.c().a());
    }

    public bf.a b() {
        return this.h.a();
    }

    public aj c() {
        return this.g;
    }

    public void a(int i) {
        if (this.c != null) {
            this.c.setBackgroundColor(i);
        }
        this.d.setBackgroundColor(i);
    }

    public void d() {
        if (this.c != null) {
            this.c.setVisibility(8);
        }
        this.k = true;
        if (this.e != null) {
            this.e.setEnabled(false);
        }
    }

    public void c(boolean z) {
        this.f.setVisibility(z ? 0 : 8);
    }

    public void a(String str) {
        this.h.a().a((OnCompletionListener) this);
        this.h.a().a((OnErrorListener) this);
        this.h.a().a((OnPreparedListener) this);
        this.h.a().a(Uri.parse(str));
    }

    public void e() {
        l.postDelayed(new 5(this), 500);
        this.h.a().a();
        l.removeCallbacks(this.o);
        l.postDelayed(this.o, 16);
    }

    public void f() {
        if (this.h.a().e()) {
            this.i.u = this.h.a().d();
            this.h.a().b();
        }
        if (this.i.r().d.getVisibility() == 0) {
            this.i.r().d.postInvalidate();
        }
        l.removeCallbacks(this.o);
    }

    public void g() {
        if (this.h.a().e()) {
            this.i.u = this.h.a().d();
        }
        this.h.a().b();
        l.removeCallbacks(this.o);
    }

    public void h() {
        this.h.setVisibility(8);
        invalidate();
    }

    public boolean i() {
        return this.h.a().e();
    }
}
