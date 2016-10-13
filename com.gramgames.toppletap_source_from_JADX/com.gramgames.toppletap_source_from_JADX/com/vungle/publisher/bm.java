package com.vungle.publisher;

import com.vungle.publisher.ad.prepare.PrepareViewableRunnable;
import com.vungle.publisher.ad.prepare.PrepareViewableRunnable.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bm implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<PrepareViewableRunnable> b;

    static {
        a = !bm.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = this.b;
    }

    private bm(Provider<PrepareViewableRunnable> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<PrepareViewableRunnable> provider) {
        return new bm(provider);
    }
}
