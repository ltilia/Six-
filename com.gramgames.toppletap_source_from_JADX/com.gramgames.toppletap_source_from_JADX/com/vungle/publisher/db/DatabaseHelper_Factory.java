package com.vungle.publisher.db;

import android.content.Context;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.inject.Provider;

/* compiled from: vungle */
public final class DatabaseHelper_Factory implements Factory<DatabaseHelper> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<DatabaseHelper> b;
    private final Provider<Context> c;

    static {
        a = !DatabaseHelper_Factory.class.desiredAssertionStatus();
    }

    public DatabaseHelper_Factory(MembersInjector<DatabaseHelper> databaseHelperMembersInjector, Provider<Context> contextProvider) {
        if (a || databaseHelperMembersInjector != null) {
            this.b = databaseHelperMembersInjector;
            if (a || contextProvider != null) {
                this.c = contextProvider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public final DatabaseHelper get() {
        return (DatabaseHelper) MembersInjectors.injectMembers(this.b, new DatabaseHelper((Context) this.c.get()));
    }

    public static Factory<DatabaseHelper> create(MembersInjector<DatabaseHelper> databaseHelperMembersInjector, Provider<Context> contextProvider) {
        return new DatabaseHelper_Factory(databaseHelperMembersInjector, contextProvider);
    }
}
