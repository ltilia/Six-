package com.vungle.publisher;

import com.vungle.publisher.db.model.AdReport.Factory;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.reporting.AdReportEventListener;
import com.vungle.publisher.reporting.AdReportManager;
import com.vungle.publisher.reporting.AdServiceReportingHandler;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jc implements MembersInjector<AdReportEventListener> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<AdServiceReportingHandler> c;
    private final Provider<Factory> d;
    private final Provider<AdReportManager> e;
    private final Provider<LoggedException.Factory> f;
    private final Provider<gh> g;

    static {
        a = !jc.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AdReportEventListener adReportEventListener = (AdReportEventListener) obj;
        if (adReportEventListener == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eu.a(adReportEventListener, this.b);
        adReportEventListener.a = (AdServiceReportingHandler) this.c.get();
        adReportEventListener.b = (Factory) this.d.get();
        adReportEventListener.c = (AdReportManager) this.e.get();
        adReportEventListener.d = (LoggedException.Factory) this.f.get();
        adReportEventListener.e = (gh) this.g.get();
    }

    private jc(Provider<EventBus> provider, Provider<AdServiceReportingHandler> provider2, Provider<Factory> provider3, Provider<AdReportManager> provider4, Provider<LoggedException.Factory> provider5, Provider<gh> provider6) {
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

    public static MembersInjector<AdReportEventListener> a(Provider<EventBus> provider, Provider<AdServiceReportingHandler> provider2, Provider<Factory> provider3, Provider<AdReportManager> provider4, Provider<LoggedException.Factory> provider5, Provider<gh> provider6) {
        return new jc(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
