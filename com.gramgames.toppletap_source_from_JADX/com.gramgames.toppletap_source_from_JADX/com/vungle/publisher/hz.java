package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.RequestStreamingAdHttpRequest.Factory;
import com.vungle.publisher.protocol.RequestStreamingAdHttpResponseHandler;
import com.vungle.publisher.protocol.RequestStreamingAdHttpTransactionFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hz implements MembersInjector<RequestStreamingAdHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;
    private final Provider<Factory> c;
    private final Provider<RequestStreamingAdHttpResponseHandler> d;

    static {
        a = !hz.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        RequestStreamingAdHttpTransactionFactory requestStreamingAdHttpTransactionFactory = (RequestStreamingAdHttpTransactionFactory) obj;
        if (requestStreamingAdHttpTransactionFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gr.a(requestStreamingAdHttpTransactionFactory, this.b);
        requestStreamingAdHttpTransactionFactory.a = (Factory) this.c.get();
        requestStreamingAdHttpTransactionFactory.b = (RequestStreamingAdHttpResponseHandler) this.d.get();
    }

    private hz(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<RequestStreamingAdHttpResponseHandler> provider3) {
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

    public static MembersInjector<RequestStreamingAdHttpTransactionFactory> a(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<RequestStreamingAdHttpResponseHandler> provider3) {
        return new hz(provider, provider2, provider3);
    }
}
