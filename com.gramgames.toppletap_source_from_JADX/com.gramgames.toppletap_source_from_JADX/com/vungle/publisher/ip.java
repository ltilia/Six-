package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory;
import com.vungle.publisher.protocol.message.RequestAd.Demographic.Location;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ip implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<Demographic> c;
    private final Provider<Location.Factory> d;

    static {
        a = !ip.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (Context) this.b.get();
        factory.b = (Demographic) this.c.get();
        factory.c = (Location.Factory) this.d.get();
    }

    private ip(Provider<Context> provider, Provider<Demographic> provider2, Provider<Location.Factory> provider3) {
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

    public static MembersInjector<Factory> a(Provider<Context> provider, Provider<Demographic> provider2, Provider<Location.Factory> provider3) {
        return new ip(provider, provider2, provider3);
    }
}
