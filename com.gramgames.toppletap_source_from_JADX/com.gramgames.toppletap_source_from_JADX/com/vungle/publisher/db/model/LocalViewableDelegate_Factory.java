package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class LocalViewableDelegate_Factory implements Factory<LocalViewableDelegate> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<LocalViewableDelegate> b;

    static {
        a = !LocalViewableDelegate_Factory.class.desiredAssertionStatus();
    }

    public LocalViewableDelegate_Factory(MembersInjector<LocalViewableDelegate> localViewableDelegateMembersInjector) {
        if (a || localViewableDelegateMembersInjector != null) {
            this.b = localViewableDelegateMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final LocalViewableDelegate get() {
        return (LocalViewableDelegate) MembersInjectors.injectMembers(this.b, new LocalViewableDelegate());
    }

    public static Factory<LocalViewableDelegate> create(MembersInjector<LocalViewableDelegate> localViewableDelegateMembersInjector) {
        return new LocalViewableDelegate_Factory(localViewableDelegateMembersInjector);
    }
}
