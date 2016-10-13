package com.vungle.publisher;

import com.vungle.publisher.ad.AdManager.PrepareStreamingAdEventListener;
import com.vungle.publisher.db.model.StreamingAd.Factory;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class d implements MembersInjector<PrepareStreamingAdEventListener> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<Factory> c;

    static {
        a = !d.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        PrepareStreamingAdEventListener prepareStreamingAdEventListener = (PrepareStreamingAdEventListener) obj;
        if (prepareStreamingAdEventListener == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eu.a(prepareStreamingAdEventListener, this.b);
        prepareStreamingAdEventListener.e = (Factory) this.c.get();
    }

    private d(Provider<EventBus> provider, Provider<Factory> provider2) {
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

    public static MembersInjector<PrepareStreamingAdEventListener> a(Provider<EventBus> provider, Provider<Factory> provider2) {
        return new d(provider, provider2);
    }
}
