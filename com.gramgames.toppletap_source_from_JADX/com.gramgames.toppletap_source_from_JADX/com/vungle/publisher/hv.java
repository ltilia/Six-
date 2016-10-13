package com.vungle.publisher;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.protocol.EventTrackingHttpLogEntryDeleteDelegate;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.protocol.RequestLocalAdHttpResponseHandler;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import com.vungle.publisher.reporting.AdServiceReportingHandler;
import dagger.MembersInjector;
import dagger.internal.DoubleCheckLazy;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hv implements MembersInjector<RequestLocalAdHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<Factory> c;
    private final Provider<AdServiceReportingHandler> d;
    private final Provider<EventBus> e;
    private final Provider<EventTrackingHttpLogEntryDeleteDelegate> f;
    private final Provider<AdManager> g;
    private final Provider<SdkState> h;
    private final Provider<RequestLocalAdResponse.Factory> i;
    private final Provider<ProtocolHttpGateway> j;

    static {
        a = !hv.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        RequestLocalAdHttpResponseHandler requestLocalAdHttpResponseHandler = (RequestLocalAdHttpResponseHandler) obj;
        if (requestLocalAdHttpResponseHandler == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gq.a(requestLocalAdHttpResponseHandler, this.b);
        gq.b(requestLocalAdHttpResponseHandler, this.c);
        requestLocalAdHttpResponseHandler.a = (AdServiceReportingHandler) this.d.get();
        requestLocalAdHttpResponseHandler.b = (EventBus) this.e.get();
        requestLocalAdHttpResponseHandler.c = (EventTrackingHttpLogEntryDeleteDelegate) this.f.get();
        requestLocalAdHttpResponseHandler.d = DoubleCheckLazy.create(this.g);
        requestLocalAdHttpResponseHandler.e = DoubleCheckLazy.create(this.h);
        requestLocalAdHttpResponseHandler.k = (RequestLocalAdResponse.Factory) this.i.get();
        requestLocalAdHttpResponseHandler.l = (ScheduledPriorityExecutor) this.b.get();
        requestLocalAdHttpResponseHandler.m = (ProtocolHttpGateway) this.j.get();
    }

    private hv(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<AdServiceReportingHandler> provider3, Provider<EventBus> provider4, Provider<EventTrackingHttpLogEntryDeleteDelegate> provider5, Provider<AdManager> provider6, Provider<SdkState> provider7, Provider<RequestLocalAdResponse.Factory> provider8, Provider<ProtocolHttpGateway> provider9) {
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

    public static MembersInjector<RequestLocalAdHttpResponseHandler> a(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<AdServiceReportingHandler> provider3, Provider<EventBus> provider4, Provider<EventTrackingHttpLogEntryDeleteDelegate> provider5, Provider<AdManager> provider6, Provider<SdkState> provider7, Provider<RequestLocalAdResponse.Factory> provider8, Provider<ProtocolHttpGateway> provider9) {
        return new hv(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }
}
