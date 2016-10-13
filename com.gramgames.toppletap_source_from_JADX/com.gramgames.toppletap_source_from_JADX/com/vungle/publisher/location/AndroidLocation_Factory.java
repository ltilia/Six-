package com.vungle.publisher.location;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AndroidLocation_Factory implements Factory<AndroidLocation> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AndroidLocation> b;

    static {
        a = !AndroidLocation_Factory.class.desiredAssertionStatus();
    }

    public AndroidLocation_Factory(MembersInjector<AndroidLocation> androidLocationMembersInjector) {
        if (a || androidLocationMembersInjector != null) {
            this.b = androidLocationMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AndroidLocation get() {
        return (AndroidLocation) MembersInjectors.injectMembers(this.b, new AndroidLocation());
    }

    public static Factory<AndroidLocation> create(MembersInjector<AndroidLocation> androidLocationMembersInjector) {
        return new AndroidLocation_Factory(androidLocationMembersInjector);
    }
}
