package com.vungle.publisher.protocol.message;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class SessionEnd_Factory implements Factory<SessionEnd> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<SessionEnd> b;

    static {
        a = !SessionEnd_Factory.class.desiredAssertionStatus();
    }

    public SessionEnd_Factory(MembersInjector<SessionEnd> sessionEndMembersInjector) {
        if (a || sessionEndMembersInjector != null) {
            this.b = sessionEndMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final SessionEnd get() {
        return (SessionEnd) MembersInjectors.injectMembers(this.b, new SessionEnd());
    }

    public static Factory<SessionEnd> create(MembersInjector<SessionEnd> sessionEndMembersInjector) {
        return new SessionEnd_Factory(sessionEndMembersInjector);
    }
}
