package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class bv implements w, AppLovinNativeAdLoadListener {
    protected final AppLovinSdkImpl a;
    protected final AppLovinLogger b;
    protected final Object c;
    protected final Map d;
    protected final Map e;
    protected final Set f;

    bv(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
        this.c = new Object();
        this.d = a();
        this.e = new HashMap();
        this.f = new HashSet();
    }

    private bw h(c cVar) {
        return (bw) this.d.get(cVar);
    }

    abstract c a(bd bdVar);

    abstract ca a(c cVar);

    abstract Map a();

    abstract void a(Object obj, bd bdVar);

    abstract void a(Object obj, c cVar, int i);

    public boolean a(c cVar, Object obj) {
        boolean z;
        synchronized (this.c) {
            if (g(cVar)) {
                z = false;
            } else {
                b(cVar, obj);
                z = true;
            }
        }
        return z;
    }

    public bd b(c cVar) {
        bd e;
        synchronized (this.c) {
            e = h(cVar).e();
        }
        return e;
    }

    void b(bd bdVar) {
        f(a(bdVar));
    }

    protected void b(c cVar, int i) {
        this.b.d("PreloadManager", "Failed to pre-load an ad of spec " + cVar + ", error code " + i);
        synchronized (this.c) {
            Object remove = this.e.remove(cVar);
            this.f.add(cVar);
        }
        if (remove != null) {
            try {
                a(remove, cVar, i);
            } catch (Throwable th) {
                this.a.getLogger().userError("PreloadManager", "Encountered exception while invoking user callback", th);
            }
        }
    }

    public void b(c cVar, Object obj) {
        synchronized (this.c) {
            if (this.e.containsKey(cVar)) {
                this.b.w("PreloadManager", "Possibly missing prior registered preload callback.");
            }
            this.e.put(cVar, obj);
        }
    }

    protected void c(bd bdVar) {
        synchronized (this.c) {
            c a = a(bdVar);
            Object obj = this.e.get(a);
            this.e.remove(a);
            this.f.add(a);
            if (obj == null) {
                h(a).a(bdVar);
                this.b.d("PreloadManager", "Ad enqueued: " + bdVar);
            } else {
                this.b.d("PreloadManager", "Additional callback found, skipping enqueue.");
            }
        }
        if (obj != null) {
            this.b.d("PreloadManager", "Called additional callback regarding " + bdVar);
            try {
                a(obj, bdVar);
            } catch (Throwable th) {
                this.a.getLogger().userError("PreloadManager", "Encountered throwable while notifying user callback", th);
            }
            b(bdVar);
        }
        this.b.d("PreloadManager", "Pulled ad from network and saved to preload cache: " + bdVar);
    }

    public boolean c(c cVar) {
        boolean c;
        synchronized (this.c) {
            c = h(cVar).c();
        }
        return c;
    }

    public void d(c cVar) {
        int i = 0;
        if (cVar != null) {
            int b;
            synchronized (this.c) {
                bw h = h(cVar);
                b = h != null ? h.b() - h.a() : 0;
            }
            if (b > 0) {
                while (i < b) {
                    f(cVar);
                    i++;
                }
            }
        }
    }

    public boolean e(c cVar) {
        boolean z;
        synchronized (this.c) {
            z = !h(cVar).d();
        }
        return z;
    }

    public void f(c cVar) {
        if (((Boolean) this.a.a(cb.A)).booleanValue() && !c(cVar)) {
            this.b.d("PreloadManager", "Preloading ad for spec " + cVar + "...");
            this.a.a().a(a(cVar), cw.MAIN, 500);
        }
    }

    boolean g(c cVar) {
        boolean contains;
        synchronized (this.c) {
            contains = this.f.contains(cVar);
        }
        return contains;
    }
}
