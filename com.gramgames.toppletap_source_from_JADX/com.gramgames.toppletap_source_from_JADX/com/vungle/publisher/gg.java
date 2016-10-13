package com.vungle.publisher;

import com.vungle.publisher.net.http.DownloadHttpRequest.Factory;
import com.vungle.publisher.net.http.DownloadHttpResponseHandler;
import com.vungle.publisher.net.http.DownloadHttpTransactionFactory;
import com.vungle.publisher.net.http.HttpTransaction;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gg implements MembersInjector<DownloadHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;
    private final Provider<Factory> c;
    private final Provider<DownloadHttpResponseHandler.Factory> d;

    static {
        a = !gg.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        DownloadHttpTransactionFactory downloadHttpTransactionFactory = (DownloadHttpTransactionFactory) obj;
        if (downloadHttpTransactionFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        downloadHttpTransactionFactory.c = this.b;
        downloadHttpTransactionFactory.a = (Factory) this.c.get();
        downloadHttpTransactionFactory.b = (DownloadHttpResponseHandler.Factory) this.d.get();
    }

    private gg(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<DownloadHttpResponseHandler.Factory> provider3) {
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

    public static MembersInjector<DownloadHttpTransactionFactory> a(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<DownloadHttpResponseHandler.Factory> provider3) {
        return new gg(provider, provider2, provider3);
    }
}
