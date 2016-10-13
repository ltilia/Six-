package com.vungle.publisher.ad.prepare;

import com.google.android.exoplayer.hls.HlsChunkSource;
import com.unity3d.ads.android.R;
import com.vungle.log.Logger;
import com.vungle.publisher.ab;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.an;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.ct;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.LocalArchive;
import com.vungle.publisher.db.model.LocalVideo;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.hb;
import com.vungle.publisher.reporting.AdReportManager;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.simple.parser.Yytoken;

/* compiled from: vungle */
public final class PrepareAdRunnable implements Runnable {
    private static final Object h;
    @Inject
    public EventBus a;
    @Inject
    public AdManager b;
    @Inject
    public AdReportManager c;
    @Inject
    public ScheduledPriorityExecutor d;
    @Inject
    public com.vungle.publisher.ad.prepare.PrepareViewableRunnable.Factory e;
    @Inject
    public RetryMap f;
    @Inject
    public com.vungle.publisher.db.model.LoggedException.Factory g;
    private String i;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[com.vungle.publisher.db.model.Ad.a.values().length];
            try {
                a[com.vungle.publisher.db.model.Ad.a.deleting.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.invalid.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.ready.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.failed.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.aware.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.preparing.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[com.vungle.publisher.db.model.Ad.a.viewed.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Provider<PrepareAdRunnable> a;

        @Inject
        Factory() {
        }
    }

    /* compiled from: vungle */
    public final class Factory_Factory implements dagger.internal.Factory<Factory> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<Factory> b;

        static {
            a = !Factory_Factory.class.desiredAssertionStatus();
        }

        public Factory_Factory(MembersInjector<Factory> factoryMembersInjector) {
            if (a || factoryMembersInjector != null) {
                this.b = factoryMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final Factory get() {
            return (Factory) MembersInjectors.injectMembers(this.b, new Factory());
        }

        public static dagger.internal.Factory<Factory> create(MembersInjector<Factory> factoryMembersInjector) {
            return new Factory_Factory(factoryMembersInjector);
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class RetryMap {
        final Map<String, hb> a;

        @Inject
        RetryMap() {
            this.a = new HashMap();
        }

        final hb a(String str) {
            return (hb) this.a.get(str);
        }
    }

    /* compiled from: vungle */
    public enum RetryMap_Factory implements dagger.internal.Factory<RetryMap> {
        ;

        private RetryMap_Factory(String str) {
        }

        public final RetryMap get() {
            return new RetryMap();
        }

        public static dagger.internal.Factory<RetryMap> create() {
            return INSTANCE;
        }
    }

    static class a extends RuntimeException {
        a(String str) {
            super(str);
        }

        a(String str, Throwable th) {
            super(str, th);
        }
    }

    static class b extends RuntimeException {
        b(String str) {
            super(str);
        }
    }

    static {
        h = new Object();
    }

    @Inject
    PrepareAdRunnable() {
    }

    public final void run() {
        synchronized (h) {
            Logger.d(Logger.PREPARE_TAG, "run PrepareAdRunnable. adId = " + this.i);
            RetryMap retryMap = this.f;
            try {
                String str = this.i;
                if (!retryMap.a.containsKey(str)) {
                    retryMap.a.put(str, new hb());
                }
                if (a().i() == com.vungle.publisher.db.model.Ad.a.ready) {
                    retryMap.a.remove(this.i);
                    this.a.a(new an());
                }
            } catch (a e) {
                Logger.w(Logger.PREPARE_TAG, e.getMessage() + " for ad.id " + this.i + ". retryCount = " + retryMap.a(this.i).b);
                this.a.a(new ab(retryMap.a(this.i)));
            } catch (Throwable e2) {
                this.g.a(Logger.PREPARE_TAG, "error processing ad.id: " + this.i, e2);
                this.a.a(new ab(retryMap.a(this.i)));
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.vungle.publisher.db.model.LocalAd a() {
        /*
        r7 = this;
        r1 = r7.i;
        r0 = r7.b;
        r0 = r0.a(r1);
        if (r0 != 0) goto L_0x001f;
    L_0x000a:
        r0 = new java.lang.IllegalArgumentException;
        r2 = new java.lang.StringBuilder;
        r3 = "no ad ";
        r2.<init>(r3);
        r1 = r2.append(r1);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x001f:
        r2 = r0.i();
        r3 = 0;
        r4 = com.vungle.publisher.ad.prepare.PrepareAdRunnable.1.a;
        r5 = r2.ordinal();
        r4 = r4[r5];
        switch(r4) {
            case 1: goto L_0x004a;
            case 2: goto L_0x004a;
            case 3: goto L_0x005f;
            default: goto L_0x002f;
        };
    L_0x002f:
        r1 = r7.a(r0);	 Catch:{ all -> 0x00a2 }
        r0.m();	 Catch:{ Exception -> 0x0080 }
        r2 = r1.i();	 Catch:{ Exception -> 0x0080 }
        r3 = com.vungle.publisher.db.model.Ad.a.ready;	 Catch:{ Exception -> 0x0080 }
        if (r2 != r3) goto L_0x0048;
    L_0x003e:
        r2 = r7.a;	 Catch:{ Exception -> 0x0080 }
        r3 = new com.vungle.publisher.z;	 Catch:{ Exception -> 0x0080 }
        r3.<init>();	 Catch:{ Exception -> 0x0080 }
        r2.a(r3);	 Catch:{ Exception -> 0x0080 }
    L_0x0048:
        r0 = r1;
    L_0x0049:
        return r0;
    L_0x004a:
        r0 = new com.vungle.publisher.ad.prepare.PrepareAdRunnable$a;
        r1 = new java.lang.StringBuilder;
        r3 = "ad status: ";
        r1.<init>(r3);
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x005f:
        r2 = "VunglePrepare";
        r3 = new java.lang.StringBuilder;
        r4 = "ad already ";
        r3.<init>(r4);
        r4 = com.vungle.publisher.db.model.Ad.a.ready;
        r3 = r3.append(r4);
        r4 = ": ";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.vungle.log.Logger.d(r2, r1);
        goto L_0x0049;
    L_0x0080:
        r2 = move-exception;
        r3 = r7.g;
        r4 = "VunglePrepare";
        r5 = new java.lang.StringBuilder;
        r6 = "error saving ad ";
        r5.<init>(r6);
        r0 = r0.d();
        r0 = r5.append(r0);
        r5 = " to database";
        r0 = r0.append(r5);
        r0 = r0.toString();
        r3.a(r4, r0, r2);
        goto L_0x0048;
    L_0x00a2:
        r2 = move-exception;
        r0.m();	 Catch:{ Exception -> 0x00b9 }
        r1 = r3.i();	 Catch:{ Exception -> 0x00b9 }
        r3 = com.vungle.publisher.db.model.Ad.a.ready;	 Catch:{ Exception -> 0x00b9 }
        if (r1 != r3) goto L_0x00b8;
    L_0x00ae:
        r1 = r7.a;	 Catch:{ Exception -> 0x00b9 }
        r3 = new com.vungle.publisher.z;	 Catch:{ Exception -> 0x00b9 }
        r3.<init>();	 Catch:{ Exception -> 0x00b9 }
        r1.a(r3);	 Catch:{ Exception -> 0x00b9 }
    L_0x00b8:
        throw r2;
    L_0x00b9:
        r1 = move-exception;
        r3 = r7.g;
        r4 = "VunglePrepare";
        r5 = new java.lang.StringBuilder;
        r6 = "error saving ad ";
        r5.<init>(r6);
        r0 = r0.d();
        r0 = r5.append(r0);
        r5 = " to database";
        r0 = r0.append(r5);
        r0 = r0.toString();
        r3.a(r4, r0, r1);
        goto L_0x00b8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.ad.prepare.PrepareAdRunnable.a():com.vungle.publisher.db.model.LocalAd");
    }

    private LocalAd a(LocalAd localAd) {
        String d = localAd.d();
        com.vungle.publisher.db.model.Ad.a i = localAd.i();
        if (i == com.vungle.publisher.db.model.Ad.a.failed) {
            com.vungle.publisher.db.model.Ad.a aVar = com.vungle.publisher.db.model.Ad.a.preparing;
            long currentTimeMillis = System.currentTimeMillis();
            long j = localAd.j();
            if (currentTimeMillis < j) {
                Logger.d(Logger.PREPARE_TAG, "clock change detected; updating ad.id " + d + " status from " + i + " to " + aVar);
                localAd.a(aVar);
            } else {
                currentTimeMillis = (currentTimeMillis - j) / HlsChunkSource.DEFAULT_PLAYLIST_BLACKLIST_MS;
                if (currentTimeMillis >= 1440) {
                    Logger.d(Logger.PREPARE_TAG, "retrying " + com.vungle.publisher.db.model.Ad.a.failed + " ad.id " + d + " after " + currentTimeMillis + "/1440 minutes; updating status from " + i + " to " + aVar);
                    localAd.a(aVar);
                } else {
                    throw new a("ad marked failed " + currentTimeMillis + " minutes ago");
                }
            }
        }
        com.vungle.publisher.db.model.Ad.a aVar2 = com.vungle.publisher.db.model.Ad.a.failed;
        i = localAd.i();
        while (true) {
            int i2 = localAd.y;
            if (i2 < 3) {
                try {
                    switch (1.a[i.ordinal()]) {
                        case Yytoken.TYPE_COMMA /*5*/:
                            this.c.a(localAd);
                            localAd.a(com.vungle.publisher.db.model.Ad.a.preparing);
                            break;
                        case Yytoken.TYPE_COLON /*6*/:
                            break;
                        case R.styleable.Toolbar_contentInsetLeft /*7*/:
                            c(localAd);
                            break;
                        default:
                            throw new IllegalStateException("unexpected ad.status: " + i);
                    }
                    Logger.d(Logger.PREPARE_TAG, "prepare_retry_count " + i2 + " for ad " + this.i);
                    b(localAd);
                    return localAd;
                } catch (b e) {
                    Logger.w(Logger.PREPARE_TAG, e.getMessage() + " for ad.id: " + this.i);
                    localAd.y++;
                }
            } else {
                localAd.a(com.vungle.publisher.db.model.Ad.a.failed);
                throw new a("failed to prepare ad after " + i2 + " attempts");
            }
        }
    }

    private void b(LocalAd localAd) {
        d(localAd);
        Object obj = 1;
        for (ct ctVar : localAd.A()) {
            Object obj2;
            com.vungle.publisher.cg.a e = ctVar.e();
            Logger.v(Logger.PREPARE_TAG, ctVar.f() + " has status " + e);
            if (e == com.vungle.publisher.cg.a.aware || e == com.vungle.publisher.cg.a.failed) {
                ctVar.b(com.vungle.publisher.cg.a.queued);
                hb a = this.f.a(this.i);
                Logger.d(Logger.PREPARE_TAG, "begin preparing " + ctVar.f());
                this.d.a(this.e.a(ctVar, a), com.vungle.publisher.async.ScheduledPriorityExecutor.b.prepareLocalViewable);
            }
            if (e == com.vungle.publisher.cg.a.ready) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (obj2 == null) {
                obj = null;
            }
        }
        if (obj != null) {
            Logger.i(Logger.PREPARE_TAG, "ad ready " + localAd.d());
            localAd.a(com.vungle.publisher.db.model.Ad.a.ready);
            ((LocalAdReport) this.c.c.b(localAd)).d(Long.valueOf(System.currentTimeMillis()));
            return;
        }
        Logger.d(Logger.PREPARE_TAG, "ad not ready " + localAd.d());
    }

    private void c(LocalAd localAd) {
        String d = localAd.d();
        Logger.d(Logger.PREPARE_TAG, "re-verify prepare_retry_count " + localAd.y + " for ad " + d);
        d(localAd);
        ct[] A = localAd.A();
        int length = A.length;
        int i = 0;
        while (i < length) {
            ct ctVar = A[i];
            if (ctVar.t()) {
                i++;
            } else {
                throw new b(ctVar.f() + " re-verification failed for ad_id " + ctVar.d());
            }
        }
        com.vungle.publisher.db.model.Ad.a aVar = com.vungle.publisher.db.model.Ad.a.ready;
        Logger.i(Logger.PREPARE_TAG, "re-verified ad and set to " + aVar + ": " + d);
        this.c.a(localAd).d(Long.valueOf(-1));
        localAd.a(aVar);
    }

    private static void d(LocalAd localAd) throws a {
        Object obj;
        Object obj2 = null;
        LocalArchive z = localAd.z();
        LocalVideo localVideo = (LocalVideo) localAd.k();
        LocalArchive u = localAd.u();
        if (z != null) {
            obj = 1;
        } else {
            obj = null;
        }
        Object obj3 = localVideo != null ? 1 : null;
        Object obj4 = u != null ? 1 : null;
        if (!(obj == null && obj3 == null && obj4 == null)) {
            obj2 = 1;
        }
        String x = localAd.x();
        if (obj2 != null) {
            if (obj != null) {
                Logger.v(Logger.PREPARE_TAG, x + " has " + com.vungle.publisher.cg.b.preRoll + ": " + z.i.b);
            }
            if (obj3 != null) {
                Logger.v(Logger.PREPARE_TAG, x + " has " + com.vungle.publisher.cg.b.localVideo + ": " + localVideo.b.b);
            }
            if (obj4 != null) {
                Logger.v(Logger.PREPARE_TAG, x + " has " + com.vungle.publisher.cg.b.postRoll + ": " + u.i.b);
            }
        } else {
            localAd.a(com.vungle.publisher.db.model.Ad.a.invalid);
        }
        if (obj2 == null) {
            throw new a("invalid ad - no viewables");
        }
    }
}
