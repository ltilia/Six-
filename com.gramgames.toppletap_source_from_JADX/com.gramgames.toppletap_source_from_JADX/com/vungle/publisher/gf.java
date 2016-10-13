package com.vungle.publisher;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.net.http.DownloadHttpResponseHandler;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gf implements MembersInjector<DownloadHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<Factory> c;
    private final Provider<EventBus> d;
    private final Provider<AdManager> e;

    static {
        a = !gf.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        DownloadHttpResponseHandler downloadHttpResponseHandler = (DownloadHttpResponseHandler) obj;
        if (downloadHttpResponseHandler == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        downloadHttpResponseHandler.f = (ScheduledPriorityExecutor) this.b.get();
        downloadHttpResponseHandler.g = (Factory) this.c.get();
        downloadHttpResponseHandler.d = (EventBus) this.d.get();
        downloadHttpResponseHandler.e = (AdManager) this.e.get();
    }

    private gf(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<EventBus> provider3, Provider<AdManager> provider4) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
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

    public static MembersInjector<DownloadHttpResponseHandler> a(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<EventBus> provider3, Provider<AdManager> provider4) {
        return new gf(provider, provider2, provider3, provider4);
    }
}
