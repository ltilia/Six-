package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class LoggedException_Factory implements Factory<LoggedException> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<LoggedException> b;

    static {
        a = !LoggedException_Factory.class.desiredAssertionStatus();
    }

    public LoggedException_Factory(MembersInjector<LoggedException> loggedExceptionMembersInjector) {
        if (a || loggedExceptionMembersInjector != null) {
            this.b = loggedExceptionMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final LoggedException get() {
        return (LoggedException) MembersInjectors.injectMembers(this.b, new LoggedException());
    }

    public static Factory<LoggedException> create(MembersInjector<LoggedException> loggedExceptionMembersInjector) {
        return new LoggedException_Factory(loggedExceptionMembersInjector);
    }
}
