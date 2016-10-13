package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class LocalAdPlay_Factory implements Factory<LocalAdPlay> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<LocalAdPlay> b;

    static {
        a = !LocalAdPlay_Factory.class.desiredAssertionStatus();
    }

    public LocalAdPlay_Factory(MembersInjector<LocalAdPlay> localAdPlayMembersInjector) {
        if (a || localAdPlayMembersInjector != null) {
            this.b = localAdPlayMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final LocalAdPlay get() {
        return (LocalAdPlay) MembersInjectors.injectMembers(this.b, new LocalAdPlay());
    }

    public static Factory<LocalAdPlay> create(MembersInjector<LocalAdPlay> localAdPlayMembersInjector) {
        return new LocalAdPlay_Factory(localAdPlayMembersInjector);
    }
}
