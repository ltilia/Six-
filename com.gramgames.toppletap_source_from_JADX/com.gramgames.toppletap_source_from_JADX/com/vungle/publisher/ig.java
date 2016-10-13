package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.TrackInstallHttpRequest.Factory;
import com.vungle.publisher.protocol.TrackInstallHttpResponseHandler;
import com.vungle.publisher.protocol.TrackInstallHttpTransactionFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ig implements MembersInjector<TrackInstallHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;
    private final Provider<Factory> c;
    private final Provider<TrackInstallHttpResponseHandler> d;

    static {
        a = !ig.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        TrackInstallHttpTransactionFactory trackInstallHttpTransactionFactory = (TrackInstallHttpTransactionFactory) obj;
        if (trackInstallHttpTransactionFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gr.a(trackInstallHttpTransactionFactory, this.b);
        trackInstallHttpTransactionFactory.a = (Factory) this.c.get();
        trackInstallHttpTransactionFactory.b = (TrackInstallHttpResponseHandler) this.d.get();
    }

    private ig(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<TrackInstallHttpResponseHandler> provider3) {
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

    public static MembersInjector<TrackInstallHttpTransactionFactory> a(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<TrackInstallHttpResponseHandler> provider3) {
        return new ig(provider, provider2, provider3);
    }
}
