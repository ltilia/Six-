package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.RequestConfigHttpRequest.Factory;
import com.vungle.publisher.protocol.RequestConfigHttpTransactionFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ht implements MembersInjector<RequestConfigHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;
    private final Provider<Factory> c;
    private final Provider<hr> d;

    static {
        a = !ht.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        RequestConfigHttpTransactionFactory requestConfigHttpTransactionFactory = (RequestConfigHttpTransactionFactory) obj;
        if (requestConfigHttpTransactionFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gr.a(requestConfigHttpTransactionFactory, this.b);
        requestConfigHttpTransactionFactory.a = (Factory) this.c.get();
        requestConfigHttpTransactionFactory.b = (hr) this.d.get();
    }

    private ht(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<hr> provider3) {
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

    public static MembersInjector<RequestConfigHttpTransactionFactory> a(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<hr> provider3) {
        return new ht(provider, provider2, provider3);
    }
}
