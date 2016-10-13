package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.TrackEventHttpRequest.Factory;
import com.vungle.publisher.net.http.TrackEventHttpResponseHandler;
import com.vungle.publisher.net.http.TrackEventHttpTransactionFactory;
import dagger.MembersInjector;
import dagger.internal.DoubleCheckLazy;
import javax.inject.Provider;

/* compiled from: vungle */
public final class he implements MembersInjector<TrackEventHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;
    private final Provider<Factory> c;
    private final Provider<TrackEventHttpResponseHandler> d;

    static {
        a = !he.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        TrackEventHttpTransactionFactory trackEventHttpTransactionFactory = (TrackEventHttpTransactionFactory) obj;
        if (trackEventHttpTransactionFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        trackEventHttpTransactionFactory.c = this.b;
        trackEventHttpTransactionFactory.a = (Factory) this.c.get();
        trackEventHttpTransactionFactory.b = DoubleCheckLazy.create(this.d);
    }

    private he(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<TrackEventHttpResponseHandler> provider3) {
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

    public static MembersInjector<TrackEventHttpTransactionFactory> a(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<TrackEventHttpResponseHandler> provider3) {
        return new he(provider, provider2, provider3);
    }
}
