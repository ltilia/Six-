package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestStreamingAdHttpTransactionFactory_Factory implements Factory<RequestStreamingAdHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<RequestStreamingAdHttpTransactionFactory> b;

    static {
        a = !RequestStreamingAdHttpTransactionFactory_Factory.class.desiredAssertionStatus();
    }

    public RequestStreamingAdHttpTransactionFactory_Factory(MembersInjector<RequestStreamingAdHttpTransactionFactory> requestStreamingAdHttpTransactionFactoryMembersInjector) {
        if (a || requestStreamingAdHttpTransactionFactoryMembersInjector != null) {
            this.b = requestStreamingAdHttpTransactionFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final RequestStreamingAdHttpTransactionFactory get() {
        return (RequestStreamingAdHttpTransactionFactory) MembersInjectors.injectMembers(this.b, new RequestStreamingAdHttpTransactionFactory());
    }

    public static Factory<RequestStreamingAdHttpTransactionFactory> create(MembersInjector<RequestStreamingAdHttpTransactionFactory> requestStreamingAdHttpTransactionFactoryMembersInjector) {
        return new RequestStreamingAdHttpTransactionFactory_Factory(requestStreamingAdHttpTransactionFactoryMembersInjector);
    }
}
