package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpRequestChainElement;
import com.vungle.publisher.net.http.HttpRequestChainElement.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gn implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpRequestChainElement> b;

    static {
        a = !gn.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = this.b;
    }

    private gn(Provider<HttpRequestChainElement> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<HttpRequestChainElement> provider) {
        return new gn(provider);
    }
}
