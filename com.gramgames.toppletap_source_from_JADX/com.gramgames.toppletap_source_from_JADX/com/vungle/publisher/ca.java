package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.ArchiveEntry;
import com.vungle.publisher.db.model.ArchiveEntry.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ca implements MembersInjector<ArchiveEntry> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;

    static {
        a = !ca.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ArchiveEntry archiveEntry = (ArchiveEntry) obj;
        if (archiveEntry == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        archiveEntry.t = (DatabaseHelper) this.b.get();
        archiveEntry.d = (Factory) this.c.get();
    }

    private ca(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ArchiveEntry> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
        return new ca(provider, provider2);
    }
}
