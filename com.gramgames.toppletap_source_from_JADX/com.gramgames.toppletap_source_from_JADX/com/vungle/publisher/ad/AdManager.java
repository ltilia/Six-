package com.vungle.publisher.ad;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import com.google.android.gms.drive.DriveFile;
import com.vungle.log.Logger;
import com.vungle.publisher.FullScreenAdActivity;
import com.vungle.publisher.af;
import com.vungle.publisher.al;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.ba;
import com.vungle.publisher.be;
import com.vungle.publisher.bi;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.Ad.a;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAd.Factory;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.db.model.StreamingAd;
import com.vungle.publisher.db.model.Viewable;
import com.vungle.publisher.ek;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.et;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.fv;
import com.vungle.publisher.fw;
import com.vungle.publisher.hb;
import com.vungle.publisher.inject.annotations.FullScreenAdActivityClass;
import com.vungle.publisher.k;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.protocol.ProtocolHttpGateway.4;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import com.vungle.publisher.v;
import com.vungle.publisher.w;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.simple.parser.Yytoken;

@Singleton
/* compiled from: vungle */
public class AdManager {
    @Inject
    public AdPreparer a;
    @Inject
    public Context b;
    @Inject
    public ek c;
    @Inject
    public EventBus d;
    @Inject
    @FullScreenAdActivityClass
    public Class e;
    @Inject
    public ScheduledPriorityExecutor f;
    @Inject
    public Factory g;
    @Inject
    public fw h;
    @Inject
    public Lazy<PlayAdEventListener> i;
    @Inject
    public Lazy<AdAvailabilityEventListener> j;
    @Inject
    public Provider<PrepareStreamingAdEventListener> k;
    @Inject
    public ProtocolHttpGateway l;
    @Inject
    public SdkConfig m;
    @Inject
    public StreamingAd.Factory n;
    @Inject
    public Viewable.Factory o;
    @Inject
    public Lazy<SdkState> p;
    @Inject
    public LoggedException.Factory q;

    public class 1 implements Runnable {
        final /* synthetic */ SafeBundleAdConfig a;
        final /* synthetic */ AdManager b;

        public 1(AdManager adManager, SafeBundleAdConfig safeBundleAdConfig) {
            this.b = adManager;
            this.a = safeBundleAdConfig;
        }

        public final void run() {
            Throwable e;
            Ad ad = null;
            Object obj = null;
            Logger.d(Logger.AD_TAG, "AdManager.playAd()");
            try {
                AdManager adManager = this.b;
                SafeBundleAdConfig safeBundleAdConfig = this.a;
                Ad a = adManager.a(false);
                Ad a2 = adManager.a(a == null ? null : a.d(), safeBundleAdConfig);
                if (a2 != null) {
                    a = a2;
                }
                Logger.i(Logger.AD_TAG, "next ad " + (a == null ? null : a.x()));
                if (a == null) {
                    try {
                        Logger.d(Logger.AD_TAG, "no ad to play");
                        this.b.d.a(new be());
                    } catch (Exception e2) {
                        e = e2;
                        ad = a;
                        try {
                            this.b.q.a(Logger.AD_TAG, "error launching ad", e);
                            this.b.d.a(new bi(ad, false));
                        } finally {
                            this.b.l.c();
                        }
                    }
                } else {
                    ((PlayAdEventListener) this.b.i.get()).register();
                    Intent intent = new Intent(this.b.b, this.b.e);
                    intent.addFlags(DriveFile.MODE_READ_WRITE);
                    intent.putExtra(FullScreenAdActivity.AD_CONFIG_EXTRA_KEY, this.a);
                    intent.putExtra(FullScreenAdActivity.AD_ID_EXTRA_KEY, a.d());
                    this.b.b.startActivity(intent);
                    obj = 1;
                }
                if (obj == null) {
                    this.b.l.c();
                }
            } catch (Exception e3) {
                e = e3;
                this.b.q.a(Logger.AD_TAG, "error launching ad", e);
                this.b.d.a(new bi(ad, false));
            }
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ AdManager a;

        2(AdManager adManager) {
            this.a = adManager;
        }

        public final void run() {
            this.a.g.h();
        }
    }

