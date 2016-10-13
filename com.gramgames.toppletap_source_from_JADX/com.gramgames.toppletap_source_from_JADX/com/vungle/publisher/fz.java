package com.vungle.publisher;

import com.vungle.publisher.net.http.AppFingerprintHttpRequest;
import com.vungle.publisher.net.http.AppFingerprintHttpRequest.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fz implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ek> b;
    private final Provider<String> c;
    private final Provider<AppFingerprintHttpRequest> d;

    static {
        a = !fz.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.b = (ek) this.b.get();
        factory.c = (String) this.c.get();
        factory.a = this.d;
    }

    private fz(Provider<ek> provider, Provider<String> provider2, Provider<AppFingerprintHttpRequest> provider3) {
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

    public static MembersInjector<Factory> a(Provider<ek> provider, Provider<String> provider2, Provider<AppFingerprintHttpRequest> provider3) {
        return new fz(provider, provider2, provider3);
    }
}
