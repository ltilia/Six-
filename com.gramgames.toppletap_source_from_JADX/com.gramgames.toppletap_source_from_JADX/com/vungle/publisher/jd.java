package com.vungle.publisher;

import com.vungle.publisher.db.model.AdReport.Factory;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.db.model.StreamingAdReport;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.reporting.AdReportManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jd implements MembersInjector<AdReportManager> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<Factory> c;
    private final Provider<LocalAdReport.Factory> d;
    private final Provider<ProtocolHttpGateway> e;
    private final Provider<SdkState> f;
    private final Provider<StreamingAdReport.Factory> g;
    private final Provider<LoggedException.Factory> h;

    static {
        a = !jd.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AdReportManager adReportManager = (AdReportManager) obj;
        if (adReportManager == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        adReportManager.a = (EventBus) this.b.get();
        adReportManager.b = (Factory) this.c.get();
        adReportManager.c = (LocalAdReport.Factory) this.d.get();
        adReportManager.d = (ProtocolHttpGateway) this.e.get();
        adReportManager.e = (SdkState) this.f.get();
        adReportManager.f = (StreamingAdReport.Factory) this.g.get();
        adReportManager.g = (LoggedException.Factory) this.h.get();
    }

    private jd(Provider<EventBus> provider, Provider<Factory> provider2, Provider<LocalAdReport.Factory> provider3, Provider<ProtocolHttpGateway> provider4, Provider<SdkState> provider5, Provider<StreamingAdReport.Factory> provider6, Provider<LoggedException.Factory> provider7) {
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

    public static MembersInjector<AdReportManager> a(Provider<EventBus> provider, Provider<Factory> provider2, Provider<LocalAdReport.Factory> provider3, Provider<ProtocolHttpGateway> provider4, Provider<SdkState> provider5, Provider<StreamingAdReport.Factory> provider6, Provider<LoggedException.Factory> provider7) {
        return new jd(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
