package com.facebook.ads;

import android.content.Context;
import com.facebook.ads.NativeAd.MediaCacheFlag;
import com.facebook.ads.internal.adapters.p;
import com.facebook.ads.internal.b;
import com.facebook.ads.internal.c;
import com.facebook.ads.internal.e;
import com.facebook.ads.internal.i;
import com.facebook.ads.internal.i.a;
import com.facebook.ads.internal.util.l;
import com.facebook.ads.internal.util.m;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class NativeAdsManager {
    private static final c a;
    private final Context b;
    private final String c;
    private final int d;
    private final List<NativeAd> e;
    private int f;
    private Listener g;
    private i h;
    private boolean i;
    private boolean j;

    class 1 implements a {
        final /* synthetic */ EnumSet a;
        final /* synthetic */ NativeAdsManager b;

        class 1 implements l {
            final /* synthetic */ NativeAd[] a;
            final /* synthetic */ int b;
            final /* synthetic */ List c;
            final /* synthetic */ int[] d;
            final /* synthetic */ 1 e;

            1(1 1, NativeAd[] nativeAdArr, int i, List list, int[] iArr) {
                this.e = 1;
                this.a = nativeAdArr;
                this.b = i;
                this.c = list;
                this.d = iArr;
            }

            public void a() {
                this.a[this.b] = new NativeAd(this.e.b.b, (p) this.c.get(this.b), null);
                int[] iArr = this.d;
                iArr[0] = iArr[0] + 1;
                if (this.d[0] == this.c.size()) {
                    this.e.b.j = true;
                    this.e.b.e.clear();
                    this.e.b.f = 0;
                    for (Object obj : this.a) {
                        if (obj != null) {
                            this.e.b.e.add(obj);
                        }
                    }
                    if (this.e.b.g != null) {
                        this.e.b.g.onAdsLoaded();
                    }
                }
            }
        }

        1(NativeAdsManager nativeAdsManager, EnumSet enumSet) {
            this.b = nativeAdsManager;
            this.a = enumSet;
        }

        public void a(b bVar) {
            if (this.b.g != null) {
                this.b.g.onAdError(bVar.b());
            }
        }

        public void a(List<p> list) {
            int i = 0;
            NativeAd[] nativeAdArr = new NativeAd[list.size()];
            int[] iArr = new int[]{0};
            while (i < list.size()) {
                p pVar = (p) list.get(i);
                List arrayList = new ArrayList(2);
                if (this.a.contains(MediaCacheFlag.ICON) && pVar.h() != null) {
                    arrayList.add(pVar.h().getUrl());
                }
                if (this.a.contains(MediaCacheFlag.IMAGE) && pVar.i() != null) {
                    arrayList.add(pVar.i().getUrl());
                }
                m.a(this.b.b, arrayList, new 1(this, nativeAdArr, i, list, iArr));
                i++;
            }
        }
    }

    public interface Listener {
        void onAdError(AdError adError);

        void onAdsLoaded();
    }

    static {
        a = c.ADS;
    }

    public NativeAdsManager(Context context, String str, int i) {
        this.b = context;
        this.c = str;
        this.d = Math.max(i, 0);
        this.e = new ArrayList(i);
        this.f = -1;
        this.j = false;
        this.i = false;
    }

    public void disableAutoRefresh() {
        this.i = true;
        if (this.h != null) {
            this.h.c();
        }
    }

    public int getUniqueNativeAdCount() {
        return this.e.size();
    }

    public boolean isLoaded() {
        return this.j;
    }

    public void loadAds() {
        loadAds(EnumSet.of(MediaCacheFlag.NONE));
    }

    public void loadAds(EnumSet<MediaCacheFlag> enumSet) {
        e eVar = e.NATIVE_UNKNOWN;
        int i = this.d;
        if (this.h != null) {
            this.h.b();
        }
        this.h = new i(this.b, this.c, eVar, null, a, i, enumSet);
        if (this.i) {
            this.h.c();
        }
        this.h.a(new 1(this, enumSet));
        this.h.a();
    }

    public NativeAd nextNativeAd() {
        if (this.e.size() == 0) {
            return null;
        }
        int i = this.f;
        this.f = i + 1;
        NativeAd nativeAd = (NativeAd) this.e.get(i % this.e.size());
        return i >= this.e.size() ? new NativeAd(nativeAd) : nativeAd;
    }

    public void setListener(Listener listener) {
        this.g = listener;
    }
}
