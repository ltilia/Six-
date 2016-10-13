package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestConfigHttpTransactionFactory_Factory implements Factory<RequestConfigHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<RequestConfigHttpTransactionFactory> b;

    static {
        a = !RequestConfigHttpTransactionFactory_Factory.class.desiredAssertionStatus();
    }

    public RequestConfigHttpTransactionFactory_Factory(MembersInjector<RequestConfigHttpTransactionFactory> requestConfigHttpTransactionFactoryMembersInjector) {
        if (a || requestConfigHttpTransactionFactoryMembersInjector != null) {
            this.b = requestConfigHttpTransactionFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final RequestConfigHttpTransactionFactory get() {
        return (RequestConfigHttpTransactionFactory) MembersInjectors.injectMembers(this.b, new RequestConfigHttpTransactionFactory());
    }

    public static Factory<RequestConfigHttpTransactionFactory> create(MembersInjector<RequestConfigHttpTransactionFactory> requestConfigHttpTransactionFactoryMembersInjector) {
        return new RequestConfigHttpTransactionFactory_Factory(requestConfigHttpTransactionFactoryMembersInjector);
    }
}
