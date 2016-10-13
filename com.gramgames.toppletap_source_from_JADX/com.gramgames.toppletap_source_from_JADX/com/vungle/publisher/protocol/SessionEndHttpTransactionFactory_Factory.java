package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class SessionEndHttpTransactionFactory_Factory implements Factory<SessionEndHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<SessionEndHttpTransactionFactory> b;

    static {
        a = !SessionEndHttpTransactionFactory_Factory.class.desiredAssertionStatus();
    }

    public SessionEndHttpTransactionFactory_Factory(MembersInjector<SessionEndHttpTransactionFactory> sessionEndHttpTransactionFactoryMembersInjector) {
        if (a || sessionEndHttpTransactionFactoryMembersInjector != null) {
            this.b = sessionEndHttpTransactionFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final SessionEndHttpTransactionFactory get() {
        return (SessionEndHttpTransactionFactory) MembersInjectors.injectMembers(this.b, new SessionEndHttpTransactionFactory());
    }

    public static Factory<SessionEndHttpTransactionFactory> create(MembersInjector<SessionEndHttpTransactionFactory> sessionEndHttpTransactionFactoryMembersInjector) {
        return new SessionEndHttpTransactionFactory_Factory(sessionEndHttpTransactionFactoryMembersInjector);
    }
}
