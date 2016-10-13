package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransport;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gs implements MembersInjector<HttpTransaction> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransport> b;

    static {
        a = !gs.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        HttpTransaction httpTransaction = (HttpTransaction) obj;
        if (httpTransaction == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        httpTransaction.d = (HttpTransport) this.b.get();
    }

    private gs(Provider<HttpTransport> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<HttpTransaction> a(Provider<HttpTransport> provider) {
        return new gs(provider);
    }
}
