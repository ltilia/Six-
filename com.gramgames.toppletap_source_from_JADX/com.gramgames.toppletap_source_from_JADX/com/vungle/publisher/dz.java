package com.vungle.publisher;

import com.vungle.publisher.display.view.PrivacyButton.Factory;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.util.ViewUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dz implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ViewUtils> b;
    private final Provider<EventBus> c;

    static {
        a = !dz.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (ViewUtils) this.b.get();
        factory.b = (EventBus) this.c.get();
    }

    private dz(Provider<ViewUtils> provider, Provider<EventBus> provider2) {
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

    public static MembersInjector<Factory> a(Provider<ViewUtils> provider, Provider<EventBus> provider2) {
        return new dz(provider, provider2);
    }
}
