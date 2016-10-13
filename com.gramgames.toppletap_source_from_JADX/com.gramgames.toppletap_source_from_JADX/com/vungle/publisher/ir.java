package com.vungle.publisher;

import com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ir implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ek> b;

    static {
        a = !ir.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (ek) this.b.get();
    }

    private ir(Provider<ek> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<ek> provider) {
        return new ir(provider);
    }
}
