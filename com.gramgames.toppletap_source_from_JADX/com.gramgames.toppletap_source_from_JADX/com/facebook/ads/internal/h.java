package com.facebook.ads.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.BannerAdapter;
import com.facebook.ads.internal.adapters.BannerAdapterListener;
import com.facebook.ads.internal.adapters.InterstitialAdapter;
import com.facebook.ads.internal.adapters.InterstitialAdapterListener;
import com.facebook.ads.internal.adapters.f;
import com.facebook.ads.internal.adapters.p;
import com.facebook.ads.internal.adapters.q;
import com.facebook.ads.internal.dto.d;
import com.facebook.ads.internal.dto.e;
import com.facebook.ads.internal.server.AdPlacementType;
import com.facebook.ads.internal.util.g;
import com.facebook.ads.internal.util.s;
import com.facebook.ads.internal.util.u;
import com.google.android.gms.drive.DriveFile;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.parser.Yytoken;

public class h implements com.facebook.ads.internal.server.a.a {
    private static final String b;
    protected a a;
    private final Context c;
    private final String d;
    private final com.facebook.ads.internal.server.a e;
    private final Handler f;
    private final Runnable g;
    private final Runnable h;
    private volatile boolean i;
    private boolean j;
    private volatile boolean k;
    private AdAdapter l;
    private View m;
    private com.facebook.ads.internal.dto.c n;
    private e o;
    private e p;
    private c q;
    private AdSize r;
    private int s;
    private final c t;
    private boolean u;

    class 1 implements Runnable {
        final /* synthetic */ BannerAdapter a;
        final /* synthetic */ h b;

        1(h hVar, BannerAdapter bannerAdapter) {
            this.b = hVar;
            this.a = bannerAdapter;
        }

        public void run() {
            this.b.a(this.a);
            this.b.n();
        }
    }

    class 2 implements BannerAdapterListener {
        final /* synthetic */ Runnable a;
        final /* synthetic */ h b;

        2(h hVar, Runnable runnable) {
            this.b = hVar;
            this.a = runnable;
        }

        public void onBannerAdClicked(BannerAdapter bannerAdapter) {
            this.b.k();
            this.b.a.b();
        }

        public void onBannerAdExpanded(BannerAdapter bannerAdapter) {
            this.b.k();
            this.b.p();
        }

        public void onBannerAdLoaded(BannerAdapter bannerAdapter, View view) {
            this.b.k();
            this.b.f.removeCallbacks(this.a);
            AdAdapter f = this.b.l;
            this.b.l = bannerAdapter;
            this.b.m = view;
            if (this.b.k) {
                this.b.a.a(view);
                this.b.a(f);
                this.b.o();
                return;
            }
            this.b.a.a();
        }

        public void onBannerAdMinimized(BannerAdapter bannerAdapter) {
            this.b.k();
            this.b.o();
        }

        public void onBannerError(BannerAdapter bannerAdapter, AdError adError) {
            this.b.k();
            this.b.f.removeCallbacks(this.a);
            this.b.a((AdAdapter) bannerAdapter);
            this.b.n();
        }

        public void onBannerLoggingImpression(BannerAdapter bannerAdapter) {
            this.b.k();
            this.b.a.c();
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ InterstitialAdapter a;
        final /* synthetic */ h b;

        3(h hVar, InterstitialAdapter interstitialAdapter) {
            this.b = hVar;
            this.a = interstitialAdapter;
        }

        public void run() {
            this.b.a(this.a);
            this.b.n();
        }
    }

    class 4 implements InterstitialAdapterListener {
        final /* synthetic */ Runnable a;
        final /* synthetic */ h b;

        4(h hVar, Runnable runnable) {
            this.b = hVar;
            this.a = runnable;
        }

        public void onInterstitialAdClicked(InterstitialAdapter interstitialAdapter, String str, boolean z) {
            this.b.k();
            this.b.a.b();
            Object obj = !s.a(str) ? 1 : null;
            if (z && obj != null) {
                Intent intent = new Intent("android.intent.action.VIEW");
                if (!(this.b.o.d instanceof Activity)) {
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                }
                intent.setData(Uri.parse(str));
                this.b.o.d.startActivity(intent);
            }
        }

        public void onInterstitialAdDismissed(InterstitialAdapter interstitialAdapter) {
            this.b.k();
            this.b.a.e();
        }

