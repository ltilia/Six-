package com.facebook.ads;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.a;
import com.facebook.ads.internal.b;
import com.facebook.ads.internal.c;
import com.facebook.ads.internal.h;
import com.facebook.ads.internal.util.g;

public class AdView extends RelativeLayout implements Ad {
    private static final c a;
    private final DisplayMetrics b;
    private final AdSize c;
    private h d;
    private volatile boolean e;
    private AdListener f;
    private ImpressionListener g;
    private View h;
    private boolean i;

    class 1 extends a {
        final /* synthetic */ AdView a;

        1(AdView adView) {
            this.a = adView;
        }

        public void a() {
            if (this.a.d != null) {
                this.a.d.c();
            }
        }

        public void a(View view) {
            if (view == null) {
                throw new IllegalStateException("Cannot present null view");
            }
            this.a.i = true;
            this.a.h = view;
            this.a.removeAllViews();
            this.a.addView(this.a.h);
            if (this.a.h instanceof com.facebook.ads.internal.view.a) {
                g.a(this.a.b, this.a.h, this.a.c);
            }
            if (this.a.f != null) {
                this.a.f.onAdLoaded(this.a);
            }
        }

        public void a(b bVar) {
            if (this.a.f != null) {
                this.a.f.onError(this.a, bVar.b());
            }
        }

        public void b() {
            if (this.a.f != null) {
                this.a.f.onAdClicked(this.a);
            }
        }

        public void c() {
            if (this.a.g != null) {
                this.a.g.onLoggingImpression(this.a);
            }
            if ((this.a.f instanceof ImpressionListener) && this.a.f != this.a.g) {
                ((ImpressionListener) this.a.f).onLoggingImpression(this.a);
            }
        }
    }

    static {
        a = c.ADS;
    }

    public AdView(Context context, String str, AdSize adSize) {
        super(context);
        this.i = false;
        if (adSize == null || adSize == AdSize.INTERSTITIAL) {
            throw new IllegalArgumentException("adSize");
        }
        this.b = getContext().getResources().getDisplayMetrics();
        this.c = adSize;
        this.d = new h(context, str, g.a(adSize), adSize, a, 1, false);
        this.d.a(new 1(this));
    }

    public void destroy() {
        if (this.d != null) {
            this.d.d();
            this.d = null;
        }
        removeAllViews();
        this.h = null;
    }

    public void disableAutoRefresh() {
        if (this.d != null) {
            this.d.h();
        }
    }

    public void loadAd() {
        if (!this.e) {
            this.d.b();
            this.e = true;
        } else if (this.d != null) {
            this.d.g();
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.h != null) {
            g.a(this.b, this.h, this.c);
        }
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.d != null) {
            if (i == 0) {
                this.d.f();
            } else if (i == 8) {
                this.d.e();
            }
        }
    }

    public void setAdListener(AdListener adListener) {
        this.f = adListener;
    }

    @Deprecated
    public void setImpressionListener(ImpressionListener impressionListener) {
        this.g = impressionListener;
    }
}
