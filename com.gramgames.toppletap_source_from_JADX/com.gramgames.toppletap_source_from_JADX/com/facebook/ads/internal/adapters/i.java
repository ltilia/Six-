package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.internal.dto.d;
import com.facebook.ads.internal.util.b;
import com.facebook.ads.internal.util.c;
import com.facebook.ads.internal.util.f;
import com.facebook.ads.internal.util.h;
import com.facebook.ads.internal.view.a;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class i extends BannerAdapter {
    private static final String a;
    private a b;
    private m c;
    private BannerAdapterListener d;
    private Map<String, Object> e;
    private k f;
    private Context g;
    private long h;
    private b.a i;

    class 1 implements a.a {
        final /* synthetic */ l a;
        final /* synthetic */ i b;

        1(i iVar, l lVar) {
            this.b = iVar;
            this.a = lVar;
        }

        public void a() {
            this.b.c.c();
        }

        public void a(int i) {
            if (i == 0 && this.b.h > 0 && this.b.i != null) {
                c.a(b.a(this.b.h, this.b.i, this.a.i()));
                this.b.h = 0;
                this.b.i = null;
            }
        }

        public void a(String str) {
            if (this.b.d != null) {
                this.b.d.onBannerAdClicked(this.b);
            }
            com.facebook.ads.internal.action.a a = com.facebook.ads.internal.action.b.a(this.b.g, Uri.parse(str));
            if (a != null) {
                try {
                    this.b.i = a.a();
                    this.b.h = System.currentTimeMillis();
                    a.b();
                } catch (Throwable e) {
                    Log.e(i.a, "Error executing action", e);
                }
            }
        }

        public void b() {
            this.b.onViewableImpression();
        }
    }

    class 2 extends c {
        final /* synthetic */ i a;

        2(i iVar) {
            this.a = iVar;
        }

        public void d() {
            if (this.a.d != null) {
                this.a.d.onBannerLoggingImpression(this.a);
            }
        }
    }

    static {
        a = i.class.getSimpleName();
    }

    private void a(d dVar) {
        this.h = 0;
        this.i = null;
        l a = l.a((JSONObject) this.e.get(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY));
        if (f.a(this.g, (a) a)) {
            this.d.onBannerError(this, AdError.NO_FILL);
            return;
        }
        this.b = new a(this.g, new 1(this, a), dVar.e());
        this.b.a(dVar.f(), dVar.g());
        this.c = new m(this.g, this.b, new 2(this));
        this.c.a(a);
        this.b.loadDataWithBaseURL(h.a(), a.d(), WebRequest.CONTENT_TYPE_HTML, "utf-8", null);
        if (this.d != null) {
            this.d.onBannerAdLoaded(this, this.b);
        }
    }

    public void loadBannerAd(Context context, AdSize adSize, BannerAdapterListener bannerAdapterListener, Map<String, Object> map) {
        this.g = context;
        this.d = bannerAdapterListener;
        this.e = map;
        a((d) map.get("definition"));
    }

    public void onDestroy() {
        if (this.b != null) {
            h.a(this.b);
            this.b.destroy();
            this.b = null;
        }
        if (this.f != null) {
            this.f.onDestroy();
            this.f = null;
        }
    }

    public void onViewableImpression() {
        if (this.c != null) {
            this.c.a();
        } else if (this.f != null) {
            Map hashMap = new HashMap();
            hashMap.put("mil", Boolean.valueOf(false));
            this.f.a(hashMap);
        }
    }
}
