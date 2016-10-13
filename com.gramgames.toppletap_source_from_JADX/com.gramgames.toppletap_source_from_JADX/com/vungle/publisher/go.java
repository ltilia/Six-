package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpResponse;
import com.vungle.publisher.net.http.HttpResponse.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class go implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpResponse> b;

    static {
        a = !go.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = this.b;
    }

    private go(Provider<HttpResponse> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<HttpResponse> provider) {
        return new go(provider);
    }
}
