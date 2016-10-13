package com.vungle.sdk;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class VungleAdvert_Factory implements Factory<VungleAdvert> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<VungleAdvert> b;

    static {
        a = !VungleAdvert_Factory.class.desiredAssertionStatus();
    }

    public VungleAdvert_Factory(MembersInjector<VungleAdvert> vungleAdvertMembersInjector) {
        if (a || vungleAdvertMembersInjector != null) {
            this.b = vungleAdvertMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final VungleAdvert get() {
        return (VungleAdvert) MembersInjectors.injectMembers(this.b, new VungleAdvert());
    }

    public static Factory<VungleAdvert> create(MembersInjector<VungleAdvert> vungleAdvertMembersInjector) {
        return new VungleAdvert_Factory(vungleAdvertMembersInjector);
    }
}
