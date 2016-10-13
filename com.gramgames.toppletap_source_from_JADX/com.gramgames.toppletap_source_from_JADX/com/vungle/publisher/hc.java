package com.vungle.publisher;

import com.vungle.publisher.net.http.TrackEventHttpRequest.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hc implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ek> b;

    static {
        a = !hc.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.b = (ek) this.b.get();
    }

    private hc(Provider<ek> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<ek> provider) {
        return new hc(provider);
    }
}
