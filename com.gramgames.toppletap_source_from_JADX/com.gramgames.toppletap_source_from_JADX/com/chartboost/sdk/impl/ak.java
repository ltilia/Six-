package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.g;
import com.google.android.exoplayer.text.Cue;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.volley.DefaultRetryPolicy;

public final class ak extends LinearLayout {
    private ah a;
    private LinearLayout b;
    private bi c;
    private TextView d;
    private bj e;
    private int f;

    class 1 extends bj {
        final /* synthetic */ ak a;

        1(ak akVar, Context context) {
            this.a = akVar;
            super(context);
        }

        protected void a(MotionEvent motionEvent) {
            this.a.e.setEnabled(false);
            this.a.a.r().h();
        }
    }

    public ak(Context context, ah ahVar) {
        super(context);
        this.f = Cue.TYPE_UNSET;
        this.a = ahVar;
        a(context);
    }

    private void a(Context context) {
        Context context2 = getContext();
        int round = Math.round(getContext().getResources().getDisplayMetrics().density * CloseButton.STROKE_WIDTH);
        setOrientation(1);
        setGravity(17);
        this.b = new LinearLayout(context2);
        this.b.setGravity(17);
        this.b.setOrientation(0);
        this.b.setPadding(round, round, round, round);
        this.c = new bi(context2);
        this.c.setScaleType(ScaleType.FIT_CENTER);
        this.c.setPadding(0, 0, round, 0);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.a.a(layoutParams, this.a.F, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        this.d = new TextView(getContext());
        this.d.setTextColor(-1);
        this.d.setTypeface(null, 1);
        this.d.setGravity(17);
        this.d.setTextSize(2, g.a(context) ? 26.0f : 16.0f);
        this.b.addView(this.c, layoutParams);
        this.b.addView(this.d, new LinearLayout.LayoutParams(-2, -2));
        this.e = new 1(this, getContext());
        this.e.setPadding(0, 0, 0, round);
        this.e.a(ScaleType.FIT_CENTER);
        this.e.setPadding(round, round, round, round);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        this.a.a(layoutParams2, this.a.E, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        if (this.a.F.e()) {
            this.c.a(this.a.F);
        }
        if (this.a.E.e()) {
            this.e.a(this.a.E);
        }
        addView(this.b, new LinearLayout.LayoutParams(-2, -2));
        addView(this.e, layoutParams2);
        a();
    }

    public void a(boolean z) {
        setBackgroundColor(z ? CtaButton.BACKGROUND_COLOR : this.f);
    }

    public void a(String str, int i) {
        this.d.setText(str);
        this.f = i;
        a(this.a.t());
    }

    public void a() {
        a(this.a.t());
    }
}
