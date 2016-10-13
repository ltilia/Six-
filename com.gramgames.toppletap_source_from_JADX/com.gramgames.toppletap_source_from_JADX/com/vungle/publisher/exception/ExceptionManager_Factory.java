package com.vungle.publisher.exception;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ExceptionManager_Factory implements Factory<ExceptionManager> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ExceptionManager> b;

    static {
        a = !ExceptionManager_Factory.class.desiredAssertionStatus();
    }

    public ExceptionManager_Factory(MembersInjector<ExceptionManager> exceptionManagerMembersInjector) {
        if (a || exceptionManagerMembersInjector != null) {
            this.b = exceptionManagerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ExceptionManager get() {
        return (ExceptionManager) MembersInjectors.injectMembers(this.b, new ExceptionManager());
    }

    public static Factory<ExceptionManager> create(MembersInjector<ExceptionManager> exceptionManagerMembersInjector) {
        return new ExceptionManager_Factory(exceptionManagerMembersInjector);
    }
}
