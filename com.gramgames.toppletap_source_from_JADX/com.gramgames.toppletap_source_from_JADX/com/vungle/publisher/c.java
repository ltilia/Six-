package com.vungle.publisher;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.ad.AdManager.PlayAdEventListener;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class c implements MembersInjector<PlayAdEventListener> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<AdManager> c;
    private final Provider<Factory> d;

    static {
        a = !c.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        PlayAdEventListener playAdEventListener = (PlayAdEventListener) obj;
        if (playAdEventListener == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eu.a(playAdEventListener, this.b);
        playAdEventListener.b = (AdManager) this.c.get();
        playAdEventListener.c = (Factory) this.d.get();
    }

    private c(Provider<EventBus> provider, Provider<AdManager> provider2, Provider<Factory> provider3) {
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

    public static MembersInjector<PlayAdEventListener> a(Provider<EventBus> provider, Provider<AdManager> provider2, Provider<Factory> provider3) {
        return new c(provider, provider2, provider3);
    }
}
