package com.vungle.publisher;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.ad.AdManager.AdAvailabilityEventListener;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class b implements MembersInjector<AdAvailabilityEventListener> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<AdManager> c;

    static {
        a = !b.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AdAvailabilityEventListener adAvailabilityEventListener = (AdAvailabilityEventListener) obj;
        if (adAvailabilityEventListener == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eu.a(adAvailabilityEventListener, this.b);
        adAvailabilityEventListener.a = (AdManager) this.c.get();
    }

    private b(Provider<EventBus> provider, Provider<AdManager> provider2) {
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

    public static MembersInjector<AdAvailabilityEventListener> a(Provider<EventBus> provider, Provider<AdManager> provider2) {
        return new b(provider, provider2);
    }
}
