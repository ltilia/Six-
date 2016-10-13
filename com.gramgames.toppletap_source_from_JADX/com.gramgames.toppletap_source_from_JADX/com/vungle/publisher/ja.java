package com.vungle.publisher;

import com.vungle.publisher.protocol.message.SessionStart.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ja implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ek> b;
    private final Provider<em> c;

    static {
        a = !ja.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (ek) this.b.get();
        factory.b = (em) this.c.get();
    }

    private ja(Provider<ek> provider, Provider<em> provider2) {
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

    public static MembersInjector<Factory> a(Provider<ek> provider, Provider<em> provider2) {
        return new ja(provider, provider2);
    }
}
