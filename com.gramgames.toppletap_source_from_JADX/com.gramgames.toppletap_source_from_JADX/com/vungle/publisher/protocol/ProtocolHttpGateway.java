package com.vungle.publisher.protocol;

import android.os.SystemClock;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.gms.games.GamesStatusCodes;
import com.vungle.log.Logger;
import com.vungle.publisher.SafeBundleAdConfigFactory;
import com.vungle.publisher.ad.SafeBundleAdConfig;
import com.vungle.publisher.aj;
import com.vungle.publisher.an;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.db.model.AdReport;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.StreamingAdReport;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.et;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.gk;
import com.vungle.publisher.hb;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.reporting.AdServiceReportingHandler;
import com.vungle.publisher.w;
import com.vungle.publisher.y;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class ProtocolHttpGateway extends gk {
    @Inject
    public EventBus a;
    @Inject
    public Lazy<SdkState> b;
    @Inject
    public PrepareLocalAdEventListener f;
    @Inject
    public ReportAdHttpTransactionFactory g;
    @Inject
    public RequestConfigHttpTransactionFactory h;
    @Inject
    public RequestLocalAdHttpTransactionFactory i;
    @Inject
    public RequestStreamingAdHttpTransactionFactory j;
    @Inject
    public SafeBundleAdConfigFactory k;
    @Inject
    public SessionEndHttpTransactionFactory l;
    @Inject
    public SessionStartHttpTransactionFactory m;
    @Inject
    public Lazy<TrackInstallHttpTransactionFactory> n;
    @Inject
    public UnfilledAdHttpTransactionFactory o;
    @Inject
    public AdServiceReportingHandler p;

    public class 1 implements Runnable {
        final /* synthetic */ AdReport a;
        final /* synthetic */ ProtocolHttpGateway b;

        public 1(ProtocolHttpGateway protocolHttpGateway, AdReport adReport) {
            this.b = protocolHttpGateway;
            this.a = adReport;
        }

        public final void run() {
            try {
                HttpTransaction a;
                ReportAdHttpTransactionFactory reportAdHttpTransactionFactory = this.b.g;
                AdReport adReport = this.a;
                if (adReport instanceof LocalAdReport) {
                    a = reportAdHttpTransactionFactory.a((LocalAdReport) adReport);
                } else if (adReport instanceof StreamingAdReport) {
                    a = reportAdHttpTransactionFactory.a((StreamingAdReport) adReport);
                } else {
                    throw new UnsupportedOperationException("unknown report type " + adReport);
                }
                a.a();
            } catch (Throwable e) {
                this.b.d.a(Logger.PROTOCOL_TAG, "error sending report ad", e);
            }
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ ProtocolHttpGateway a;

        2(ProtocolHttpGateway protocolHttpGateway) {
            this.a = protocolHttpGateway;
        }

        public final void run() {
            try {
                this.a.h.a().a();
            } catch (Throwable e) {
                this.a.d.a(Logger.PROTOCOL_TAG, "error sending request config", e);
            }
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ SdkState a;
        final /* synthetic */ hb b;
        final /* synthetic */ ProtocolHttpGateway c;

        3(ProtocolHttpGateway protocolHttpGateway, SdkState sdkState, hb hbVar) {
            this.c = protocolHttpGateway;
            this.a = sdkState;
            this.b = hbVar;
        }

        public final void run() {
            try {
                if (this.a.n.compareAndSet(false, true)) {
                    this.c.p.a = SystemClock.elapsedRealtime();
                    this.c.f.register();
                    this.c.i.a(this.b).a();
                    return;
                }
                Logger.d(Logger.PROTOCOL_TAG, "request ad already in progress");
            } catch (Throwable e) {
                this.c.d.a(Logger.PROTOCOL_TAG, "error requesting local ad", e);
                this.c.b(this.b);
            }
        }
    }

    public class 4 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ SafeBundleAdConfig b;
        final /* synthetic */ ProtocolHttpGateway c;

        public 4(ProtocolHttpGateway protocolHttpGateway, String str, SafeBundleAdConfig safeBundleAdConfig) {
            this.c = protocolHttpGateway;
            this.a = str;
            this.b = safeBundleAdConfig;
        }

        public final void run() {
            try {
                this.c.j.a(this.a, this.b).a();
            } catch (Throwable e) {
                this.c.d.b(Logger.PROTOCOL_TAG, "error creating request streaming ad message", e);
                this.c.b();
            } catch (Throwable e2) {
                this.c.d.a(Logger.PROTOCOL_TAG, "error requesting streaming ad", e2);
                this.c.b();
            }
        }
    }

    public class 5 implements Runnable {
        final /* synthetic */ long a;
        final /* synthetic */ long b;
        final /* synthetic */ ProtocolHttpGateway c;

        public 5(ProtocolHttpGateway protocolHttpGateway, long j, long j2) {
            this.c = protocolHttpGateway;
            this.a = j;
            this.b = j2;
        }

        public final void run() {
            try {
                this.c.l.a(this.a, this.b).a();
            } catch (Throwable e) {
                this.c.d.a(Logger.PROTOCOL_TAG, "error sending session end", e);
            }
        }
    }

    public class 6 implements Runnable {
        final /* synthetic */ long a;
        final /* synthetic */ ProtocolHttpGateway b;

        public 6(ProtocolHttpGateway protocolHttpGateway, long j) {
            this.b = protocolHttpGateway;
            this.a = j;
        }

        public final void run() {
            try {
                this.b.m.a(this.a).a();
            } catch (Throwable e) {
                this.b.d.a(Logger.PROTOCOL_TAG, "error sending session start", e);
            }
        }
    }

    class 7 implements Runnable {
        final /* synthetic */ hb a;
        final /* synthetic */ ProtocolHttpGateway b;

        7(ProtocolHttpGateway protocolHttpGateway, hb hbVar) {
            this.b = protocolHttpGateway;
            this.a = hbVar;
        }

        public final void run() {
            try {
                ((TrackInstallHttpTransactionFactory) this.b.n.get()).a().a();
            } catch (Throwable e) {
                this.b.d.a(Logger.PROTOCOL_TAG, "error sending track install", e);
                this.b.c(this.a);
            }
        }
    }

    class 8 implements Runnable {
        final /* synthetic */ ProtocolHttpGateway a;

        8(ProtocolHttpGateway protocolHttpGateway) {
            this.a = protocolHttpGateway;
        }

        public final void run() {
            try {
                this.a.o.a(System.currentTimeMillis() / 1000).a();
            } catch (Throwable e) {
                this.a.d.a(Logger.PROTOCOL_TAG, "error sending unfilled ad", e);
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class PrepareLocalAdEventListener extends et {
        @Inject
        public Provider<ProtocolHttpGateway> a;

        @Inject
        PrepareLocalAdEventListener() {
        }

        public void onEvent(an anVar) {
            a(null);
        }

        public void onEvent(aj prepareAdRecoverableFailureEvent) {
            a(prepareAdRecoverableFailureEvent.a);
        }

        public void onEvent(y yVar) {
            a(null);
        }

        private void a(hb hbVar) {
            ((ProtocolHttpGateway) this.a.get()).b(hbVar);
        }
    }

    /* compiled from: vungle */
    public final class PrepareLocalAdEventListener_Factory implements Factory<PrepareLocalAdEventListener> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<PrepareLocalAdEventListener> b;

        static {
            a = !PrepareLocalAdEventListener_Factory.class.desiredAssertionStatus();
        }

        public PrepareLocalAdEventListener_Factory(MembersInjector<PrepareLocalAdEventListener> prepareLocalAdEventListenerMembersInjector) {
            if (a || prepareLocalAdEventListenerMembersInjector != null) {
                this.b = prepareLocalAdEventListenerMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final PrepareLocalAdEventListener get() {
            return (PrepareLocalAdEventListener) MembersInjectors.injectMembers(this.b, new PrepareLocalAdEventListener());
        }

        public static Factory<PrepareLocalAdEventListener> create(MembersInjector<PrepareLocalAdEventListener> prepareLocalAdEventListenerMembersInjector) {
            return new PrepareLocalAdEventListener_Factory(prepareLocalAdEventListenerMembersInjector);
        }
    }

    @Inject
    ProtocolHttpGateway() {
    }

    public final void a() {
        this.e.a(new 2(this), b.requestConfig);
    }

    public final void a(hb hbVar) {
        SdkState sdkState = (SdkState) this.b.get();
        long max = Math.max(Math.max(0, sdkState.o.getLong("VgSleepWakeupTime", 0) - System.currentTimeMillis()), (long) hbVar.a(GamesStatusCodes.STATUS_REQUEST_UPDATE_PARTIAL_SUCCESS));
        Logger.d(Logger.PROTOCOL_TAG, "scheduling local ad request in " + max + " ms");
        this.e.a(new 3(this, sdkState, hbVar), b.requestLocalAd, max);
    }

    final void b(hb hbVar) {
        ((SdkState) this.b.get()).n.set(false);
        this.f.unregister();
        if (hbVar != null) {
            a(hbVar);
        }
    }

    final void b() {
        this.a.a(new w());
    }

    public final void c(hb hbVar) {
        this.e.a(new 7(this, hbVar), b.reportInstall, (long) hbVar.a(ExoPlayer.Factory.DEFAULT_MIN_REBUFFER_MS));
    }

    public final void c() {
        this.e.a(new 8(this), b.unfilledAd);
    }
}
