package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.Libraries.j;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.c;
import com.chartboost.sdk.g;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer.util.MimeTypes;
import com.mopub.mobileads.VastIconXmlManager;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class av extends g {
    private String A;
    protected j l;
    protected j m;
    private List<com.chartboost.sdk.Libraries.e.a> n;
    private j o;
    private j p;
    private j q;
    private j r;
    private j s;
    private j t;
    private j u;
    private Set<j> v;
    private int w;
    private com.chartboost.sdk.Libraries.e.a x;
    private int y;
    private int z;

    public class a extends com.chartboost.sdk.g.a {
        final /* synthetic */ av b;
        private bj c;
        private bi d;
        private TextView e;
        private RelativeLayout f;
        private ListView g;
        private a h;

        class 1 extends bj {
            final /* synthetic */ av a;
            final /* synthetic */ a b;

            1(a aVar, Context context, av avVar) {
                this.b = aVar;
                this.a = avVar;
                super(context);
            }

            protected void a(MotionEvent motionEvent) {
                this.b.b.h();
            }
        }

        public class a extends ArrayAdapter<com.chartboost.sdk.Libraries.e.a> {
            final /* synthetic */ a a;
            private Context b;

            class 1 implements OnClickListener {
                final /* synthetic */ com.chartboost.sdk.Libraries.e.a a;
                final /* synthetic */ ao b;
                final /* synthetic */ a c;

                1(a aVar, com.chartboost.sdk.Libraries.e.a aVar2, ao aoVar) {
                    this.c = aVar;
                    this.a = aVar2;
                    this.b = aoVar;
                }

                public void onClick(View v) {
                    String e = this.a.e("deep-link");
                    if (TextUtils.isEmpty(e) || !ba.a(e)) {
                        e = this.a.e(ShareConstants.WEB_DIALOG_PARAM_LINK);
                    }
                    if (VERSION.SDK_INT >= 11) {
                        this.a.a("x", Float.valueOf(this.c.a.getX()));
                        this.a.a("y", Float.valueOf(this.c.a.getY()));
                        this.a.a(VastIconXmlManager.WIDTH, Integer.valueOf(this.b.getHeight()));
                        this.a.a(VastIconXmlManager.HEIGHT, Integer.valueOf(this.b.getWidth()));
                    }
                    this.c.a.b.a(e, this.a);
                }
            }

            public /* synthetic */ Object getItem(int x0) {
                return a(x0);
            }

            public a(a aVar, Context context) {
                this.a = aVar;
                super(context, 0, aVar.b.n);
                this.b = context;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                int i = 0;
                com.chartboost.sdk.Libraries.e.a a = a(position);
                com.chartboost.sdk.Libraries.e.a a2 = a.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
                if (convertView == null) {
                    View view;
                    b[] values = b.values();
                    while (i < values.length) {
                        if (a2.equals(values[i].e)) {
                            try {
                                view = (ao) values[i].f.getConstructor(new Class[]{av.class, Context.class}).newInstance(new Object[]{this.a.b, this.b});
                                break;
                            } catch (Throwable e) {
                                CBLogging.b(this, "error in more apps list", e);
                                view = null;
                            }
                        } else {
                            i++;
                        }
                    }
                    view = null;
                    convertView = view;
                } else if (!(convertView instanceof ao)) {
                    return convertView;
                } else {
                    ao convertView2 = (ao) convertView;
                }
                if (convertView == null) {
                    return new View(getContext());
                }
                convertView.a(a, position);
                LayoutParams layoutParams = convertView.getLayoutParams();
                if (layoutParams == null || !(layoutParams instanceof AbsListView.LayoutParams)) {
                    layoutParams = new AbsListView.LayoutParams(-1, convertView.a());
                } else {
                    layoutParams = (AbsListView.LayoutParams) layoutParams;
                    layoutParams.width = -1;
                    layoutParams.height = convertView.a();
                }
                convertView.setLayoutParams(layoutParams);
                convertView.setOnClickListener(new 1(this, a, convertView));
                return convertView;
            }

            public int getCount() {
                return this.a.b.n.size();
            }

            public com.chartboost.sdk.Libraries.e.a a(int i) {
                return (com.chartboost.sdk.Libraries.e.a) this.a.b.n.get(i);
            }

            public int getItemViewType(int position) {
                com.chartboost.sdk.Libraries.e.a a = a(position).a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
                b[] values = b.values();
                for (int i = 0; i < values.length; i++) {
                    if (a.equals(values[i].e)) {
                        return i;
                    }
                }
                return 0;
            }

            public int getViewTypeCount() {
                return b.values().length;
            }
        }

        private a(av avVar, Context context) {
            this.b = avVar;
            super(avVar, context);
            setBackgroundColor(-1);
            this.d = new bi(context);
            this.c = new 1(this, context, avVar);
            this.e = new TextView(context);
            this.e.setBackgroundColor(avVar.y);
            this.e.setText(avVar.A);
            this.e.setTextColor(avVar.z);
            this.e.setTextSize(2, c() ? 30.0f : RadialCountdown.TEXT_SIZE_SP);
            this.e.setGravity(17);
            this.g = new ListView(context);
            this.g.setBackgroundColor(-1);
            this.g.setDividerHeight(0);
            a(this.g);
            addView(this.g);
            this.d.setFocusable(false);
            this.c.setFocusable(false);
            this.c.setClickable(true);
            this.d.setScaleType(ScaleType.CENTER_CROP);
            this.c.a(ScaleType.FIT_CENTER);
            this.f = new RelativeLayout(context);
            this.f.addView(this.d, new RelativeLayout.LayoutParams(-1, -1));
            this.f.addView(this.e, new RelativeLayout.LayoutParams(-1, -1));
            addView(this.f, new RelativeLayout.LayoutParams(-1, -1));
            addView(this.c, new RelativeLayout.LayoutParams(-1, -1));
            a(this.f);
            this.h = new a(this, context);
        }

        protected void a(int i, int i2) {
            j e;
            int i3;
            Context context = getContext();
            f c = CBUtility.c();
            if (c.a() && this.b.r.e()) {
                e = this.b.r;
            } else if (c.b() && this.b.s.e()) {
                e = this.b.s;
            } else if (this.b.u.e()) {
                e = this.b.u;
            } else {
                e = null;
            }
            if (e != null) {
                this.b.w = e.i();
                if (e.h() < i) {
                    this.b.w = Math.round(((float) this.b.w) * (((float) i) / ((float) e.h())));
                }
                this.e.setVisibility(8);
                this.d.a(e);
            } else {
                this.b.w = CBUtility.a(c() ? 80 : 40, context);
                this.e.setVisibility(0);
            }
            if (this.b.x.c()) {
                this.b.w = CBUtility.a(this.b.x.l(), context);
            }
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, this.b.w);
            if (this.b.p.e() && c.a()) {
                e = this.b.p;
            } else if (this.b.q.e() && c.b()) {
                e = this.b.q;
            } else if (this.b.o.e()) {
                e = this.b.o;
            } else {
                e = null;
            }
            if (e != null) {
                this.c.a(e, layoutParams2);
                if (c()) {
                    i3 = 14;
                } else {
                    i3 = 7;
                }
                i3 = CBUtility.a(i3, context);
            } else {
                this.c.a("X");
                this.c.a().setTextSize(2, c() ? 26.0f : 16.0f);
                this.c.a().setTextColor(this.b.z);
                this.c.a().setTypeface(Typeface.SANS_SERIF, 1);
                layoutParams2.width = this.b.w / 2;
                layoutParams2.height = this.b.w / 3;
                if (c()) {
                    i3 = 30;
                } else {
                    i3 = 20;
                }
                i3 = CBUtility.a(i3, context);
            }
            int round = Math.round(((float) (this.b.w - layoutParams2.height)) / 2.0f);
            layoutParams2.rightMargin = i3;
            layoutParams2.topMargin = round;
            layoutParams2.addRule(11);
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.addRule(3, this.f.getId());
            this.g.setAdapter(this.h);
            this.g.setLayoutParams(layoutParams);
            this.c.setLayoutParams(layoutParams2);
            this.f.setLayoutParams(layoutParams3);
        }

        public void b() {
            super.b();
            this.c = null;
            this.d = null;
            this.g = null;
        }
    }

    private enum b {
        FEATURED("featured", ap.class),
        REGULAR("regular", aq.class),
        WEBVIEW("webview", as.class),
        VIDEO(MimeTypes.BASE_TYPE_VIDEO, ar.class);
        
        private String e;
        private Class<? extends ao> f;

        private b(String str, Class<? extends ao> cls) {
            this.e = str;
            this.f = cls;
        }
    }

    public av(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
        this.n = new ArrayList();
        this.u = new j(this);
        this.s = new j(this);
        this.r = new j(this);
        this.t = new j(this);
        this.o = new j(this);
        this.q = new j(this);
        this.p = new j(this);
        this.m = new j(this);
        this.l = new j(this);
    }

    protected com.chartboost.sdk.g.a b(Context context) {
        return new a(context, null);
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        int i = 0;
        if (!super.a(aVar)) {
            return false;
        }
        com.chartboost.sdk.Libraries.e.a a = aVar.a("cells");
        if (a.b()) {
            a(CBImpressionError.INVALID_RESPONSE);
            return false;
        }
        this.v = new HashSet();
        while (i < a.p()) {
            com.chartboost.sdk.Libraries.e.a c = a.c(i);
            this.n.add(c);
            com.chartboost.sdk.Libraries.e.a a2 = c.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
            if (a2.equals("regular")) {
                c = c.a("assets");
                if (c.c()) {
                    a(c, "icon");
                }
            } else if (a2.equals("featured")) {
                c = c.a("assets");
                if (c.c()) {
                    a(c, DeviceInfo.ORIENTATION_PORTRAIT);
                    a(c, DeviceInfo.ORIENTATION_LANDSCAPE);
                }
            } else if (a2.equals("webview")) {
            }
            i++;
        }
        this.o.a(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE);
        this.q.a("close-landscape");
        this.p.a("close-portrait");
        this.u.a("header-center");
        this.r.a("header-portrait");
        this.s.a("header-landscape");
        this.t.a("header-tile");
        this.m.a("play-button");
        this.l.a("install-button");
        this.x = this.f.a("header-height");
        if (this.x.c()) {
            this.w = this.x.l();
        } else {
            this.w = g.a(c.y()) ? 80 : 40;
        }
        this.y = this.f.c("background-color") ? g.a(this.f.e("background-color")) : -14571545;
        this.A = this.f.c("header-text") ? this.f.e("header-text") : "More Free Games";
        this.z = this.f.c("text-color") ? g.a(this.f.e("text-color")) : -1;
        return true;
    }

    private void a(com.chartboost.sdk.Libraries.e.a aVar, String str) {
        if (!aVar.b(str)) {
            j jVar = new j(this);
            this.v.add(jVar);
            jVar.a(aVar, str, new Bundle());
        }
    }

    public void d() {
        super.d();
        this.n = null;
        for (j d : this.v) {
            d.d();
        }
        this.v.clear();
        this.o.d();
        this.q.d();
        this.p.d();
        this.u.d();
        this.t.d();
        this.r.d();
        this.s.d();
        this.m.d();
        this.l.d();
    }
}
