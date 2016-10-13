package com.vungle.publisher;

import com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.UnfilledAdHttpRequest.Factory;
import com.vungle.publisher.protocol.UnfilledAdHttpTransactionFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ii implements MembersInjector<UnfilledAdHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;
    private final Provider<Factory> c;
    private final Provider<FireAndForgetHttpResponseHandler> d;

    static {
        a = !ii.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        UnfilledAdHttpTransactionFactory unfilledAdHttpTransactionFactory = (UnfilledAdHttpTransactionFactory) obj;
        if (unfilledAdHttpTransactionFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gr.a(unfilledAdHttpTransactionFactory, this.b);
        unfilledAdHttpTransactionFactory.a = (Factory) this.c.get();
        unfilledAdHttpTransactionFactory.b = (FireAndForgetHttpResponseHandler) this.d.get();
    }

    private ii(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<FireAndForgetHttpResponseHandler> provider3) {
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

    public static MembersInjector<UnfilledAdHttpTransactionFactory> a(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<FireAndForgetHttpResponseHandler> provider3) {
        return new ii(provider, provider2, provider3);
    }
}
