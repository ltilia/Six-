package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.RequestLocalAdHttpRequest.Factory;
import com.vungle.publisher.protocol.RequestLocalAdHttpResponseHandler;
import com.vungle.publisher.protocol.RequestLocalAdHttpTransactionFactory;
import dagger.MembersInjector;
import dagger.internal.DoubleCheckLazy;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hw implements MembersInjector<RequestLocalAdHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;
    private final Provider<Factory> c;
    private final Provider<RequestLocalAdHttpResponseHandler> d;

    static {
        a = !hw.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        RequestLocalAdHttpTransactionFactory requestLocalAdHttpTransactionFactory = (RequestLocalAdHttpTransactionFactory) obj;
        if (requestLocalAdHttpTransactionFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gr.a(requestLocalAdHttpTransactionFactory, this.b);
        requestLocalAdHttpTransactionFactory.a = (Factory) this.c.get();
        requestLocalAdHttpTransactionFactory.b = DoubleCheckLazy.create(this.d);
    }

    private hw(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<RequestLocalAdHttpResponseHandler> provider3) {
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

    public static MembersInjector<RequestLocalAdHttpTransactionFactory> a(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<RequestLocalAdHttpResponseHandler> provider3) {
        return new hw(provider, provider2, provider3);
    }
}
