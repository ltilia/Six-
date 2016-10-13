package com.vungle.publisher.location;

import com.vungle.publisher.fp;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class GoogleAggregateDetailedLocationProvider_Factory implements Factory<fp> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<fp> b;

    static {
        a = !GoogleAggregateDetailedLocationProvider_Factory.class.desiredAssertionStatus();
    }

    public GoogleAggregateDetailedLocationProvider_Factory(MembersInjector<fp> googleAggregateDetailedLocationProviderMembersInjector) {
        if (a || googleAggregateDetailedLocationProviderMembersInjector != null) {
            this.b = googleAggregateDetailedLocationProviderMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final fp get() {
        return (fp) MembersInjectors.injectMembers(this.b, new fp());
    }

    public static Factory<fp> create(MembersInjector<fp> googleAggregateDetailedLocationProviderMembersInjector) {
        return new GoogleAggregateDetailedLocationProvider_Factory(googleAggregateDetailedLocationProviderMembersInjector);
    }
}
