package com.vungle.publisher;

import com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.Factory;
import com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class it implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<PlayCheckpoint.Factory> b;

    static {
        a = !it.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (PlayCheckpoint.Factory) this.b.get();
    }

    private it(Provider<PlayCheckpoint.Factory> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<PlayCheckpoint.Factory> provider) {
        return new it(provider);
    }
}
