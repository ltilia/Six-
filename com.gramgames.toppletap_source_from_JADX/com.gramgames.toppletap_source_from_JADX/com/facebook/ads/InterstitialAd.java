package com.facebook.ads;

import android.content.Context;
import android.view.View;
import com.facebook.ads.internal.a;
import com.facebook.ads.internal.b;
import com.facebook.ads.internal.c;
import com.facebook.ads.internal.h;
import com.facebook.ads.internal.util.g;

public class InterstitialAd implements Ad {
    private static final c a;
    private final Context b;
    private final String c;
    private h d;
    private boolean e;
    private boolean f;
    private InterstitialAdListener g;
    private ImpressionListener h;

    class 1 extends a {
        final /* synthetic */ InterstitialAd a;

        1(InterstitialAd interstitialAd) {
            this.a = interstitialAd;
        }

        public void a() {
            this.a.e = true;
            if (this.a.g != null) {
                this.a.g.onAdLoaded(this.a);
            }
        }

        public void a(View view) {
        }

        public void a(b bVar) {
            if (this.a.g != null) {
                this.a.g.onError(this.a, bVar.b());
            }
        }

        public void b() {
            if (this.a.g != null) {
                this.a.g.onAdClicked(this.a);
            }
        }

        public void c() {
            if (this.a.h != null) {
                this.a.h.onLoggingImpression(this.a);
            }
            if ((this.a.g instanceof ImpressionListener) && this.a.g != this.a.h) {
                ((ImpressionListener) this.a.g).onLoggingImpression(this.a);
            }
        }

        public void d() {
            if (this.a.g != null) {
                this.a.g.onInterstitialDisplayed(this.a);
            }
        }

        public void e() {
            this.a.f = false;
            if (this.a.d != null) {
                this.a.d.d();
                this.a.d = null;
            }
            if (this.a.g != null) {
                this.a.g.onInterstitialDismissed(this.a);
            }
        }
    }

    static {
        a = c.ADS;
    }

    public InterstitialAd(Context context, String str) {
        this.b = context;
        this.c = str;
    }

    public void destroy() {
        if (this.d != null) {
            this.d.d();
            this.d = null;
        }
    }

    public boolean isAdLoaded() {
        return this.e;
    }

    public void loadAd() {
        this.e = false;
        if (this.f) {
            throw new IllegalStateException("InterstitialAd cannot be loaded while being displayed. Make sure your adapter calls adapterListener.onInterstitialDismissed().");
        }
        if (this.d != null) {
            this.d.d();
            this.d = null;
        }
        AdSize adSize = AdSize.INTERSTITIAL;
        this.d = new h(this.b, this.c, g.a(AdSize.INTERSTITIAL), adSize, a, 1, true);
        this.d.a(new 1(this));
        this.d.b();
    }

    public void setAdListener(InterstitialAdListener interstitialAdListener) {
        this.g = interstitialAdListener;
    }

    @Deprecated
    public void setImpressionListener(ImpressionListener impressionListener) {
        this.h = impressionListener;
    }

    public boolean show() {
        if (this.e) {
            this.d.c();
            this.f = true;
            this.e = false;
            return true;
        } else if (this.g == null) {
            return false;
        } else {
            this.g.onError(this, AdError.INTERNAL_ERROR);
            return false;
        }
    }
}
