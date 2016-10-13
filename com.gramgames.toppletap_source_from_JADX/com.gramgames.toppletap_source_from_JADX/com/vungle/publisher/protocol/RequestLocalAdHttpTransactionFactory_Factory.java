package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestLocalAdHttpTransactionFactory_Factory implements Factory<RequestLocalAdHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<RequestLocalAdHttpTransactionFactory> b;

    static {
        a = !RequestLocalAdHttpTransactionFactory_Factory.class.desiredAssertionStatus();
    }

    public RequestLocalAdHttpTransactionFactory_Factory(MembersInjector<RequestLocalAdHttpTransactionFactory> requestLocalAdHttpTransactionFactoryMembersInjector) {
        if (a || requestLocalAdHttpTransactionFactoryMembersInjector != null) {
            this.b = requestLocalAdHttpTransactionFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final RequestLocalAdHttpTransactionFactory get() {
        return (RequestLocalAdHttpTransactionFactory) MembersInjectors.injectMembers(this.b, new RequestLocalAdHttpTransactionFactory());
    }

    public static Factory<RequestLocalAdHttpTransactionFactory> create(MembersInjector<RequestLocalAdHttpTransactionFactory> requestLocalAdHttpTransactionFactoryMembersInjector) {
        return new RequestLocalAdHttpTransactionFactory_Factory(requestLocalAdHttpTransactionFactoryMembersInjector);
    }
}