        public void onInterstitialAdDisplayed(InterstitialAdapter interstitialAdapter) {
            this.b.k();
            this.b.a.d();
        }

        public void onInterstitialAdLoaded(InterstitialAdapter interstitialAdapter) {
            this.b.k();
            this.b.f.removeCallbacks(this.a);
            this.b.l = interstitialAdapter;
            this.b.a.a();
            this.b.o();
        }

        public void onInterstitialError(InterstitialAdapter interstitialAdapter, AdError adError) {
            this.b.k();
            this.b.f.removeCallbacks(this.a);
            this.b.a((AdAdapter) interstitialAdapter);
            this.b.n();
        }

        public void onInterstitialLoggingImpression(InterstitialAdapter interstitialAdapter) {
            this.b.k();
            this.b.a.c();
        }
    }

    class 5 implements Runnable {
        final /* synthetic */ p a;
        final /* synthetic */ h b;

        5(h hVar, p pVar) {
            this.b = hVar;
            this.a = pVar;
        }

        public void run() {
            this.b.a(this.a);
            this.b.n();
        }
    }

    class 6 implements q {
        final /* synthetic */ Runnable a;
        final /* synthetic */ h b;

        6(h hVar, Runnable runnable) {
            this.b = hVar;
            this.a = runnable;
        }

        public void a(p pVar) {
            this.b.k();
            this.b.f.removeCallbacks(this.a);
            this.b.l = pVar;
            this.b.a.a();
        }

        public void a(p pVar, AdError adError) {
            this.b.k();
            this.b.f.removeCallbacks(this.a);
            this.b.a((AdAdapter) pVar);
            this.b.n();
        }
    }

    static /* synthetic */ class 7 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[AdPlacementType.values().length];
            try {
                a[AdPlacementType.INTERSTITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[AdPlacementType.BANNER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[AdPlacementType.NATIVE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private static final class a extends u<h> {
        public a(h hVar) {
            super(hVar);
        }

        public void run() {
            h hVar = (h) a();
            if (hVar != null) {
                hVar.i = false;
                hVar.m();
            }
        }
    }

    private static final class b extends u<h> {
        public b(h hVar) {
            super(hVar);
        }

        public void run() {
            h hVar = (h) a();
            if (hVar != null) {
                hVar.o();
            }
        }
    }

    private class c extends BroadcastReceiver {
        final /* synthetic */ h a;

        private c(h hVar) {
            this.a = hVar;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                this.a.p();
            } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                this.a.o();
            }
        }
    }

    static {
        b = h.class.getSimpleName();
    }

    public h(Context context, String str, e eVar, AdSize adSize, c cVar, int i, boolean z) {
        this.c = context;
        this.d = str;
        this.p = eVar;
        this.r = adSize;
        this.q = cVar;
        this.s = i;
        this.t = new c();
        this.e = new com.facebook.ads.internal.server.a();
        this.e.a((com.facebook.ads.internal.server.a.a) this);
        this.f = new Handler();
        this.g = new a(this);
        this.h = new b(this);
        this.j = z;
        i();
    }

    private void a(AdAdapter adAdapter) {
        if (adAdapter != null) {
            adAdapter.onDestroy();
        }
    }

    private void i() {
        if (!this.j) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.c.registerReceiver(this.t, intentFilter);
            this.u = true;
        }
    }

    private void j() {
        if (this.u) {
            try {
                this.c.unregisterReceiver(this.t);
                this.u = false;
            } catch (Throwable e) {
                com.facebook.ads.internal.util.c.a(com.facebook.ads.internal.util.b.a(e, "Error unregistering screen state receiever"));
            }
        }
    }

    private void k() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("Adapter listener must be called on the main thread.");
        }
    }

    private AdPlacementType l() {
        return this.r == null ? AdPlacementType.NATIVE : this.r == AdSize.INTERSTITIAL ? AdPlacementType.INTERSTITIAL : AdPlacementType.BANNER;
    }

    private void m() {
        this.o = new e(this.c, this.d, this.r, this.p, this.q, this.s, AdSettings.isTestMode(this.c));
        this.e.a(this.c, this.o);
    }

