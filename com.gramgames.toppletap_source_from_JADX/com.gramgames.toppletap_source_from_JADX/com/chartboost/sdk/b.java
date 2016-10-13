package com.chartboost.sdk;

import android.os.CountDownTimer;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.g.k;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.aa;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.bc;
import com.chartboost.sdk.impl.bt;
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
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.gms.games.Games;
import com.mopub.common.AdType;
import com.mopub.mobileads.CustomEventBannerAdapter;
import com.mopub.volley.DefaultRetryPolicy;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import org.json.JSONArray;

public class b {
    private static HashMap<e, ArrayList<com.chartboost.sdk.Libraries.e.a>> A;
    private static Object B;
    private static boolean C;
    private static boolean D;
    private static com.chartboost.sdk.impl.ay.c E;
    public static volatile ConcurrentHashMap<com.chartboost.sdk.Model.a, e> a;
    public static b b;
    public static ArrayList<String> c;
    private static final String d;
    private static h e;
    private static m f;
    private static b g;
    private static b h;
    private static AtomicInteger i;
    private static AtomicInteger j;
    private static String k;
    private static boolean l;
    private static ConcurrentHashMap<String, a> m;
    private static ConcurrentHashMap<String, JSONArray> n;
    private static ConcurrentHashMap<String, String> o;
    private static ConcurrentHashMap<String, Integer> p;
    private static com.chartboost.sdk.Libraries.e.a q;
    private static ArrayList<c> r;
    private static ArrayList<String> s;
    private static HashMap<String, File> t;
    private static HashMap<String, String> u;
    private static e v;
    private static com.chartboost.sdk.Libraries.e.a w;
    private static ArrayList<com.chartboost.sdk.Libraries.e.a> x;
    private static ArrayList<com.chartboost.sdk.Libraries.e.a> y;
    private static ArrayList<com.chartboost.sdk.Libraries.e.a> z;

