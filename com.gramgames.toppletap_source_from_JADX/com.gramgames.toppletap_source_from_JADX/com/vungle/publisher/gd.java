package com.vungle.publisher;

import com.vungle.publisher.net.http.DownloadHttpRequest.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gd implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ek> b;

    static {
        a = !gd.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.b = (ek) this.b.get();
    }

    private gd(Provider<ek> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<ek> provider) {
        return new gd(provider);
    }
}
