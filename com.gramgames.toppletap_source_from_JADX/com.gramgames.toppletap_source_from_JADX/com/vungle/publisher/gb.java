package com.vungle.publisher;

import com.vungle.publisher.net.http.AppFingerprintHttpRequest.Factory;
import com.vungle.publisher.net.http.AppFingerprintHttpResponseHandler;
import com.vungle.publisher.net.http.AppFingerprintHttpTransactionFactory;
import com.vungle.publisher.net.http.HttpTransaction;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gb implements MembersInjector<AppFingerprintHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;
    private final Provider<Factory> c;
    private final Provider<AppFingerprintHttpResponseHandler> d;

    static {
        a = !gb.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AppFingerprintHttpTransactionFactory appFingerprintHttpTransactionFactory = (AppFingerprintHttpTransactionFactory) obj;
        if (appFingerprintHttpTransactionFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        appFingerprintHttpTransactionFactory.c = this.b;
        appFingerprintHttpTransactionFactory.a = (Factory) this.c.get();
        appFingerprintHttpTransactionFactory.b = (AppFingerprintHttpResponseHandler) this.d.get();
    }

    private gb(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<AppFingerprintHttpResponseHandler> provider3) {
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

    public static MembersInjector<AppFingerprintHttpTransactionFactory> a(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<AppFingerprintHttpResponseHandler> provider3) {
        return new gb(provider, provider2, provider3);
    }
}
