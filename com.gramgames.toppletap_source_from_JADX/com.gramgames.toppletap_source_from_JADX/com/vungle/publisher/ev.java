package com.vungle.publisher;

import com.vungle.publisher.event.ClientEventListenerAdapter;
import com.vungle.publisher.event.ClientEventListenerAdapter.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ev implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ClientEventListenerAdapter> b;

    static {
        a = !ev.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = this.b;
    }

    private ev(Provider<ClientEventListenerAdapter> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<ClientEventListenerAdapter> provider) {
        return new ev(provider);
    }
}
