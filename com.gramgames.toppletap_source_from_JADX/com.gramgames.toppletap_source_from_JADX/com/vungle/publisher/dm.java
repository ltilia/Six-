package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.device.data.AppFingerprint;
import com.vungle.publisher.device.data.AppFingerprint.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dm implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ek> b;
    private final Provider<Context> c;
    private final Provider<AppFingerprint> d;

    static {
        a = !dm.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (ek) this.b.get();
        factory.b = (Context) this.c.get();
        factory.c = this.d;
    }

    private dm(Provider<ek> provider, Provider<Context> provider2, Provider<AppFingerprint> provider3) {
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

    public static MembersInjector<Factory> a(Provider<ek> provider, Provider<Context> provider2, Provider<AppFingerprint> provider3) {
        return new dm(provider, provider2, provider3);
    }
}
