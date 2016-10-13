package com.vungle.publisher;

import com.vungle.publisher.protocol.message.RequestAd.Demographic.Location.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class iq implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ft> b;

    static {
        a = !iq.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (ft) this.b.get();
    }

    private iq(Provider<ft> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<ft> provider) {
        return new iq(provider);
    }
}
