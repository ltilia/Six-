package com.vungle.publisher;

import com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.SessionStartHttpRequest.Factory;
import com.vungle.publisher.protocol.SessionStartHttpTransactionFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class id implements MembersInjector<SessionStartHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;
    private final Provider<Factory> c;
    private final Provider<FireAndForgetHttpResponseHandler> d;

    static {
        a = !id.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        SessionStartHttpTransactionFactory sessionStartHttpTransactionFactory = (SessionStartHttpTransactionFactory) obj;
        if (sessionStartHttpTransactionFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gr.a(sessionStartHttpTransactionFactory, this.b);
        sessionStartHttpTransactionFactory.a = (Factory) this.c.get();
        sessionStartHttpTransactionFactory.b = (FireAndForgetHttpResponseHandler) this.d.get();
    }

    private id(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<FireAndForgetHttpResponseHandler> provider3) {
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

    public static MembersInjector<SessionStartHttpTransactionFactory> a(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<FireAndForgetHttpResponseHandler> provider3) {
        return new id(provider, provider2, provider3);
    }
}
