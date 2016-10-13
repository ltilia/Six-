package com.chartboost.sdk;

import android.os.CountDownTimer;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.g.k;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.aa;
import com.chartboost.sdk.impl.ax;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.d;
import com.chartboost.sdk.impl.i;
import com.chartboost.sdk.impl.l;
import com.chartboost.sdk.impl.m;
import com.chartboost.sdk.impl.n;
import com.chartboost.sdk.impl.q;
import com.chartboost.sdk.impl.r;
import com.chartboost.sdk.impl.s;
import com.chartboost.sdk.impl.u;
import com.chartboost.sdk.impl.y;
import com.facebook.ads.AdError;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.gms.games.Games;
import com.mopub.mobileads.CustomEventBannerAdapter;
import com.mopub.volley.DefaultRetryPolicy;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;

public class h {
    public static h a;
    private static final String b;
    private static com.chartboost.sdk.Libraries.h c;
    private static m d;
    private static ArrayList<b> e;
    private static ConcurrentHashMap<Integer, b> f;
    private static a g;
    private static a h;
    private static AtomicInteger i;
    private static AtomicInteger j;
    private static boolean k;
    private static boolean l;
    private static boolean m;
    private static boolean n;
    private static Observer o;
    private static com.chartboost.sdk.impl.ay.c p;

    static class 1 implements Observer {
        1() {
        }

        public void update(Observable observable, Object data) {
            h.p();
        }
    }

