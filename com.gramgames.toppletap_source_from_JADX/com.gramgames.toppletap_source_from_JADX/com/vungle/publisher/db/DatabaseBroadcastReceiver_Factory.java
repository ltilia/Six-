package com.vungle.publisher.db;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class DatabaseBroadcastReceiver_Factory implements Factory<DatabaseBroadcastReceiver> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<DatabaseBroadcastReceiver> b;

    static {
        a = !DatabaseBroadcastReceiver_Factory.class.desiredAssertionStatus();
    }

    public DatabaseBroadcastReceiver_Factory(MembersInjector<DatabaseBroadcastReceiver> databaseBroadcastReceiverMembersInjector) {
        if (a || databaseBroadcastReceiverMembersInjector != null) {
            this.b = databaseBroadcastReceiverMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final DatabaseBroadcastReceiver get() {
        return (DatabaseBroadcastReceiver) MembersInjectors.injectMembers(this.b, new DatabaseBroadcastReceiver());
    }

    public static Factory<DatabaseBroadcastReceiver> create(MembersInjector<DatabaseBroadcastReceiver> databaseBroadcastReceiverMembersInjector) {
        return new DatabaseBroadcastReceiver_Factory(databaseBroadcastReceiverMembersInjector);
    }
}
