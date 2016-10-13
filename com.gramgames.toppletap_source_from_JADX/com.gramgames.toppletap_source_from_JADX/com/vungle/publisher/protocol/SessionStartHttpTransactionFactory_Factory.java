package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class SessionStartHttpTransactionFactory_Factory implements Factory<SessionStartHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<SessionStartHttpTransactionFactory> b;

    static {
        a = !SessionStartHttpTransactionFactory_Factory.class.desiredAssertionStatus();
    }

    public SessionStartHttpTransactionFactory_Factory(MembersInjector<SessionStartHttpTransactionFactory> sessionStartHttpTransactionFactoryMembersInjector) {
        if (a || sessionStartHttpTransactionFactoryMembersInjector != null) {
            this.b = sessionStartHttpTransactionFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final SessionStartHttpTransactionFactory get() {
        return (SessionStartHttpTransactionFactory) MembersInjectors.injectMembers(this.b, new SessionStartHttpTransactionFactory());
    }

    public static Factory<SessionStartHttpTransactionFactory> create(MembersInjector<SessionStartHttpTransactionFactory> sessionStartHttpTransactionFactoryMembersInjector) {
        return new SessionStartHttpTransactionFactory_Factory(sessionStartHttpTransactionFactoryMembersInjector);
    }
}
