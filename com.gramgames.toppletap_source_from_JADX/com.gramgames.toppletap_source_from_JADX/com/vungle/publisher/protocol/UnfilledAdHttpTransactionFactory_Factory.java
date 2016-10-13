package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class UnfilledAdHttpTransactionFactory_Factory implements Factory<UnfilledAdHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<UnfilledAdHttpTransactionFactory> b;

    static {
        a = !UnfilledAdHttpTransactionFactory_Factory.class.desiredAssertionStatus();
    }

    public UnfilledAdHttpTransactionFactory_Factory(MembersInjector<UnfilledAdHttpTransactionFactory> unfilledAdHttpTransactionFactoryMembersInjector) {
        if (a || unfilledAdHttpTransactionFactoryMembersInjector != null) {
            this.b = unfilledAdHttpTransactionFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final UnfilledAdHttpTransactionFactory get() {
        return (UnfilledAdHttpTransactionFactory) MembersInjectors.injectMembers(this.b, new UnfilledAdHttpTransactionFactory());
    }

    public static Factory<UnfilledAdHttpTransactionFactory> create(MembersInjector<UnfilledAdHttpTransactionFactory> unfilledAdHttpTransactionFactoryMembersInjector) {
        return new UnfilledAdHttpTransactionFactory_Factory(unfilledAdHttpTransactionFactoryMembersInjector);
    }
}
