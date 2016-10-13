package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class HttpTransaction_Factory implements Factory<HttpTransaction> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<HttpTransaction> b;

    static {
        a = !HttpTransaction_Factory.class.desiredAssertionStatus();
    }

    public HttpTransaction_Factory(MembersInjector<HttpTransaction> httpTransactionMembersInjector) {
        if (a || httpTransactionMembersInjector != null) {
            this.b = httpTransactionMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final HttpTransaction get() {
        return (HttpTransaction) MembersInjectors.injectMembers(this.b, new HttpTransaction());
    }

    public static Factory<HttpTransaction> create(MembersInjector<HttpTransaction> httpTransactionMembersInjector) {
        return new HttpTransaction_Factory(httpTransactionMembersInjector);
    }
}
