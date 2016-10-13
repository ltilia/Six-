package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.protocol.ProtocolHttpGateway.PrepareLocalAdEventListener;
import com.vungle.publisher.protocol.ReportAdHttpTransactionFactory;
import com.vungle.publisher.protocol.RequestConfigHttpTransactionFactory;
import com.vungle.publisher.protocol.RequestLocalAdHttpTransactionFactory;
import com.vungle.publisher.protocol.RequestStreamingAdHttpTransactionFactory;
import com.vungle.publisher.protocol.SessionEndHttpTransactionFactory;
import com.vungle.publisher.protocol.SessionStartHttpTransactionFactory;
import com.vungle.publisher.protocol.TrackInstallHttpTransactionFactory;
import com.vungle.publisher.protocol.UnfilledAdHttpTransactionFactory;
import com.vungle.publisher.reporting.AdServiceReportingHandler;
import dagger.MembersInjector;
import dagger.internal.DoubleCheckLazy;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hh implements MembersInjector<ProtocolHttpGateway> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<Factory> c;
    private final Provider<ScheduledPriorityExecutor> d;
    private final Provider<EventBus> e;
    private final Provider<SdkState> f;
    private final Provider<PrepareLocalAdEventListener> g;
    private final Provider<ReportAdHttpTransactionFactory> h;
    private final Provider<RequestConfigHttpTransactionFactory> i;
    private final Provider<RequestLocalAdHttpTransactionFactory> j;
    private final Provider<RequestStreamingAdHttpTransactionFactory> k;
    private final Provider<SafeBundleAdConfigFactory> l;
    private final Provider<SessionEndHttpTransactionFactory> m;
    private final Provider<SessionStartHttpTransactionFactory> n;
    private final Provider<TrackInstallHttpTransactionFactory> o;
    private final Provider<UnfilledAdHttpTransactionFactory> p;
    private final Provider<AdServiceReportingHandler> q;

    static {
        a = !hh.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ProtocolHttpGateway protocolHttpGateway = (ProtocolHttpGateway) obj;
        if (protocolHttpGateway == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gl.a(protocolHttpGateway, this.b);
        gl.b(protocolHttpGateway, this.c);
        gl.c(protocolHttpGateway, this.d);
        protocolHttpGateway.a = (EventBus) this.e.get();
        protocolHttpGateway.b = DoubleCheckLazy.create(this.f);
        protocolHttpGateway.f = (PrepareLocalAdEventListener) this.g.get();
        protocolHttpGateway.g = (ReportAdHttpTransactionFactory) this.h.get();
        protocolHttpGateway.h = (RequestConfigHttpTransactionFactory) this.i.get();
        protocolHttpGateway.i = (RequestLocalAdHttpTransactionFactory) this.j.get();
        protocolHttpGateway.j = (RequestStreamingAdHttpTransactionFactory) this.k.get();
        protocolHttpGateway.k = (SafeBundleAdConfigFactory) this.l.get();
        protocolHttpGateway.l = (SessionEndHttpTransactionFactory) this.m.get();
        protocolHttpGateway.m = (SessionStartHttpTransactionFactory) this.n.get();
        protocolHttpGateway.n = DoubleCheckLazy.create(this.o);
        protocolHttpGateway.o = (UnfilledAdHttpTransactionFactory) this.p.get();
        protocolHttpGateway.p = (AdServiceReportingHandler) this.q.get();
    }

    private hh(Provider<Context> provider, Provider<Factory> provider2, Provider<ScheduledPriorityExecutor> provider3, Provider<EventBus> provider4, Provider<SdkState> provider5, Provider<PrepareLocalAdEventListener> provider6, Provider<ReportAdHttpTransactionFactory> provider7, Provider<RequestConfigHttpTransactionFactory> provider8, Provider<RequestLocalAdHttpTransactionFactory> provider9, Provider<RequestStreamingAdHttpTransactionFactory> provider10, Provider<SafeBundleAdConfigFactory> provider11, Provider<SessionEndHttpTransactionFactory> provider12, Provider<SessionStartHttpTransactionFactory> provider13, Provider<TrackInstallHttpTransactionFactory> provider14, Provider<UnfilledAdHttpTransactionFactory> provider15, Provider<AdServiceReportingHandler> provider16) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
                            if (a || provider6 != null) {
                                this.g = provider6;
                                if (a || provider7 != null) {
                                    this.h = provider7;
                                    if (a || provider8 != null) {
                                        this.i = provider8;
                                        if (a || provider9 != null) {
                                            this.j = provider9;
                                            if (a || provider10 != null) {
                                                this.k = provider10;
                                                if (a || provider11 != null) {
                                                    this.l = provider11;
                                                    if (a || provider12 != null) {
                                                        this.m = provider12;
                                                        if (a || provider13 != null) {
                                                            this.n = provider13;
                                                            if (a || provider14 != null) {
                                                                this.o = provider14;
                                                                if (a || provider15 != null) {
                                                                    this.p = provider15;
                                                                    if (a || provider16 != null) {
                                                                        this.q = provider16;
                                                                        return;
                                                                    }
                                                                    throw new AssertionError();
                                                                }
                                                                throw new AssertionError();
                                                            }
                                                            throw new AssertionError();
                                                        }
                                                        throw new AssertionError();
                                                    }
                                                    throw new AssertionError();
                                                }
                                                throw new AssertionError();
                                            }
                                            throw new AssertionError();
                                        }
                                        throw new AssertionError();
                                    }
                                    throw new AssertionError();
                                }
                                throw new AssertionError();
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ProtocolHttpGateway> a(Provider<Context> provider, Provider<Factory> provider2, Provider<ScheduledPriorityExecutor> provider3, Provider<EventBus> provider4, Provider<SdkState> provider5, Provider<PrepareLocalAdEventListener> provider6, Provider<ReportAdHttpTransactionFactory> provider7, Provider<RequestConfigHttpTransactionFactory> provider8, Provider<RequestLocalAdHttpTransactionFactory> provider9, Provider<RequestStreamingAdHttpTransactionFactory> provider10, Provider<SafeBundleAdConfigFactory> provider11, Provider<SessionEndHttpTransactionFactory> provider12, Provider<SessionStartHttpTransactionFactory> provider13, Provider<TrackInstallHttpTransactionFactory> provider14, Provider<UnfilledAdHttpTransactionFactory> provider15, Provider<AdServiceReportingHandler> provider16) {
        return new hh(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16);
    }
}
