package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class LocalArchive_Factory implements Factory<LocalArchive> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<LocalArchive> b;

    static {
        a = !LocalArchive_Factory.class.desiredAssertionStatus();
    }

    public LocalArchive_Factory(MembersInjector<LocalArchive> localArchiveMembersInjector) {
        if (a || localArchiveMembersInjector != null) {
            this.b = localArchiveMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final LocalArchive get() {
        return (LocalArchive) MembersInjectors.injectMembers(this.b, new LocalArchive());
    }

    public static Factory<LocalArchive> create(MembersInjector<LocalArchive> localArchiveMembersInjector) {
        return new LocalArchive_Factory(localArchiveMembersInjector);
    }
}
