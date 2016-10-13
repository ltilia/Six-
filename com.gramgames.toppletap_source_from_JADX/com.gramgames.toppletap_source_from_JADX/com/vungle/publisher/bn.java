package com.vungle.publisher;

import com.vungle.publisher.ad.prepare.PrepareViewableRunnable;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bn implements MembersInjector<PrepareViewableRunnable> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<Factory> c;

    static {
        a = !bn.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        PrepareViewableRunnable prepareViewableRunnable = (PrepareViewableRunnable) obj;
        if (prepareViewableRunnable == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        prepareViewableRunnable.d = (EventBus) this.b.get();
        prepareViewableRunnable.e = (Factory) this.c.get();
    }

    private bn(Provider<EventBus> provider, Provider<Factory> provider2) {
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

    public static MembersInjector<PrepareViewableRunnable> a(Provider<EventBus> provider, Provider<Factory> provider2) {
        return new bn(provider, provider2);
    }
}
