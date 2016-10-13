package com.vungle.publisher.event;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ClientEventListenerAdapter_Factory implements Factory<ClientEventListenerAdapter> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ClientEventListenerAdapter> b;

    static {
        a = !ClientEventListenerAdapter_Factory.class.desiredAssertionStatus();
    }

    public ClientEventListenerAdapter_Factory(MembersInjector<ClientEventListenerAdapter> clientEventListenerAdapterMembersInjector) {
        if (a || clientEventListenerAdapterMembersInjector != null) {
            this.b = clientEventListenerAdapterMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ClientEventListenerAdapter get() {
        return (ClientEventListenerAdapter) MembersInjectors.injectMembers(this.b, new ClientEventListenerAdapter());
    }

    public static Factory<ClientEventListenerAdapter> create(MembersInjector<ClientEventListenerAdapter> clientEventListenerAdapterMembersInjector) {
        return new ClientEventListenerAdapter_Factory(clientEventListenerAdapterMembersInjector);
    }
}
