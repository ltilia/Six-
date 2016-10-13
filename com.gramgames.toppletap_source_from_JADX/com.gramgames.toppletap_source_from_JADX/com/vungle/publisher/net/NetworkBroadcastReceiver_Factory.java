package com.vungle.publisher.net;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class NetworkBroadcastReceiver_Factory implements Factory<NetworkBroadcastReceiver> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<NetworkBroadcastReceiver> b;

    static {
        a = !NetworkBroadcastReceiver_Factory.class.desiredAssertionStatus();
    }

    public NetworkBroadcastReceiver_Factory(MembersInjector<NetworkBroadcastReceiver> networkBroadcastReceiverMembersInjector) {
        if (a || networkBroadcastReceiverMembersInjector != null) {
            this.b = networkBroadcastReceiverMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final NetworkBroadcastReceiver get() {
        return (NetworkBroadcastReceiver) MembersInjectors.injectMembers(this.b, new NetworkBroadcastReceiver());
    }

    public static Factory<NetworkBroadcastReceiver> create(MembersInjector<NetworkBroadcastReceiver> networkBroadcastReceiverMembersInjector) {
        return new NetworkBroadcastReceiver_Factory(networkBroadcastReceiverMembersInjector);
    }
}
