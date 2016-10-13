package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class LocalAd_Factory implements Factory<LocalAd> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<LocalAd> b;

    static {
        a = !LocalAd_Factory.class.desiredAssertionStatus();
    }

    public LocalAd_Factory(MembersInjector<LocalAd> localAdMembersInjector) {
        if (a || localAdMembersInjector != null) {
            this.b = localAdMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final LocalAd get() {
        return (LocalAd) MembersInjectors.injectMembers(this.b, new LocalAd());
    }

    public static Factory<LocalAd> create(MembersInjector<LocalAd> localAdMembersInjector) {
        return new LocalAd_Factory(localAdMembersInjector);
    }
}
