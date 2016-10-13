package com.facebook.ads.internal.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.internal.adapters.c;
import com.facebook.ads.internal.adapters.l;
import com.facebook.ads.internal.adapters.m;
import com.facebook.ads.internal.util.b;
import com.facebook.ads.internal.util.h;
import com.facebook.ads.internal.view.h.a;
import com.unity3d.ads.android.properties.UnityAdsConstants;

public class f implements h {
    private static final String a;
    private a b;
    private a c;
    private l d;
    private m e;
    private long f;
    private long g;
    private b.a h;

    class 1 implements a.a {
        final /* synthetic */ InterstitialAdActivity a;
        final /* synthetic */ f b;

        1(f fVar, InterstitialAdActivity interstitialAdActivity) {
            this.b = fVar;
            this.a = interstitialAdActivity;
        }

        public void a() {
            this.b.e.c();
        }

        public void a(int i) {
        }

        public void a(String str) {
            Uri parse = Uri.parse(str);
            if ("fbad".equals(parse.getScheme()) && UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE.equals(parse.getAuthority())) {
                this.a.finish();
                return;
            }
            this.b.b.a("com.facebook.ads.interstitial.clicked");
            com.facebook.ads.internal.action.a a = com.facebook.ads.internal.action.b.a(this.a, parse);
            if (a != null) {
                try {
                    this.b.h = a.a();
                    this.b.g = System.currentTimeMillis();
                    a.b();
                } catch (Throwable e) {
                    Log.e(f.a, "Error executing action", e);
                }
            }
        }

        public void b() {
            this.b.e.a();
        }
    }

    class 2 extends c {
        final /* synthetic */ f a;

        2(f fVar) {
            this.a = fVar;
        }

        public void d() {
            this.a.b.a("com.facebook.ads.interstitial.impression.logged");
        }
    }

    static {
        a = f.class.getSimpleName();
    }

    public f(InterstitialAdActivity interstitialAdActivity, a aVar) {
        this.b = aVar;
        this.f = System.currentTimeMillis();
        this.c = new a(interstitialAdActivity, new 1(this, interstitialAdActivity), 1);
        this.c.setId(100001);
        this.c.setLayoutParams(new LayoutParams(-1, -1));
        this.e = new m(interstitialAdActivity, this.c, new 2(this));
        this.e.d();
        aVar.a(this.c);
    }

    public void a() {
        if (this.c != null) {
            this.c.onPause();
        }
    }

    public void a(Intent intent, Bundle bundle) {
        if (bundle == null || !bundle.containsKey("dataModel")) {
            this.d = l.b(intent);
            if (this.d != null) {
                this.e.a(this.d);
                this.c.loadDataWithBaseURL(h.a(), this.d.d(), WebRequest.CONTENT_TYPE_HTML, "utf-8", null);
                this.c.a(this.d.j(), this.d.k());
                return;
            }
            return;
        }
        this.d = l.a(bundle.getBundle("dataModel"));
        if (this.d != null) {
            this.c.loadDataWithBaseURL(h.a(), this.d.d(), WebRequest.CONTENT_TYPE_HTML, "utf-8", null);
            this.c.a(this.d.j(), this.d.k());
        }
    }

    public void a(Bundle bundle) {
        if (this.d != null) {
            bundle.putBundle("dataModel", this.d.l());
        }
    }

    public void b() {
        if (!(this.g <= 0 || this.h == null || this.d == null)) {
            com.facebook.ads.internal.util.c.a(b.a(this.g, this.h, this.d.i()));
        }
        if (this.c != null) {
            this.c.onResume();
        }
    }

    public void c() {
        if (this.d != null) {
            com.facebook.ads.internal.util.c.a(b.a(this.f, b.a.XOUT, this.d.i()));
        }
        if (this.c != null) {
            h.a(this.c);
            this.c.destroy();
            this.c = null;
        }
    }
}
