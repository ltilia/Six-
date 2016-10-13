package com.vungle.publisher;

import com.vungle.publisher.ad.prepare.PrepareAdRunnable;
import com.vungle.publisher.ad.prepare.PrepareAdRunnable.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bk implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<PrepareAdRunnable> b;

    static {
        a = !bk.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = this.b;
    }

    private bk(Provider<PrepareAdRunnable> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<PrepareAdRunnable> provider) {
        return new bk(provider);
    }
}
