package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.protocol.RequestConfigAsync;
import com.vungle.publisher.protocol.RequestConfigAsync.RequestConfigRunnable;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hp implements MembersInjector<RequestConfigAsync> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<RequestConfigRunnable> c;

    static {
        a = !hp.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        RequestConfigAsync requestConfigAsync = (RequestConfigAsync) obj;
        if (requestConfigAsync == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bp.a(requestConfigAsync, this.b);
        requestConfigAsync.b = (RequestConfigRunnable) this.c.get();
    }

    private hp(Provider<ScheduledPriorityExecutor> provider, Provider<RequestConfigRunnable> provider2) {
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

    public static MembersInjector<RequestConfigAsync> a(Provider<ScheduledPriorityExecutor> provider, Provider<RequestConfigRunnable> provider2) {
        return new hp(provider, provider2);
    }
}
