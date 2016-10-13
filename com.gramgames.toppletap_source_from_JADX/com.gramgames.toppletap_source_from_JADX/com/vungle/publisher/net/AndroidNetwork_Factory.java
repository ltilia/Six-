package com.vungle.publisher.net;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AndroidNetwork_Factory implements Factory<AndroidNetwork> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AndroidNetwork> b;

    static {
        a = !AndroidNetwork_Factory.class.desiredAssertionStatus();
    }

    public AndroidNetwork_Factory(MembersInjector<AndroidNetwork> androidNetworkMembersInjector) {
        if (a || androidNetworkMembersInjector != null) {
            this.b = androidNetworkMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AndroidNetwork get() {
        return (AndroidNetwork) MembersInjectors.injectMembers(this.b, new AndroidNetwork());
    }

    public static Factory<AndroidNetwork> create(MembersInjector<AndroidNetwork> androidNetworkMembersInjector) {
        return new AndroidNetwork_Factory(androidNetworkMembersInjector);
    }
}