    static class 2 extends CountDownTimer {
        2(long j, long j2) {
            super(j, j2);
        }

        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            CBLogging.a(h.b, "###### Native Prefetch Session expired");
            h.n = true;
        }
    }

    static class 3 implements com.chartboost.sdk.impl.ay.c {
        3() {
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar) {
            try {
                synchronized (h.class) {
                    h.g = a.kCBIntial;
                    if (aVar.c()) {
                        h.a(aVar.a("videos"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, CBError cBError) {
            h.g = a.kCBIntial;
        }
    }

    public enum a {
        kCBIntial,
        kCBInProgress
    }

    private static class b extends l<Object> {
        private String a;
        private long b;
        private String c;

        public b(int i, String str, c cVar, String str2) {
            super(i, str, cVar);
            this.a = str2;
            this.c = str;
            this.b = System.currentTimeMillis();
        }

        protected void b(Object obj) {
        }

        protected n<Object> a(i iVar) {
            if (iVar != null) {
                com.chartboost.sdk.Tracking.a.e(this.c, this.a, Long.valueOf((System.currentTimeMillis() - this.b) / 1000).toString());
                CBLogging.a(h.b, "Video download Success. Storing video in cache " + h.c.f() + this.a);
                h.c.a(h.c.f(), this.a, iVar.b);
            }
            synchronized (h.a()) {
                h.a.c();
                if (h.i.incrementAndGet() == h.j.get()) {
                    CBLogging.a(h.b, "Video Prefetcher downloads completed");
                    h.i.set(0);
                    h.j.set(0);
                    h.h = a.kCBIntial;
                    h.f.clear();
                }
            }
            return n.a(null, null);
        }

        public com.chartboost.sdk.impl.l.a s() {
            return com.chartboost.sdk.impl.l.a.LOW;
        }

        public Map<String, String> i() throws com.chartboost.sdk.impl.a {
            Map<String, String> hashMap = new HashMap();
            for (Entry entry : ay.b().entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
            }
            return hashMap;
        }
    }

    private static class c implements com.chartboost.sdk.impl.n.a {
        private b a;

        private c() {
        }

        public void a(s sVar) {
            h.a.c();
            if ((sVar instanceof r) || (sVar instanceof q) || (sVar instanceof com.chartboost.sdk.impl.h)) {
                if (this.a != null) {
                    com.chartboost.sdk.Tracking.a.c(this.a.c, this.a.a, Long.valueOf((System.currentTimeMillis() - this.a.b) / 1000).toString(), sVar.getMessage());
                }
                h.f.put(Integer.valueOf(this.a.hashCode()), this.a);
                CBLogging.b(h.b, "Error downloading video " + sVar.getMessage() + this.a.a);
            }
            if (this.a != null && !TextUtils.isEmpty(this.a.c)) {
                CBLogging.a(h.b, "Removing the cache from volley for: " + this.a.c);
                h.d.d().b(this.a.c);
            }
        }
    }

    static {
        b = h.class.getSimpleName();
        i = new AtomicInteger();
        j = new AtomicInteger();
        k = true;
        l = false;
        m = false;
        n = true;
        o = new 1();
        p = new 3();
    }

    private h() {
    }

    public static synchronized h a() {
        h hVar;
        synchronized (h.class) {
            if (a == null) {
                a = new h();
                o();
            }
            hVar = a;
        }
        return hVar;
    }

    private static synchronized void o() {
        synchronized (h.class) {
            if (!m) {
                m = true;
                c = new com.chartboost.sdk.Libraries.h(true);
                f = new ConcurrentHashMap();
                g = a.kCBIntial;
                h = a.kCBIntial;
                d = new m(new aa(), new u(new y()));
                e = new ArrayList();
                ax.a().addObserver(o);
                d.a();
            }
        }
    }

    public static synchronized void b() {
        synchronized (h.class) {
            if (!m) {
                o();
            }
            if (!c.N()) {
                CBLogging.a(b, "###### Native is disabled so not performing prefetch");
            } else if (c.x() && !c.T().booleanValue()) {
                CBLogging.a(b, "Native Prefetching the Video list");
                if (!(a.kCBInProgress == g || a.kCBInProgress == h)) {
                    if (n) {
                        new 2((long) ((c.V() * 60) * AdError.NETWORK_ERROR_CODE), 1000).start();
                        n = false;
                        if (!(f == null || f.isEmpty())) {
                            f.clear();
                            d.a(Integer.valueOf(o.hashCode()));
                            h = a.kCBIntial;
                            CBLogging.a(b, "prefetchVideo: Clearing all volley request for new start");
                        }
                        g = a.kCBInProgress;
                        Object jSONArray = new JSONArray();
                        if (d() != null) {
                            for (Object put : d()) {
                                jSONArray.put(put);
                            }
                        }
                        j.set(0);
                        i.set(0);
                        ay ayVar = new ay("/api/video-prefetch");
                        ayVar.a("local-videos", jSONArray);
                        k[] kVarArr = new k[2];
                        kVarArr[0] = g.a(Games.EXTRA_STATUS, com.chartboost.sdk.Libraries.a.a);
                        kVarArr[1] = g.a("videos", g.b(g.a(g.a(MimeTypes.BASE_TYPE_VIDEO, g.a(g.a())), g.a(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, g.a()))));
                        ayVar.a(g.a(kVarArr));
                        ayVar.b(true);
                        ayVar.a(p);
                    } else {
                        CBLogging.a(b, "Native Prefetch session is still active. Won't be making any new prefetch until the prefetch session expires");
                    }
                }
            }
        }
    }

    public static synchronized void a(com.chartboost.sdk.Libraries.e.a aVar) {
        synchronized (h.class) {
            if (c.x()) {
                if (aVar.c()) {
                    HashMap hashMap = new HashMap();
                    d();
                    for (int i = 0; i < aVar.p(); i++) {
                        com.chartboost.sdk.Libraries.e.a c = aVar.c(i);
                        if (!(c.b(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY) || c.b(MimeTypes.BASE_TYPE_VIDEO))) {
                            String e = c.e(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
                            CharSequence e2 = c.e(MimeTypes.BASE_TYPE_VIDEO);
                            if (!(c.c(e) || TextUtils.isEmpty(e) || TextUtils.isEmpty(e2) || hashMap.get(e) != null)) {
                                hashMap.put(e, e2);
                                j.incrementAndGet();
                            }
                        }
                    }
                    if (k) {
                        k = false;
                    }
                    CBLogging.a(b, "Synchronizing videos with the list from the server");
                    if (!hashMap.isEmpty()) {
                        a(hashMap);
                        h = a.kCBInProgress;
                    }
                }
            }
        }
    }

    private static synchronized void a(HashMap<String, String> hashMap) {
        synchronized (h.class) {
            for (String str : hashMap.keySet()) {
                c cVar = new c();
                b bVar = new b(0, (String) hashMap.get(str), cVar, str);
                bVar.a(new d(CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                cVar.a = bVar;
                bVar.a(false);
                bVar.a((Object) Integer.valueOf(o.hashCode()));
                com.chartboost.sdk.Tracking.a.a((String) hashMap.get(str), str);
                e.add(bVar);
                CBLogging.a(b, "Downloading video:" + ((String) hashMap.get(str)));
            }
            if (l) {
                CBLogging.a(b, "##### Video Download is put on hold, it seems an ad is shown, it will resume once the ad is closed");
            } else if (!e.isEmpty()) {
                d.a((l) e.remove(0));
            }
        }
    }

    public void c() {
        if (!l && !e.isEmpty()) {
            CBLogging.a(b, "##### Flushing out next request to download");
            d.a((l) e.remove(0));
        }
    }

    public static String[] d() {
        if (c == null) {
            return null;
        }
        return c.c(c.f());
    }

    public static synchronized void e() {
        synchronized (h.class) {
            d.a(Integer.valueOf(o.hashCode()));
        }
    }

    public static String a(String str) {
        if (c.c(str)) {
            return c.c(c.f(), str).getPath();
        }
        return null;
    }

    public static String b(com.chartboost.sdk.Libraries.e.a aVar) {
        if (aVar == null) {
            return null;
        }
        com.chartboost.sdk.Libraries.e.a a = aVar.a("assets");
        if (a.b()) {
            return null;
        }
        com.chartboost.sdk.Libraries.e.a a2 = a.a(CBUtility.c().a() ? "video-portrait" : "video-landscape");
        if (a2.b()) {
            return null;
        }
        String e = a2.e(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        return a(e);
    }

    public static boolean c(com.chartboost.sdk.Libraries.e.a aVar) {
        return !TextUtils.isEmpty(b(aVar));
    }

    public static void f() {
        if (!l) {
            CBLogging.a(b, "##### Pause Video Downloads if its in progress.");
            CBLogging.a(b, "##### Current Queue size: " + e.size());
            l = true;
        }
    }

    public static void g() {
        if (l) {
            CBLogging.a(b, "##### Resume video download if its in progress");
            CBLogging.a(b, "##### Current Queue size: " + e.size());
            l = false;
            a.c();
        }
    }

    private static synchronized void p() {
        synchronized (h.class) {
            CBLogging.a(b, "Process Request called");
            if (!(g == a.kCBInProgress || h == a.kCBInProgress)) {
                if ((h == a.kCBIntial && f != null) || f.size() > 0) {
                    for (Integer num : f.keySet()) {
                        h = a.kCBInProgress;
                        d.a((l) f.get(num));
                        f.remove(num);
                    }
                }
            }
        }
    }
}
