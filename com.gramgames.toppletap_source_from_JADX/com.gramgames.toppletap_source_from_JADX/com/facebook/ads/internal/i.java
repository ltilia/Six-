package com.facebook.ads.internal;

import android.content.Context;
import android.os.Handler;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.NativeAd.MediaCacheFlag;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.f;
import com.facebook.ads.internal.adapters.p;
import com.facebook.ads.internal.adapters.q;
import com.facebook.ads.internal.dto.c;
import com.facebook.ads.internal.dto.e;
import com.facebook.ads.internal.server.AdPlacementType;
import com.facebook.ads.internal.server.d;
import com.facebook.ads.internal.util.g;
import com.facebook.ads.internal.util.u;
import com.google.android.exoplayer.hls.HlsChunkSource;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class i implements com.facebook.ads.internal.server.a.a {
    private final Context a;
    private final String b;
    private final com.facebook.ads.internal.server.a c;
    private final e d;
    private final c e;
    private final AdSize f;
    private final int g;
    private boolean h;
    private final Handler i;
    private final Runnable j;
    private a k;
    private c l;

    public interface a {
        void a(b bVar);

        void a(List<p> list);
    }

    class 1 implements q {
        final /* synthetic */ List a;
        final /* synthetic */ i b;

        1(i iVar, List list) {
            this.b = iVar;
            this.a = list;
        }

        public void a(p pVar) {
            this.a.add(pVar);
        }

        public void a(p pVar, AdError adError) {
        }
    }

    private static final class b extends u<i> {
        public b(i iVar) {
            super(iVar);
        }

        public void run() {
            i iVar = (i) a();
            if (iVar != null) {
                if (g.a(iVar.a)) {
                    iVar.a();
                } else {
                    iVar.i.postDelayed(iVar.j, HlsChunkSource.DEFAULT_MIN_BUFFER_TO_SWITCH_UP_MS);
                }
            }
        }
    }

    public i(Context context, String str, e eVar, AdSize adSize, c cVar, int i, EnumSet<MediaCacheFlag> enumSet) {
        this.a = context;
        this.b = str;
        this.d = eVar;
        this.f = adSize;
        this.e = cVar;
        this.g = i;
        this.c = new com.facebook.ads.internal.server.a();
        this.c.a((com.facebook.ads.internal.server.a.a) this);
        this.h = true;
        this.i = new Handler();
        this.j = new b(this);
    }

    private List<p> d() {
        c cVar = this.l;
        com.facebook.ads.internal.dto.a c = cVar.c();
        List<p> arrayList = new ArrayList(cVar.b());
        for (com.facebook.ads.internal.dto.a aVar = c; aVar != null; aVar = cVar.c()) {
            AdAdapter a = f.a(aVar.b, AdPlacementType.NATIVE);
            if (a != null && a.getPlacementType() == AdPlacementType.NATIVE) {
                Map hashMap = new HashMap();
                hashMap.put(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY, aVar.c);
                hashMap.put("definition", cVar.a());
                ((p) a).a(this.a, new 1(this, arrayList), hashMap);
            }
        }
        return arrayList;
    }

    public void a() {
        this.c.a(this.a, new e(this.a, this.b, this.f, this.d, this.e, this.g, AdSettings.isTestMode(this.a)));
    }

    public void a(b bVar) {
        if (this.h) {
            this.i.postDelayed(this.j, 1800000);
        }
        if (this.k != null) {
            this.k.a(bVar);
        }
    }

    public void a(a aVar) {
        this.k = aVar;
    }

    public void a(d dVar) {
        c b = dVar.b();
        if (b == null) {
            throw new IllegalStateException("no placement in response");
        }
        if (this.h) {
            long b2 = b.a().b();
            if (b2 == 0) {
                b2 = 1800000;
            }
            this.i.postDelayed(this.j, b2);
        }
        this.l = b;
        List d = d();
        if (this.k == null) {
            return;
        }
        if (d.isEmpty()) {
            this.k.a(AdErrorType.NO_FILL.getAdErrorWrapper(BuildConfig.FLAVOR));
        } else {
            this.k.a(d);
        }
    }

    public void b() {
    }

    public void c() {
        this.h = false;
        this.i.removeCallbacks(this.j);
    }
}