    static class 1 extends CountDownTimer {
        1(long j, long j2) {
            super(j, j2);
        }

        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            CBLogging.a(b.d, "###### Webview Prefetch Session expired");
            b.C = true;
        }
    }

    static class 2 implements com.chartboost.sdk.impl.ay.c {
        2() {
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar) {
            b.g = b.kCBInitial;
            try {
                if (aVar.c()) {
                    com.chartboost.sdk.Libraries.e.a a = aVar.a("cache_assets");
                    CBLogging.a(b.d, "Got Asset list for Web Prefetch from server :)" + aVar);
                    b.a(e.Low, a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, CBError cBError) {
            try {
                b.g = b.kCBInitial;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class 3 implements Runnable {
        final /* synthetic */ com.chartboost.sdk.Model.a a;

        3(com.chartboost.sdk.Model.a aVar) {
            this.a = aVar;
        }

        public void run() {
            CBLogging.a(b.d, "######## Impression found and is read to be notified.");
            this.a.u().q(this.a);
        }
    }

    static class 4 implements Comparator<File> {
        4() {
        }

        public /* synthetic */ int compare(Object x0, Object x1) {
            return a((File) x0, (File) x1);
        }

        public int a(File file, File file2) {
            return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
        }
    }

    static /* synthetic */ class 5 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[e.values().length];
            try {
                a[e.Low.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[e.Medium.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[e.High.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public com.chartboost.sdk.Libraries.e.a e;
        public ArrayList<String> f;
        public boolean g;

        public a(String str, String str2, String str3, String str4, com.chartboost.sdk.Libraries.e.a aVar) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = aVar;
            this.f = new ArrayList();
            this.f.add(this.a);
            this.g = false;
        }
    }

    public enum b {
        kCBInitial,
        kCBInProgress
    }

    private static class c extends l<Object> {
        private String a;
        private long b;
        private String c;
        private a d;

        public c(int i, String str, d dVar, String str2, a aVar) {
            super(i, str, dVar);
            this.a = str2;
            this.c = str;
            this.b = System.currentTimeMillis();
            this.d = aVar;
        }

        public Map<String, String> i() throws com.chartboost.sdk.impl.a {
            Map<String, String> hashMap = new HashMap();
            for (Entry entry : ay.b().entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
            }
            return hashMap;
        }

        protected void b(Object obj) {
        }

        protected n<Object> a(i iVar) {
            if (iVar != null) {
                File j;
                com.chartboost.sdk.Tracking.a.e(this.c, this.a, Long.valueOf((System.currentTimeMillis() - this.b) / 1000).toString());
                File d = b.e.d(this.d.c);
                if (d != null) {
                    if (d != null) {
                        try {
                            bt.a(new File(d, this.d.b), iVar.b);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        if (!(TextUtils.isEmpty(this.d.a) || this.d.e == null || !this.d.e.c() || this.d.c.contains("param"))) {
                            j = b.e.j();
                            if (j != null) {
                                Iterator it = this.d.f.iterator();
                                while (it.hasNext()) {
                                    File file = new File(j, (String) it.next());
                                    if (!file.exists()) {
                                        file.mkdir();
                                    }
                                    File file2 = new File(file, this.d.b.split("\\.(?=[^\\.]+$)")[0]);
                                    try {
                                        CBLogging.a(b.d, "Asset download Success. Storing asset in cache: " + this.a);
                                        bt.a(file2, this.d.e.toString().getBytes());
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                if (!TextUtils.isEmpty(this.d.a) && this.d.e != null && this.d.e.c() && b.p.containsKey(this.d.a)) {
                    int intValue = ((Integer) b.p.get(this.d.a)).intValue() - 1;
                    if (intValue > 0) {
                        b.p.put(this.d.a, Integer.valueOf(intValue));
                    } else {
                        ArrayList d2;
                        CBLogging.a(b.d, "All files for " + this.d.a + "is downloaded");
                        b.p.remove(this.d.a);
                        j = b.e.k();
                        File l = b.e.l();
                        if (h.e()) {
                            d2 = h.d(l);
                        } else {
                            d2 = h.d(j);
                        }
                        if (!(d2 == null || d2.isEmpty() || !d2.contains(this.d.a))) {
                            d2.remove(this.d.a);
                        }
                        if (h.e()) {
                            CBLogging.e(b.d, "##### Serializing blacklist ad id to " + l);
                            h.a(d2, l, false);
                        } else {
                            CBLogging.e(b.d, "##### Serializing blacklist ad id to " + j);
                            h.a(d2, j, false);
                        }
                        if (!b.c.contains(this.d.a)) {
                            b.c.add(this.d.a);
                        }
                        b.b(b.v, this.d.a, true);
                    }
                }
            }
            CBLogging.a(b.d, "Current Download count:" + b.i.get());
            CBLogging.a(b.d, "Total Download count:" + b.j.get());
            if (b.i.incrementAndGet() == b.j.get()) {
                CBLogging.e(b.d, "##### Asset Prefetch Download Complete");
                b.i.set(0);
                b.j.set(0);
                CBLogging.e(b.d, "##### Calling to notify impression callback");
                b.b(b.v, BuildConfig.FLAVOR, false);
            }
            b.b.a(this.d);
            return n.a(null, null);
        }

        public com.chartboost.sdk.impl.l.a s() {
            return com.chartboost.sdk.impl.l.a.LOW;
        }
    }

    private static class d implements com.chartboost.sdk.impl.n.a {
        private c a;

        private d() {
        }

        public void a(s sVar) {
            synchronized (b.a()) {
                try {
                    if (((sVar instanceof r) || (sVar instanceof q) || (sVar instanceof com.chartboost.sdk.impl.h)) && this.a != null) {
                        com.chartboost.sdk.Tracking.a.c(this.a.c, this.a.a, Long.valueOf((System.currentTimeMillis() - this.a.b) / 1000).toString(), sVar.getMessage());
                        CBLogging.b(b.d, "Error downloading asset " + sVar.getMessage() + this.a.a);
                    }
                    a d = this.a.d;
                    if (b.p.containsKey(d.a)) {
                        b.p.remove(d.a);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (b.i.incrementAndGet() == b.j.get()) {
                    CBLogging.e(b.d, "##### Failure response callback : Asset Prefetch Download Complete");
                    b.i.set(0);
                    b.j.set(0);
                    b.h = b.kCBInitial;
                    if (this.a.d == null || TextUtils.isEmpty(this.a.d.a)) {
                        b.b(b.v, BuildConfig.FLAVOR, false);
                    } else {
                        b.b(b.v, this.a.d.a, false);
                    }
                }
                if (!(this.a == null || TextUtils.isEmpty(this.a.c))) {
                    CBLogging.a(b.d, "Removing the cache from volley for: " + this.a.c);
                    b.f.d().b(this.a.c);
                }
                b.b.a(this.a.d);
            }
        }
    }

    public enum e {
        Idle,
        High,
        Medium,
        Low
    }

    static {
        d = b.class.getSimpleName();
        i = new AtomicInteger();
        j = new AtomicInteger();
        k = "blacklist";
        l = false;
        r = new ArrayList();
        s = new ArrayList();
        B = new Object();
        C = true;
        D = false;
        E = new 2();
    }

    private b() {
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b();
                q();
            }
            bVar = b;
        }
        return bVar;
    }

    private static synchronized void q() {
        synchronized (b.class) {
            if (!l) {
                l = true;
                e = new h(true);
                g = b.kCBInitial;
                h = b.kCBInitial;
                f = new m(new aa(), new u(new y()));
                a = new ConcurrentHashMap();
                p = new ConcurrentHashMap();
                o = new ConcurrentHashMap();
                c = new ArrayList();
                t = new HashMap();
                u = new HashMap();
                v = e.Idle;
                x = new ArrayList();
                y = new ArrayList();
                z = new ArrayList();
                A = new HashMap();
                A.put(e.Low, x);
                A.put(e.Medium, y);
                A.put(e.High, z);
                e.o();
                File a = h.a();
                if (a != null) {
                    String[] list = a.list();
                    if (list != null && list.length > 0) {
                        for (Object add : list) {
                            s.add(add);
                        }
                    }
                }
                f.a();
            }
        }
    }

    public static synchronized void b() {
        synchronized (b.class) {
            synchronized (b) {
                if (!c.H().booleanValue()) {
                    CBLogging.a(d, "###### WebView is disabled so not performing prefetch");
                } else if (!c.x()) {
                } else if (c.T().booleanValue()) {
                } else {
                    CBLogging.a(d, "Webview Prefetching the asset list");
                    if (g == b.kCBInProgress) {
                        CBLogging.a(d, "###### Webview Prefetch is already in progress");
                    } else if (C) {
                        new 1((long) ((c.U() * 60) * AdError.NETWORK_ERROR_CODE), 1000).start();
                        C = false;
                        g = b.kCBInProgress;
                        j.set(0);
                        i.set(0);
                        bc bcVar = new bc(c.C());
                        bcVar.a("cache_assets", c(), com.chartboost.sdk.impl.bc.a.AD);
                        k[] kVarArr = new k[3];
                        kVarArr[0] = g.a(Games.EXTRA_STATUS, com.chartboost.sdk.Libraries.a.a);
                        kVarArr[1] = g.a(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, g.a());
                        k[] kVarArr2 = new k[3];
                        k[] kVarArr3 = new k[2];
                        kVarArr3[0] = g.a("template", g.a());
                        kVarArr3[1] = g.a("elements", g.b(g.a(g.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, g.a()), g.a(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY, g.a()), g.a("value", g.a()))));
                        kVarArr2[0] = g.a("templates", g.a(g.b(g.a(kVarArr3))));
                        kVarArr2[1] = g.a("images", g.a(g.b(g.a(g.a(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY, g.a()), g.a("value", g.a())))));
                        kVarArr2[2] = g.a("videos", g.a(g.b(g.a(g.a(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY, g.a()), g.a("value", g.a())))));
                        kVarArr[2] = g.a("cache_assets", g.a(kVarArr2));
                        bcVar.a(g.a(kVarArr));
                        bcVar.b(true);
                        bcVar.a(E);
                        com.chartboost.sdk.Tracking.a.a(h.n().e());
                        v = e.Low;
                    } else {
                        CBLogging.a(d, "Webview Prefetch session is still active. Won't be making any new prefetch until the prefetch session expires");
                    }
                }
            }
        }
    }

    private static void a(com.chartboost.sdk.Libraries.e.a aVar) {
        int i;
        int p = aVar.p();
        u = new HashMap();
        int F = c.F();
        if (p > F) {
            i = F;
        } else {
            i = p;
        }
        for (int i2 = 0; i2 < i; i2++) {
            com.chartboost.sdk.Libraries.e.a c = aVar.c(i2);
            String e = c.e("template");
            com.chartboost.sdk.Libraries.e.a a = c.a("elements");
            JSONArray jSONArray = new JSONArray();
            if (!(TextUtils.isEmpty(e) || a == null || a.p() <= 0)) {
                for (int i3 = 0; i3 < a.p(); i3++) {
                    com.chartboost.sdk.Libraries.e.a c2 = a.c(i3);
                    String e2 = c2.e(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
                    String e3 = c2.e(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY);
                    String e4 = c2.e("value");
                    Object e5 = c2.e("param");
                    if (!(TextUtils.isEmpty(e2) || s.contains(e2))) {
                        e.e(e2);
                        s.add(e2);
                    }
                    if (t.containsKey(e3)) {
                        if (e2.equals(AdType.HTML) && !u.containsKey(e3)) {
                            u.put(e3, e);
                        }
                        ((File) t.get(e3)).setLastModified(System.currentTimeMillis());
                        if (!TextUtils.isEmpty(e5)) {
                            jSONArray.put(new a(e, e5, "param", e3, c2));
                        }
                    } else if (!TextUtils.isEmpty(e2) && e2.equals("param")) {
                        jSONArray.put(new a(e, e5, "param", e4, c2));
                    } else if (!(TextUtils.isEmpty(e2) || TextUtils.isEmpty(e3) || TextUtils.isEmpty(e4))) {
                        if (e2.equals(AdType.HTML)) {
                            u.put(e3, e);
                        }
                        if (m.containsKey(e3)) {
                            a aVar2 = (a) m.get(e3);
                            aVar2.f.add(e);
                            m.put(e3, aVar2);
                        } else {
                            m.put(e3, new a(e, e3, e2, e4, c2));
                        }
                        if (!TextUtils.isEmpty(e5)) {
                            jSONArray.put(new a(e, e5, "param", e3, c2));
                        }
                    }
                }
                if (jSONArray.length() > 0 && !n.containsKey(e)) {
                    n.put(e, jSONArray);
                }
            }
        }
    }

    private static void a(com.chartboost.sdk.Libraries.e.a aVar, String str) {
        int p = aVar.p();
        for (int i = 0; i < p; i++) {
            com.chartboost.sdk.Libraries.e.a c = aVar.c(i);
            String e = c.e(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY);
            String e2 = c.e("value");
            if (!(TextUtils.isEmpty(str) || s.contains(str))) {
                e.e(str);
                s.add(str);
            }
            if (!t.containsKey(e)) {
                m.put(e, new a(str, e, str, e2, null));
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(com.chartboost.sdk.b.e r4, com.chartboost.sdk.Libraries.e.a r5) {
        /*
        r1 = com.chartboost.sdk.b.class;
        monitor-enter(r1);
        r0 = d;	 Catch:{ all -> 0x0031 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0031 }
        r2.<init>();	 Catch:{ all -> 0x0031 }
        r3 = "##### SynchronizeAssets called on state: ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0031 }
        r2 = r2.append(r4);	 Catch:{ all -> 0x0031 }
        r2 = r2.toString();	 Catch:{ all -> 0x0031 }
        com.chartboost.sdk.Libraries.CBLogging.a(r0, r2);	 Catch:{ all -> 0x0031 }
        r0 = com.chartboost.sdk.b.5.a;	 Catch:{ all -> 0x0031 }
        r2 = r4.ordinal();	 Catch:{ all -> 0x0031 }
        r0 = r0[r2];	 Catch:{ all -> 0x0031 }
        switch(r0) {
            case 1: goto L_0x002b;
            case 2: goto L_0x0034;
            case 3: goto L_0x003a;
            default: goto L_0x0026;
        };	 Catch:{ all -> 0x0031 }
    L_0x0026:
        a(r4);	 Catch:{ all -> 0x0031 }
        monitor-exit(r1);
        return;
    L_0x002b:
        r0 = x;	 Catch:{ all -> 0x0031 }
        r0.add(r5);	 Catch:{ all -> 0x0031 }
        goto L_0x0026;
    L_0x0031:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x0034:
        r0 = y;	 Catch:{ all -> 0x0031 }
        r0.add(r5);	 Catch:{ all -> 0x0031 }
        goto L_0x0026;
    L_0x003a:
        r0 = z;	 Catch:{ all -> 0x0031 }
        r0.add(r5);	 Catch:{ all -> 0x0031 }
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.b.a(com.chartboost.sdk.b$e, com.chartboost.sdk.Libraries.e$a):void");
    }

    private static synchronized void a(e eVar) {
        synchronized (b.class) {
            if (eVar != null) {
                CBLogging.a(d, "##### Flush AdQueue called on state: " + eVar);
                if (f()) {
                    CBLogging.a(d, "##### Flush AdQueue Download in progress: ");
                    if (eVar == e.High && (v == e.Medium || v == e.Low)) {
                        CBLogging.a(d, "###### FlushAdQueue: Overrriding the current AdPriority" + v + " with a high priority one");
                        ((ArrayList) A.get(v)).add(w);
                        e();
                        v = e.High;
                        w = (com.chartboost.sdk.Libraries.e.a) z.remove(0);
                        a(w, false, eVar);
                        h = b.kCBInProgress;
                    } else {
                        if (eVar == e.Medium && v == e.Low) {
                            CBLogging.a(d, "###### FlushAdQueue: Overrriding the current AdPriority" + v + " with a medium priority one");
                            ((ArrayList) A.get(v)).add(w);
                            e();
                            v = e.Medium;
                            a((com.chartboost.sdk.Libraries.e.a) y.remove(0), false, eVar);
                            h = b.kCBInProgress;
                        }
                    }
                }
            }
            if (!f()) {
                CBLogging.a(d, "###### FlushAdQueue: Download is not in progress");
                CBLogging.a(d, "###### FlushAdQueue: AdPriorityQueue");
                if (!z.isEmpty()) {
                    v = e.High;
                    h = b.kCBInProgress;
                    w = (com.chartboost.sdk.Libraries.e.a) z.remove(0);
                    CBLogging.a(d, "###### Flush Ad Queue: Synchronizing a high priority Ad");
                    a(w, false, e.High);
                } else if (!y.isEmpty()) {
                    v = e.Medium;
                    h = b.kCBInProgress;
                    CBLogging.a(d, "###### Flush Ad Queue: Synchronizing a medium priority Ad");
                    w = (com.chartboost.sdk.Libraries.e.a) y.remove(0);
                    a(w, false, e.Medium);
                } else if (x.isEmpty()) {
                    CBLogging.a(d, "###### Flush Ad Queue: Nothing avaliable in queue resetting the state to initial and idle");
                    if (a != null && a.size() > 0) {
                        for (com.chartboost.sdk.Model.a aVar : a.keySet()) {
                            aVar.u().a(aVar, CBImpressionError.ERROR_LOADING_WEB_VIEW);
                            a.remove(aVar);
                        }
                    }
                    h = b.kCBInitial;
                    v = e.Idle;
                    w = null;
                } else {
                    v = e.Low;
                    h = b.kCBInProgress;
                    CBLogging.a(d, "###### Flush Ad Queue: Synchronizing a low priority Ad");
                    w = (com.chartboost.sdk.Libraries.e.a) x.remove(0);
                    a(w, true, e.Low);
                }
            }
        }
    }

    public static void a(com.chartboost.sdk.Libraries.e.a aVar, boolean z, e eVar) {
        try {
            synchronized (b) {
                com.chartboost.sdk.Libraries.e.a a;
                m = new ConcurrentHashMap();
                n = new ConcurrentHashMap();
                File k = e.k();
                File l = e.l();
                if (q != null && q.c() && q.p() > 0) {
                    a(q);
                }
                if (aVar != null && aVar.c()) {
                    a = aVar.a("templates");
                    if (a != null && a.c()) {
                        a(a);
                    }
                    ArrayList arrayList = new ArrayList();
                    for (Entry value : m.entrySet()) {
                        a aVar2 = (a) value.getValue();
                        if (p.containsKey(aVar2.a)) {
                            p.put(aVar2.a, Integer.valueOf(((Integer) p.get(aVar2.a)).intValue() + 1));
                        } else {
                            if (!arrayList.contains(aVar2.a)) {
                                arrayList.add(aVar2.a);
                            }
                            p.put(aVar2.a, Integer.valueOf(1));
                        }
                    }
                    if (h.e()) {
                        CBLogging.e(d, "##### Serializing blacklist ad id to " + l);
                        h.a(arrayList, l, true);
                    } else {
                        CBLogging.e(d, "##### Serializing blacklist ad id to " + k);
                        h.a(arrayList, k, true);
                    }
                    for (String str : aVar.q()) {
                        if (!str.contains("template")) {
                            com.chartboost.sdk.Libraries.e.a a2 = aVar.a(str);
                            if (a2 != null && a2.c()) {
                                a(a2, str);
                            }
                        }
                    }
                }
                if (m.isEmpty()) {
                    h = b.kCBInitial;
                    CBLogging.a(d, "####### Nothing to download for the given response object");
                    if (aVar.c() && aVar.a("templates").c() && aVar.a("templates").p() > 0) {
                        a = aVar.a("templates").c(0);
                        if (a.c()) {
                            Object e = a.e("template");
                            if (eVar != e.Low) {
                                if (TextUtils.isEmpty(e)) {
                                    b(eVar, BuildConfig.FLAVOR, false);
                                    CBLogging.a(d, "###### TemplateId Missing for the given response object");
                                } else {
                                    b(eVar, e, true);
                                }
                            }
                        }
                    }
                } else {
                    j.set(m.size());
                    a(m, eVar);
                }
            }
        } catch (Exception e2) {
            CBLogging.b(d, "Error while syncrhonizing assets");
        }
    }

    public static void a(HashMap<String, File> hashMap) {
        synchronized (B) {
            ArrayList arrayList = new ArrayList();
            for (String split : hashMap.keySet()) {
                arrayList.add(split.split("\\.(?=[^\\.]+$)")[0]);
            }
            File j = e.j();
            if (j != null) {
                File[] listFiles = j.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File file : listFiles) {
                        if (!TextUtils.isEmpty(file.getName())) {
                            Object obj = 1;
                            String name = file.getName();
                            File[] listFiles2 = file.listFiles();
                            if (listFiles2 != null && listFiles2.length > 0) {
                                for (File name2 : listFiles2) {
                                    if (!arrayList.contains(name2.getName())) {
                                        obj = null;
                                    }
                                }
                            }
                            if (obj != null) {
                                if (!c.contains(name)) {
                                    c.add(name);
                                }
                            } else if (c.contains(name)) {
                                c.remove(name);
                            }
                        }
                    }
                }
            }
        }
    }

    private static synchronized void a(ConcurrentHashMap<String, a> concurrentHashMap, e eVar) {
        synchronized (b.class) {
            for (a aVar : concurrentHashMap.values()) {
                d dVar = new d();
                c cVar = new c(0, aVar.d, dVar, aVar.b, aVar);
                dVar.a = cVar;
                cVar.a((Object) Integer.valueOf(b.hashCode()));
                cVar.a(false);
                cVar.a(new com.chartboost.sdk.impl.d(CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                r.add(cVar);
                com.chartboost.sdk.Tracking.a.a(aVar.d, aVar.b);
            }
            if (!D) {
                if (eVar == v) {
                    CBLogging.a(d, "##### DownloadAssets:Sending request to volley: " + v);
                    if (!r.isEmpty()) {
                        f.a((l) r.remove(0));
                    }
                } else {
                    CBLogging.a(d, "##### DownloadAssets: Priority states are different probably overridden by a high priority one ");
                }
            }
        }
    }

    private static String a(String str, String str2) {
        if (n.isEmpty() || str.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = (JSONArray) n.get(str2);
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    a aVar = (a) jSONArray.get(i);
                    String str3 = aVar.b;
                    str = str.replaceAll(Pattern.quote(str3), aVar.d);
                } catch (Exception e) {
                    e.printStackTrace();
                    CBLogging.b(d, "Error while injecting values into the html");
                }
            }
        }
        if (!str.contains("{{")) {
            return str;
        }
        CBLogging.b(d, " Html data still contains mustache injection values, cannot load the web view ad");
        return null;
    }

    public void a(a aVar) {
        if (r.isEmpty()) {
            h = b.kCBInitial;
            CBLogging.a(d, "######## No request to flush from queue");
        } else if (D) {
            CBLogging.a(d, "######## Request download is paused");
            CBLogging.a(d, "######## Current download queue size: " + r.size());
        } else if (!D && !r.isEmpty()) {
            CBLogging.a(d, "######## Flushing out next asset download request");
            CBLogging.a(d, "######## Current download queue size: " + r.size());
            f.a((l) r.remove(0));
        }
    }

    public static synchronized com.chartboost.sdk.Libraries.e.a c() {
        Object a;
        synchronized (b.class) {
            a = com.chartboost.sdk.Libraries.e.a.a();
            try {
                t = h.b();
                a(t);
                List<String> l = c.l();
                JSONArray jSONArray = new JSONArray();
                if (c.size() > 0) {
                    Iterator it = c.iterator();
                    while (it.hasNext()) {
                        jSONArray.put((String) it.next());
                    }
                }
                a.a("templates", jSONArray);
                if (!(l == null || l.isEmpty())) {
                    for (String str : l) {
                        if (!str.contains("template")) {
                            h hVar = e;
                            File file = new File(h.a(), str);
                            JSONArray jSONArray2 = new JSONArray();
                            if (file.exists()) {
                                String[] list = file.list();
                                if (list != null) {
                                    for (String str2 : list) {
                                        if (!str2.contains("nomedia")) {
                                            jSONArray2.put(str2);
                                        }
                                    }
                                }
                            }
                            a.a(str, jSONArray2);
                        }
                    }
                }
            } catch (Exception e) {
                CBLogging.b(d, "getAvailableAdIdList(): Error while loading ad id into json array");
            }
        }
        return com.chartboost.sdk.Libraries.e.a.a(a);
    }

    public static ConcurrentHashMap<String, String> d() {
        return o;
    }

    public static synchronized void e() {
        synchronized (b.class) {
            f.a(Integer.valueOf(b.hashCode()));
            if (!(r == null || r.isEmpty())) {
                r.clear();
            }
        }
    }

    public static synchronized boolean f() {
        boolean z;
        synchronized (b.class) {
            if (r.isEmpty()) {
                z = false;
            } else {
                CBLogging.a(d, "##### Downloads are in progress");
                z = true;
            }
        }
        return z;
    }

    private static void b(e eVar, String str, boolean z) {
        CBLogging.a(d, "##### notifyCacheImpressionCallback called on state:" + eVar + " for adId:" + (TextUtils.isEmpty(str) ? "Empty" : str));
        if (eVar == e.Low) {
            CBLogging.a(d, "##### No need to notify any impressions as they are prefetch download request");
            return;
        }
        if (!(u.isEmpty() || n.isEmpty())) {
            File file = new File(e.m(), com.chartboost.sdk.Libraries.h.a.Html.toString());
            if (file == null || !file.exists()) {
                CBLogging.b(d, "Error happened, cannot able to find html directory for some reason");
            } else {
                for (String str2 : u.keySet()) {
                    String str3 = BuildConfig.FLAVOR;
                    File file2 = new File(file, str2);
                    if (file2 == null || !file2.exists()) {
                        CBLogging.b(d, "Error happened, cannot able to find html file in the directory for some reason:" + str2);
                    } else {
                        String str4 = new String(e.b(file2), Charset.defaultCharset());
                        CBLogging.a(d, "##### Before html injection file path " + file2);
                        CharSequence a = a(str4, (String) u.get(str2));
                        if (TextUtils.isEmpty(a)) {
                            CBLogging.b(d, "Error happened while injection on updating the html file in cache " + file2);
                        } else {
                            o.put(u.get(str2), a);
                        }
                    }
                }
            }
        }
        if (a.size() > 0) {
            for (com.chartboost.sdk.Model.a aVar : a.keySet()) {
                if (!TextUtils.isEmpty(str) && aVar.i.equals(str)) {
                    if (z) {
                        CBUtility.e().post(new 3(aVar));
                        a.remove(aVar);
                    } else {
                        aVar.u().a(aVar, CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
                        a.remove(aVar);
                    }
                }
            }
        }
        h = b.kCBInitial;
        a(null);
    }

    public static synchronized void g() {
        synchronized (b.class) {
            try {
                CBLogging.a(d, "########### Invalidating the disk cache");
                t = h.b();
                if (!(t == null || t.isEmpty())) {
                    File[] fileArr = new File[t.size()];
                    int i = 0;
                    for (File file : t.values()) {
                        fileArr[i] = file;
                        i++;
                    }
                    if (fileArr.length > 1) {
                        Arrays.sort(fileArr, new 4());
                    }
                    ArrayList arrayList = new ArrayList();
                    if (fileArr.length > 0) {
                        long G = (long) c.G();
                        long f = h.f(e.f());
                        int E = c.E();
                        CBLogging.a(d, "Total local file count:" + fileArr.length);
                        CBLogging.a(d, "Video Folder Size in bytes :" + f);
                        CBLogging.a(d, "Max Bytes allowed:" + G);
                        File j = e.j();
                        for (File file2 : fileArr) {
                            if (f <= G) {
                                break;
                            }
                            if (file2 != null && (file2.getPath().contains(com.chartboost.sdk.Libraries.h.a.Videos.toString()) || file2.getPath().contains(MimeTypes.BASE_TYPE_VIDEO))) {
                                CBLogging.a(d, "Deleting file at path:" + file2.getPath());
                                f -= file2.length();
                                CBLogging.a(d, "Current Video Size:" + f);
                                file2.delete();
                                a(file2, j, arrayList);
                            }
                        }
                        for (File file3 : fileArr) {
                            if (h.a(file3, E) && file3.exists()) {
                                CBLogging.a(d, "Deleting file at path:" + file3.getPath());
                                file3.delete();
                                a(file3, j, arrayList);
                            }
                        }
                    }
                    if (h.e()) {
                        h.a(arrayList, e.l(), true);
                    } else {
                        h.a(arrayList, e.k(), true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void h() {
        if (!D) {
            CBLogging.a(d, "######## Pause any downloads for webview, as a ad is about to be shown");
            CBLogging.a(d, "######## Current dowload Queue size: " + r.size());
            CBLogging.a(d, "######## CurrentAdPriority: " + v);
            D = true;
        }
    }

    public static void i() {
        if (D) {
            CBLogging.a(d, "######## Resuming any downloads for webview, as a ad is about to be dismissed");
            CBLogging.a(d, "######## Current dowload Queue size" + r.size());
            CBLogging.a(d, "######## CurrentAdPriority: " + v);
            D = false;
            if (r.isEmpty()) {
                a(null);
            } else {
                b.a(null);
            }
        }
    }

    private static void a(File file, File file2, ArrayList<String> arrayList) {
        if (file2 != null) {
            File[] listFiles = file2.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file3 : listFiles) {
                    arrayList.add(file3.getName());
                    if (o.containsKey(arrayList)) {
                        o.remove(arrayList);
                    }
                    if (c.contains(arrayList)) {
                        c.remove(arrayList);
                    }
                    File[] listFiles2 = file3.listFiles();
                    if (listFiles2 != null && listFiles2.length > 0) {
                        for (File file4 : listFiles2) {
                            if (file4.getName().equalsIgnoreCase(file.getName().split("\\.(?=[^\\.]+$)")[0])) {
                                CBLogging.a(d, "Deleting log file info:" + file4);
                                file4.delete();
                            }
                        }
                    }
                }
            }
        }
    }
}