    private void n() {
        com.facebook.ads.internal.dto.c cVar = this.n;
        com.facebook.ads.internal.dto.a c = cVar.c();
        if (c == null) {
            this.a.a(AdErrorType.NO_FILL.getAdErrorWrapper(BuildConfig.FLAVOR));
            o();
            return;
        }
        String str = c.b;
        AdAdapter a = f.a(str, cVar.a().a());
        if (a == null) {
            Log.e(b, "Adapter does not exist: " + str);
            n();
        } else if (l() != a.getPlacementType()) {
            this.a.a(AdErrorType.INTERNAL_ERROR.getAdErrorWrapper(BuildConfig.FLAVOR));
        } else {
            Map hashMap = new HashMap();
            d a2 = cVar.a();
            hashMap.put(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY, c.c);
            hashMap.put("definition", a2);
            if (this.o == null) {
                this.a.a(AdErrorType.UNKNOWN_ERROR.getAdErrorWrapper("environment is empty"));
            }
            Runnable 3;
            switch (7.a[a.getPlacementType().ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    InterstitialAdapter interstitialAdapter = (InterstitialAdapter) a;
                    3 = new 3(this, interstitialAdapter);
                    this.f.postDelayed(3, 10000);
                    interstitialAdapter.loadInterstitialAd(this.c, new 4(this, 3), hashMap);
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    BannerAdapter bannerAdapter = (BannerAdapter) a;
                    3 = new 1(this, bannerAdapter);
                    this.f.postDelayed(3, 10000);
                    bannerAdapter.loadBannerAd(this.c, this.r, new 2(this, 3), hashMap);
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    p pVar = (p) a;
                    3 = new 5(this, pVar);
                    this.f.postDelayed(3, 10000);
                    pVar.a(this.c, new 6(this, 3), hashMap);
                default:
                    Log.e(b, "attempt unexpected adapter type");
            }
        }
    }

    private void o() {
        if (!this.j && !this.i) {
            switch (7.a[l().ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    if (!g.a(this.c)) {
                        this.f.postDelayed(this.h, 1000);
                        break;
                    }
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    int e = this.n == null ? 1 : this.n.a().e();
                    if (!(this.m == null || g.a(this.c, this.m, e))) {
                        this.f.postDelayed(this.h, 1000);
                        return;
                    }
                default:
                    return;
            }
            long b = this.n == null ? 30000 : this.n.a().b();
            if (b > 0) {
                this.f.postDelayed(this.g, b);
                this.i = true;
            }
        }
    }

    private void p() {
        if (this.i) {
            this.f.removeCallbacks(this.g);
            this.i = false;
        }
    }

    public d a() {
        return this.n == null ? null : this.n.a();
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(b bVar) {
        this.a.a(bVar);
        if (!this.j && !this.i) {
            switch (bVar.a().getErrorCode()) {
                case AdError.NETWORK_ERROR_CODE /*1000*/:
                case AdError.LOAD_TOO_FREQUENTLY_ERROR_CODE /*1002*/:
                    switch (7.a[l().ordinal()]) {
                        case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                            this.f.postDelayed(this.g, 30000);
                            this.i = true;
                        default:
                    }
                default:
            }
        }
    }

    public void a(com.facebook.ads.internal.server.d dVar) {
        com.facebook.ads.internal.dto.c b = dVar.b();
        if (b == null || b.a() == null) {
            throw new IllegalStateException("invalid placement in response");
        }
        this.n = b;
        n();
    }

    public void b() {
        m();
    }

    public void c() {
        if (this.l == null) {
            throw new IllegalStateException("no adapter ready to start");
        } else if (this.k) {
            throw new IllegalStateException("ad already started");
        } else {
            this.k = true;
            switch (7.a[this.l.getPlacementType().ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    ((InterstitialAdapter) this.l).show();
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    if (this.m != null) {
                        this.a.a(this.m);
                        o();
                    }
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    p pVar = (p) this.l;
                    if (pVar.w()) {
                        this.a.a(pVar);
                        return;
                    }
                    throw new IllegalStateException("ad is not ready or already displayed");
                default:
                    Log.e(b, "start unexpected adapter type");
            }
        }
    }

    public void d() {
        j();
        if (this.k) {
            p();
            a(this.l);
            this.m = null;
            this.k = false;
        }
    }

    public void e() {
        if (this.k) {
            p();
        }
    }

    public void f() {
        if (this.k) {
            o();
        }
    }

    public void g() {
        p();
        m();
    }

    public void h() {
        this.j = true;
        p();
    }
}