    public static /* synthetic */ class 3 {
        public static final /* synthetic */ int[] a;

        static {
            a = new int[a.values().length];
            try {
                a[a.aware.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.failed.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.preparing.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.viewed.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[a.ready.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class AdAvailabilityEventListener extends et {
        @Inject
        public AdManager a;

        @Inject
        AdAvailabilityEventListener() {
        }

        public void onEvent(k kVar) {
            this.a.b(false);
        }
    }

    /* compiled from: vungle */
    public final class AdAvailabilityEventListener_Factory implements dagger.internal.Factory<AdAvailabilityEventListener> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<AdAvailabilityEventListener> b;

        static {
            a = !AdAvailabilityEventListener_Factory.class.desiredAssertionStatus();
        }

        public AdAvailabilityEventListener_Factory(MembersInjector<AdAvailabilityEventListener> adAvailabilityEventListenerMembersInjector) {
            if (a || adAvailabilityEventListenerMembersInjector != null) {
                this.b = adAvailabilityEventListenerMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final AdAvailabilityEventListener get() {
            return (AdAvailabilityEventListener) MembersInjectors.injectMembers(this.b, new AdAvailabilityEventListener());
        }

        public static dagger.internal.Factory<AdAvailabilityEventListener> create(MembersInjector<AdAvailabilityEventListener> adAvailabilityEventListenerMembersInjector) {
            return new AdAvailabilityEventListener_Factory(adAvailabilityEventListenerMembersInjector);
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class PlayAdEventListener extends et {
        final String a;
        @Inject
        public AdManager b;
        @Inject
        public LoggedException.Factory c;

        @Inject
        PlayAdEventListener() {
            this.a = Logger.PREPARE_TAG;
        }

        public void onEvent(al startPlayAdEvent) {
            try {
                startPlayAdEvent.a().b(a.viewed);
            } catch (Throwable e) {
                this.c.a(Logger.PREPARE_TAG, "error processing start play ad event", e);
            }
        }

        public void onEvent(v vVar) {
            Logger.d(Logger.PREPARE_TAG, "sent ad report - unregistering play ad listener");
            unregister();
        }

        public void onEvent(ba baVar) {
            Logger.d(Logger.PREPARE_TAG, "play ad failure - unregistering play ad listener");
            unregister();
        }
    }

    /* compiled from: vungle */
    public final class PlayAdEventListener_Factory implements dagger.internal.Factory<PlayAdEventListener> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<PlayAdEventListener> b;

        static {
            a = !PlayAdEventListener_Factory.class.desiredAssertionStatus();
        }

        public PlayAdEventListener_Factory(MembersInjector<PlayAdEventListener> playAdEventListenerMembersInjector) {
            if (a || playAdEventListenerMembersInjector != null) {
                this.b = playAdEventListenerMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final PlayAdEventListener get() {
            return (PlayAdEventListener) MembersInjectors.injectMembers(this.b, new PlayAdEventListener());
        }

        public static dagger.internal.Factory<PlayAdEventListener> create(MembersInjector<PlayAdEventListener> playAdEventListenerMembersInjector) {
            return new PlayAdEventListener_Factory(playAdEventListenerMembersInjector);
        }
    }

    /* compiled from: vungle */
    public static class PrepareStreamingAdEventListener extends et {
        final String a;
        volatile boolean b;
        volatile StreamingAd c;
        final long d;
        @Inject
        public StreamingAd.Factory e;

        @Inject
        PrepareStreamingAdEventListener() {
            this.a = Logger.PREPARE_TAG;
            this.d = System.currentTimeMillis();
        }

        final void a() {
            this.b = true;
            synchronized (this) {
                notifyAll();
            }
        }

        public void onEvent(w prepareStreamingAdFailureEvent) {
            unregister();
            Logger.d(Logger.PREPARE_TAG, "request streaming ad failure after " + (prepareStreamingAdFailureEvent.e - this.d) + " ms");
            a();
        }

        public void onEvent(af prepareStreamingAdSuccessEvent) {
            unregister();
            long j = prepareStreamingAdSuccessEvent.e - this.d;
            RequestStreamingAdResponse requestStreamingAdResponse = prepareStreamingAdSuccessEvent.a;
            if (Boolean.TRUE.equals(requestStreamingAdResponse.r)) {
                Logger.d(Logger.PREPARE_TAG, "received streaming ad " + requestStreamingAdResponse.f() + " after " + j + " ms");
                String f = requestStreamingAdResponse.f();
                StreamingAd streamingAd = (StreamingAd) this.e.a((Object) f);
                if (streamingAd == null) {
                    streamingAd = this.e.a(requestStreamingAdResponse);
                    this.c = streamingAd;
                    Logger.d(Logger.PREPARE_TAG, "inserting new " + streamingAd.x());
                    try {
                        streamingAd.l();
                    } catch (SQLException e) {
                        Logger.d(Logger.PREPARE_TAG, "did not insert streaming ad - possible duplicate");
                    }
                } else {
                    try {
                        this.e.a((Ad) streamingAd, (RequestAdResponse) requestStreamingAdResponse);
                    } catch (Throwable e2) {
                        Logger.w(Logger.PREPARE_TAG, "error updating ad " + f, e2);
                    }
                    a i = streamingAd.i();
                    switch (3.a[i.ordinal()]) {
                        case Yytoken.TYPE_LEFT_BRACE /*1*/:
                            Logger.w(Logger.PREPARE_TAG, "unexpected ad status " + i + " for " + streamingAd.x());
                            break;
                        case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                        case Yytoken.TYPE_COMMA /*5*/:
                            break;
                        default:
                            Logger.w(Logger.PREPARE_TAG, "existing " + streamingAd.x() + " with status " + i + " - ignoring");
                            break;
                    }
                    Logger.d(Logger.PREPARE_TAG, "existing " + streamingAd.x() + " with status " + i);
                    if (i != a.ready) {
                        streamingAd.b(a.ready);
                    }
                    this.c = streamingAd;
                }
            } else {
                Logger.d(Logger.PREPARE_TAG, "no streaming ad to play after " + j + " ms");
            }
            a();
        }
    }

    /* compiled from: vungle */
    public final class PrepareStreamingAdEventListener_Factory implements dagger.internal.Factory<PrepareStreamingAdEventListener> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<PrepareStreamingAdEventListener> b;

        static {
            a = !PrepareStreamingAdEventListener_Factory.class.desiredAssertionStatus();
        }

        public PrepareStreamingAdEventListener_Factory(MembersInjector<PrepareStreamingAdEventListener> prepareStreamingAdEventListenerMembersInjector) {
            if (a || prepareStreamingAdEventListenerMembersInjector != null) {
                this.b = prepareStreamingAdEventListenerMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final PrepareStreamingAdEventListener get() {
            return (PrepareStreamingAdEventListener) MembersInjectors.injectMembers(this.b, new PrepareStreamingAdEventListener());
        }

        public static dagger.internal.Factory<PrepareStreamingAdEventListener> create(MembersInjector<PrepareStreamingAdEventListener> prepareStreamingAdEventListenerMembersInjector) {
            return new PrepareStreamingAdEventListener_Factory(prepareStreamingAdEventListenerMembersInjector);
        }
    }

    @Inject
    AdManager() {
    }

    public final boolean a() {
        SdkState sdkState = (SdkState) this.p.get();
        if (!sdkState.l.get() && sdkState.b()) {
            if ((this.g.f() != null ? 1 : null) != null) {
                return true;
            }
        }
        return false;
    }

    public final LocalAd a(String str) {
        return this.g.a(str);
    }

    final LocalAd a(boolean z) {
        if (this.c.o()) {
            LocalAd a;
            if (z) {
                a = this.g.a(a.ready, a.preparing);
            } else {
                a = this.g.f();
            }
            if (a == null) {
                Logger.d(Logger.PREPARE_TAG, "no local ad available");
                this.l.a(new hb());
                return null;
            }
            a i = a.i();
            if (i != a.preparing) {
                if (i == a.ready) {
                    Logger.v(Logger.PREPARE_TAG, "local ad already available for " + a.d());
                }
                return a;
            } else if (z) {
                Logger.d(Logger.PREPARE_TAG, "local ad partially prepared, restarting preparation for " + a.d());
                this.a.a(a.d());
                return null;
            } else {
                Logger.i(Logger.PREPARE_TAG, "local ad partially prepared, but not restarting preparation for " + a.d());
                return null;
            }
        }
        Logger.w(Logger.PREPARE_TAG, "unable to fetch local ad -  no external storage available");
        return null;
    }

    final StreamingAd a(String str, SafeBundleAdConfig safeBundleAdConfig) {
        Throwable th;
        Throwable th2;
        StreamingAd streamingAd;
        StreamingAd streamingAd2 = null;
        boolean z = false;
        try {
            if (this.m.b) {
                fv a = this.h.a();
                z = this.m.c.contains(a);
                Logger.d(Logger.PREPARE_TAG, "ad streaming " + (z ? "enabled" : "disabled") + " for " + a + " connectivity");
            } else {
                Logger.d(Logger.PREPARE_TAG, "ad streaming disabled");
            }
            if (!z) {
                return null;
            }
            Logger.d(Logger.PREPARE_TAG, "requesting streaming ad");
            PrepareStreamingAdEventListener prepareStreamingAdEventListener = (PrepareStreamingAdEventListener) this.k.get();
            prepareStreamingAdEventListener.register();
            ProtocolHttpGateway protocolHttpGateway = this.l;
            protocolHttpGateway.e.a(new 4(protocolHttpGateway, str, safeBundleAdConfig), b.requestStreamingAd);
            long j = prepareStreamingAdEventListener.d;
            int i = this.m.d;
            Logger.d(Logger.CONFIG_TAG, "streaming response timeout config " + i + " ms");
            long j2 = ((long) i) + j;
            synchronized (prepareStreamingAdEventListener) {
                StreamingAd streamingAd3;
                while (!prepareStreamingAdEventListener.b) {
                    try {
                        long currentTimeMillis = j2 - System.currentTimeMillis();
                        if (currentTimeMillis > 0) {
                            try {
                                prepareStreamingAdEventListener.wait(currentTimeMillis);
                            } catch (InterruptedException e) {
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        streamingAd3 = null;
                        th2 = th;
                    }
                }
                break;
                j2 = System.currentTimeMillis() - j;
                if (prepareStreamingAdEventListener.b) {
                    streamingAd3 = prepareStreamingAdEventListener.c;
                    if (streamingAd3 != null) {
                        try {
                            Logger.d(Logger.PREPARE_TAG, "request streaming ad success after " + j2 + " ms " + streamingAd3.x());
                            streamingAd2 = streamingAd3;
                        } catch (Throwable th4) {
                            th2 = th4;
                            try {
                                throw th2;
                            } catch (Throwable e2) {
                                th2 = e2;
                                streamingAd = streamingAd3;
                            }
                        }
                    } else {
                        streamingAd2 = streamingAd3;
                    }
                } else {
                    Logger.d(Logger.PREPARE_TAG, "request streaming ad timeout after " + j2 + " ms");
                    prepareStreamingAdEventListener.a();
                }
                try {
                    return streamingAd2;
                } catch (Throwable th32) {
                    th = th32;
                    streamingAd3 = streamingAd2;
                    th2 = th;
                    throw th2;
                }
            }
        } catch (Throwable e22) {
            th = e22;
            streamingAd = null;
            th2 = th;
            this.q.a(Logger.PREPARE_TAG, "error getting streaming ad", th2);
            return streamingAd;
        }
    }

    public final void b(boolean z) {
        a(z);
        this.f.a(b.deleteExpiredAds);
        Long g = this.g.g();
        if (g != null) {
            this.f.a(new 2(this), b.deleteExpiredAds, g.longValue() - System.currentTimeMillis());
        }
    }
}
