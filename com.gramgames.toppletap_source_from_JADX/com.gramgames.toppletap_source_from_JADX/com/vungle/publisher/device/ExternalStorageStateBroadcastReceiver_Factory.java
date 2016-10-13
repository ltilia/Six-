package com.vungle.publisher.device;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ExternalStorageStateBroadcastReceiver_Factory implements Factory<ExternalStorageStateBroadcastReceiver> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ExternalStorageStateBroadcastReceiver> b;

    static {
        a = !ExternalStorageStateBroadcastReceiver_Factory.class.desiredAssertionStatus();
    }

    public ExternalStorageStateBroadcastReceiver_Factory(MembersInjector<ExternalStorageStateBroadcastReceiver> externalStorageStateBroadcastReceiverMembersInjector) {
        if (a || externalStorageStateBroadcastReceiverMembersInjector != null) {
            this.b = externalStorageStateBroadcastReceiverMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ExternalStorageStateBroadcastReceiver get() {
        return (ExternalStorageStateBroadcastReceiver) MembersInjectors.injectMembers(this.b, new ExternalStorageStateBroadcastReceiver());
    }

    public static Factory<ExternalStorageStateBroadcastReceiver> create(MembersInjector<ExternalStorageStateBroadcastReceiver> externalStorageStateBroadcastReceiverMembersInjector) {
        return new ExternalStorageStateBroadcastReceiver_Factory(externalStorageStateBroadcastReceiverMembersInjector);
    }
}
