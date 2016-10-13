package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ArchiveEntry_Factory implements Factory<ArchiveEntry> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ArchiveEntry> b;

    static {
        a = !ArchiveEntry_Factory.class.desiredAssertionStatus();
    }

    public ArchiveEntry_Factory(MembersInjector<ArchiveEntry> archiveEntryMembersInjector) {
        if (a || archiveEntryMembersInjector != null) {
            this.b = archiveEntryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ArchiveEntry get() {
        return (ArchiveEntry) MembersInjectors.injectMembers(this.b, new ArchiveEntry());
    }

    public static Factory<ArchiveEntry> create(MembersInjector<ArchiveEntry> archiveEntryMembersInjector) {
        return new ArchiveEntry_Factory(archiveEntryMembersInjector);
    }
}
