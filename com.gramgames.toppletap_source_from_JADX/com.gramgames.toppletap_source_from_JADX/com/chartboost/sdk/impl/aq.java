package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.Libraries.j;
import com.chartboost.sdk.g;
import com.facebook.share.internal.ShareConstants;
import com.mopub.mobileads.VastIconXmlManager;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.mopub.volley.DefaultRetryPolicy;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;

public class aq extends ao {
    private av a;
    private TextView b;
    private TextView c;
    private TextView d;
    private LinearLayout e;
    private at f;
    private bj g;
    private int h;
    private Point i;
    private j j;
    private OnClickListener k;

    class 1 extends bj {
        final /* synthetic */ aq a;

        1(aq aqVar, Context context) {
            this.a = aqVar;
            super(context);
        }

        protected void a(MotionEvent motionEvent) {
            this.a.k.onClick(this.a.g);
        }
    }

    public aq(av avVar, Context context) {
        super(context);
        this.a = avVar;
        this.e = new LinearLayout(context);
        this.e.setOrientation(1);
        setGravity(16);
        boolean a = g.a(context);
        this.b = new TextView(context);
        this.b.setTypeface(null, 1);
        this.b.setTextSize(2, a ? 21.0f : 16.0f);
        this.b.setTextColor(CtaButton.BACKGROUND_COLOR);
        this.b.setSingleLine();
        this.b.setEllipsize(TruncateAt.END);
        this.c = new TextView(context);
        this.c.setTextSize(2, a ? 16.0f : 10.0f);
        this.c.setTextColor(CtaButton.BACKGROUND_COLOR);
        this.c.setSingleLine();
        this.c.setEllipsize(TruncateAt.END);
        this.d = new TextView(context);
        this.d.setTextSize(2, a ? RadialCountdown.TEXT_SIZE_SP : 11.0f);
        this.d.setTextColor(CtaButton.BACKGROUND_COLOR);
        this.d.setMaxLines(2);
        this.d.setEllipsize(TruncateAt.END);
        this.g = new 1(this, context);
        this.g.a(ScaleType.FIT_CENTER);
        this.f = new at(context);
        setFocusable(false);
        setGravity(16);
        addView(this.f);
        addView(this.e, new LayoutParams(0, -2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        addView(this.g);
        setBackgroundColor(0);
        this.e.addView(this.b, new LayoutParams(-1, -2));
        this.e.addView(this.c, new LayoutParams(-1, -2));
        this.e.addView(this.d, new LayoutParams(-1, -1));
    }

    public void setOnClickListener(OnClickListener clickListener) {
        super.setOnClickListener(clickListener);
        this.k = clickListener;
    }

    public void a(a aVar, int i) {
        this.b.setText(aVar.a(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY).d("Unknown App"));
        if (TextUtils.isEmpty(aVar.e("publisher"))) {
            this.c.setVisibility(8);
        } else {
            this.c.setText(aVar.e("publisher"));
        }
        if (TextUtils.isEmpty(aVar.e(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION))) {
            this.d.setVisibility(8);
        } else {
            this.d.setText(aVar.e(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION));
        }
        this.h = aVar.b("border-color") ? -4802890 : g.a(aVar.e("border-color"));
        if (aVar.c(VastIconXmlManager.OFFSET)) {
            this.i = new Point(aVar.a(VastIconXmlManager.OFFSET).f("x"), aVar.a(VastIconXmlManager.OFFSET).f("y"));
        } else {
            this.i = new Point(0, 0);
        }
        this.j = null;
        if (aVar.c("deep-link") && ba.a(aVar.e("deep-link"))) {
            if (this.a.m.e()) {
                this.j = this.a.m;
            } else {
                this.g.a("Play");
            }
        } else if (this.a.l.e()) {
            this.j = this.a.l;
        } else {
            this.g.a("Install");
        }
        int a = CBUtility.a(g.a(getContext()) ? 14 : 7, getContext());
        if (this.j != null) {
            this.g.a(this.j);
            a = (a * 2) + Math.round((((float) this.j.b()) * ((float) c())) / ((float) this.j.c()));
        } else {
            this.g.a().setTextColor(-14571545);
            a = CBUtility.a(8, getContext());
            this.g.a().setPadding(a, a, a, a);
            a = CBUtility.a(100, getContext());
        }
        this.g.setLayoutParams(new LayoutParams(a, c()));
        removeView(this.f);
        this.f = new at(getContext());
        addView(this.f, 0);
        a(this.f, i, aVar.a("assets").a("icon"));
        this.f.a(this.h);
        this.f.a(0.16666667f);
        b();
    }

    private void a(bi biVar, int i, a aVar) {
        if (!aVar.b()) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            String str = BuildConfig.FLAVOR;
            if (!(aVar.e("checksum") == null || aVar.e("checksum").isEmpty())) {
                str = aVar.e("checksum");
            }
            bb.a().a(aVar.e(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY), str, null, biVar, bundle);
        }
    }

    protected void b() {
        int a = CBUtility.a(g.a(getContext()) ? 14 : 7, getContext());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(a() - (a * 2), a() - (a * 2));
        layoutParams.setMargins(a, a, a, a);
        this.e.setPadding(0, a, 0, a);
        this.g.setPadding((this.i.x * 2) + a, this.i.y * 2, a, 0);
        this.f.setLayoutParams(layoutParams);
        this.f.setScaleType(ScaleType.FIT_CENTER);
    }

    public int a() {
        int i = 134;
        if (CBUtility.c().b()) {
            if (!g.a(getContext())) {
                i = 75;
            }
        } else if (!g.a(getContext())) {
            i = 77;
        }
        return CBUtility.a(i, getContext());
    }

    private int c() {
        int i = 74;
        if (CBUtility.c().b()) {
            if (!g.a(getContext())) {
                i = 41;
            }
        } else if (!g.a(getContext())) {
            i = 41;
        }
        return CBUtility.a(i, getContext());
    }
}
