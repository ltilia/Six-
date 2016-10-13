package com.vungle.publisher;

import com.vungle.publisher.protocol.message.SessionEnd.Factory;
import com.vungle.publisher.protocol.message.SessionStart;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class iz implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<SessionStart.Factory> b;

    static {
        a = !iz.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (SessionStart.Factory) this.b.get();
    }

    private iz(Provider<SessionStart.Factory> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<SessionStart.Factory> provider) {
        return new iz(provider);
    }
}
