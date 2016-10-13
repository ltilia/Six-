package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gj implements MembersInjector<FireAndForgetHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<Factory> c;

    static {
        a = !gj.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        FireAndForgetHttpResponseHandler fireAndForgetHttpResponseHandler = (FireAndForgetHttpResponseHandler) obj;
        if (fireAndForgetHttpResponseHandler == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        fireAndForgetHttpResponseHandler.f = (ScheduledPriorityExecutor) this.b.get();
        fireAndForgetHttpResponseHandler.g = (Factory) this.c.get();
    }

    private gj(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<FireAndForgetHttpResponseHandler> a(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2) {
        return new gj(provider, provider2);
    }
}
