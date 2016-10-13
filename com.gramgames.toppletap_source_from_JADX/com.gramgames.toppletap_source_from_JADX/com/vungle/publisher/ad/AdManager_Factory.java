package com.vungle.publisher.ad;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AdManager_Factory implements Factory<AdManager> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AdManager> b;

    static {
        a = !AdManager_Factory.class.desiredAssertionStatus();
    }

    public AdManager_Factory(MembersInjector<AdManager> adManagerMembersInjector) {
        if (a || adManagerMembersInjector != null) {
            this.b = adManagerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AdManager get() {
        return (AdManager) MembersInjectors.injectMembers(this.b, new AdManager());
    }

    public static Factory<AdManager> create(MembersInjector<AdManager> adManagerMembersInjector) {
        return new AdManager_Factory(adManagerMembersInjector);
    }
}
