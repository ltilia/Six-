package com.vungle.publisher;

import com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.SessionEndHttpRequest.Factory;
import com.vungle.publisher.protocol.SessionEndHttpTransactionFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ib implements MembersInjector<SessionEndHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;
    private final Provider<Factory> c;
    private final Provider<FireAndForgetHttpResponseHandler> d;

    static {
        a = !ib.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        SessionEndHttpTransactionFactory sessionEndHttpTransactionFactory = (SessionEndHttpTransactionFactory) obj;
        if (sessionEndHttpTransactionFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gr.a(sessionEndHttpTransactionFactory, this.b);
        sessionEndHttpTransactionFactory.a = (Factory) this.c.get();
        sessionEndHttpTransactionFactory.b = (FireAndForgetHttpResponseHandler) this.d.get();
    }

    private ib(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<FireAndForgetHttpResponseHandler> provider3) {
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

    public static MembersInjector<SessionEndHttpTransactionFactory> a(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<FireAndForgetHttpResponseHandler> provider3) {
        return new ib(provider, provider2, provider3);
    }
}
