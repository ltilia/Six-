package com.vungle.publisher;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.event.ClientEventListenerAdapter;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ew implements MembersInjector<ClientEventListenerAdapter> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<ScheduledPriorityExecutor> c;
    private final Provider<AdManager> d;

    static {
        a = !ew.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ClientEventListenerAdapter clientEventListenerAdapter = (ClientEventListenerAdapter) obj;
        if (clientEventListenerAdapter == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        clientEventListenerAdapter.h = (EventBus) this.b.get();
        clientEventListenerAdapter.b = (ScheduledPriorityExecutor) this.c.get();
        clientEventListenerAdapter.c = (AdManager) this.d.get();
    }

    private ew(Provider<EventBus> provider, Provider<ScheduledPriorityExecutor> provider2, Provider<AdManager> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ClientEventListenerAdapter> a(Provider<EventBus> provider, Provider<ScheduledPriorityExecutor> provider2, Provider<AdManager> provider3) {
        return new ew(provider, provider2, provider3);
    }
}
