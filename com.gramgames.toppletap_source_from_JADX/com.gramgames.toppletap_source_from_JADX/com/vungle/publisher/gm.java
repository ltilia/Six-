package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gm<T extends HttpRequest> implements MembersInjector<Factory<T>> {
    static final /* synthetic */ boolean a;
    private final Provider<ek> b;

    static {
        a = !gm.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.b = (ek) this.b.get();
    }

    public static <T extends HttpRequest> void a(Factory<T> factory, Provider<ek> provider) {
        factory.b = (ek) provider.get();
    }
}
