package com.chartboost.sdk.InPlay;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.area730.localnotif.NotificationReciever;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.d;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.ay.c;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.ba;
import com.chartboost.sdk.impl.s;
import com.chartboost.sdk.impl.z;
import com.facebook.share.internal.ShareConstants;
import com.mopub.mobileads.GooglePlayServicesInterstitial;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class a {
    private static final String a;
    private static ArrayList<CBInPlay> b;
    private static int c;
    private static a d;
    private static LinkedHashMap<String, Bitmap> e;
    private static volatile boolean f;

    class 1 implements c {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;
        final /* synthetic */ a c;

        1(a aVar, String str, boolean z) {
            this.c = aVar;
            this.a = str;
            this.b = z;
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar) {
            a.f = false;
            if (aVar.c()) {
                CBInPlay cBInPlay = new CBInPlay();
                cBInPlay.a(aVar);
                cBInPlay.b(aVar.e(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY));
                if (!TextUtils.isEmpty(this.a)) {
                    cBInPlay.a(this.a);
                }
                com.chartboost.sdk.Libraries.e.a a = aVar.a("icons");
                if (a.c() && !TextUtils.isEmpty(a.e("lg"))) {
                    String e = a.e("lg");
                    if (a.e.get(e) == null) {
                        com.chartboost.sdk.impl.n.b bVar = new b(null);
                        com.chartboost.sdk.impl.n.a aVar2 = new a(null);
                        bVar.c = cBInPlay;
                        bVar.b = e;
                        bVar.a = this.b;
                        aVar2.a = this.a;
                        az.a(com.chartboost.sdk.c.y()).a().a(new z(e, bVar, 0, 0, null, aVar2));
                        return;
                    }
                    this.c.a(cBInPlay, e, true);
                }
            }
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, CBError cBError) {
            CBLogging.b(a.a, "InPlay cache call failed" + cBError);
            a.f = false;
            if (com.chartboost.sdk.c.h() != null) {
                com.chartboost.sdk.c.h().didFailToLoadInPlay(this.a, cBError != null ? cBError.c() : null);
            }
        }
    }

    class 2 implements com.chartboost.sdk.d.b {
        final /* synthetic */ CBInPlay a;
        final /* synthetic */ com.chartboost.sdk.Libraries.e.a b;
        final /* synthetic */ a c;

        2(a aVar, CBInPlay cBInPlay, com.chartboost.sdk.Libraries.e.a aVar2) {
            this.c = aVar;
            this.a = cBInPlay;
            this.b = aVar2;
        }

        public void a() {
            ay d = d.a().d();
            d.a(GooglePlayServicesInterstitial.LOCATION_KEY, this.a.getLocation());
            d.a(ShareConstants.WEB_DIALOG_PARAM_TO, this.b);
            d.a("cgn", this.b);
            d.a("creative", this.b);
            d.a("ad_id", this.b);
            d.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, this.b);
            d.a("more_type", this.b);
            d.a(true);
            d.t();
        }
    }

    private class a implements com.chartboost.sdk.impl.n.a {
        protected String a;
        final /* synthetic */ a b;

        private a(a aVar) {
            this.b = aVar;
        }

        public void a(s sVar) {
            CBLogging.b(a.a, "Bitmap download failed " + sVar.getMessage());
            if (com.chartboost.sdk.c.h() != null) {
                com.chartboost.sdk.c.h().didFailToLoadInPlay(this.a, CBImpressionError.NETWORK_FAILURE);
            }
        }
    }

    private class b implements com.chartboost.sdk.impl.n.b<Bitmap> {
        protected boolean a;
        protected String b;
        protected CBInPlay c;
        final /* synthetic */ a d;

        private b(a aVar) {
            this.d = aVar;
        }

        public void a(Bitmap bitmap) {
            a.e.put(this.b, bitmap);
            this.d.a(this.c, this.b, this.a);
        }
    }

    static {
        a = a.class.getSimpleName();
        c = 4;
        f = false;
    }

    private a() {
        b = new ArrayList();
        e = new LinkedHashMap(c);
    }

    public static a a() {
        if (d == null) {
            synchronized (a.class) {
                if (d == null) {
                    d = new a();
                }
            }
        }
        return d;
    }

    public synchronized void a(String str) {
        if (!(e() || f)) {
            a(str, true);
        }
    }

    private static boolean e() {
        return b.size() == c;
    }

    public synchronized boolean b(String str) {
        boolean z;
        if (b.size() > 0) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized CBInPlay c(String str) {
        CBInPlay cBInPlay;
        cBInPlay = null;
        if (b.size() > 0) {
            cBInPlay = (CBInPlay) b.get(0);
            b.remove(0);
        }
        if (!(e() || f)) {
            a(str, true);
        }
        if (cBInPlay == null && com.chartboost.sdk.c.h() != null) {
            com.chartboost.sdk.c.h().didFailToLoadInPlay(str, CBImpressionError.NO_AD_FOUND);
        }
        return cBInPlay;
    }

    private void a(String str, boolean z) {
        f = true;
        ay ayVar = new ay("/inplay/get");
        ayVar.a(NotificationReciever.RAW_TYPE, Boolean.valueOf(true));
        ayVar.a("cache", Boolean.valueOf(true));
        ayVar.a(com.chartboost.sdk.impl.l.a.HIGH);
        ayVar.b(true);
        ayVar.a(GooglePlayServicesInterstitial.LOCATION_KEY, (Object) str);
        ayVar.a(com.chartboost.sdk.Model.b.e);
        ayVar.a(new 1(this, str, z));
    }

    private synchronized void a(CBInPlay cBInPlay, String str, boolean z) {
        cBInPlay.a((Bitmap) e.get(str));
        b.add(cBInPlay);
        com.chartboost.sdk.a h = com.chartboost.sdk.c.h();
        if (h != null && z) {
            h.didCacheInPlay(cBInPlay.getLocation());
        }
        if (!(e() || f)) {
            a(cBInPlay.getLocation(), false);
        }
    }

    protected void a(CBInPlay cBInPlay) {
        Object a = cBInPlay.a();
        ay ayVar = new ay("/inplay/show");
        ayVar.a("inplay-dictionary", a);
        ayVar.a(GooglePlayServicesInterstitial.LOCATION_KEY, cBInPlay.getLocation());
        ayVar.a(true);
        ayVar.t();
        if (cBInPlay.a().c()) {
            a.e("ad_id");
        }
    }

    protected void b(CBInPlay cBInPlay) {
        String str;
        com.chartboost.sdk.Libraries.e.a a = cBInPlay.a();
        if (a != null) {
            String e = a.e(ShareConstants.WEB_DIALOG_PARAM_LINK);
            String e2 = a.e("deep-link");
            if (!TextUtils.isEmpty(e2)) {
                try {
                    if (!ba.a(e2)) {
                        e2 = e;
                    }
                    str = e2;
                } catch (Exception e3) {
                    CBLogging.b(a, "Cannot open a url");
                }
            }
            str = e;
        } else {
            str = null;
        }
        com.chartboost.sdk.d.b 2 = new 2(this, cBInPlay, a);
        d a2 = d.a();
        if (TextUtils.isEmpty(str)) {
            a2.b.a(null, false, str, CBClickError.URI_INVALID, 2);
        } else {
            a2.b(null, str, 2);
        }
    }

    public static void b() {
        if (e != null) {
            e.clear();
        }
        if (b != null) {
            b.clear();
        }
    }
}
