package com.vungle.publisher;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.ad.prepare.PrepareAdRunnable;
import com.vungle.publisher.ad.prepare.PrepareAdRunnable.RetryMap;
import com.vungle.publisher.ad.prepare.PrepareViewableRunnable.Factory;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.reporting.AdReportManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bl implements MembersInjector<PrepareAdRunnable> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<AdManager> c;
    private final Provider<AdReportManager> d;
    private final Provider<ScheduledPriorityExecutor> e;
    private final Provider<Factory> f;
    private final Provider<RetryMap> g;
    private final Provider<LoggedException.Factory> h;

    static {
        a = !bl.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        PrepareAdRunnable prepareAdRunnable = (PrepareAdRunnable) obj;
        if (prepareAdRunnable == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        prepareAdRunnable.a = (EventBus) this.b.get();
        prepareAdRunnable.b = (AdManager) this.c.get();
        prepareAdRunnable.c = (AdReportManager) this.d.get();
        prepareAdRunnable.d = (ScheduledPriorityExecutor) this.e.get();
        prepareAdRunnable.e = (Factory) this.f.get();
        prepareAdRunnable.f = (RetryMap) this.g.get();
        prepareAdRunnable.g = (LoggedException.Factory) this.h.get();
    }

    private bl(Provider<EventBus> provider, Provider<AdManager> provider2, Provider<AdReportManager> provider3, Provider<ScheduledPriorityExecutor> provider4, Provider<Factory> provider5, Provider<RetryMap> provider6, Provider<LoggedException.Factory> provider7) {
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

    public static MembersInjector<PrepareAdRunnable> a(Provider<EventBus> provider, Provider<AdManager> provider2, Provider<AdReportManager> provider3, Provider<ScheduledPriorityExecutor> provider4, Provider<Factory> provider5, Provider<RetryMap> provider6, Provider<LoggedException.Factory> provider7) {
        return new bl(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
