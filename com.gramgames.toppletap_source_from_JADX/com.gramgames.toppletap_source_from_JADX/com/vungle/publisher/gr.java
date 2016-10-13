package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gr implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;

    static {
        a = !gr.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.c = this.b;
    }

    public static void a(Factory factory, Provider<HttpTransaction> provider) {
        factory.c = provider;
    }
}
