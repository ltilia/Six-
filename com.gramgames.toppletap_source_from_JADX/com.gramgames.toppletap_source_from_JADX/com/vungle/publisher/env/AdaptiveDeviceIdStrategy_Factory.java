package com.vungle.publisher.env;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AdaptiveDeviceIdStrategy_Factory implements Factory<AdaptiveDeviceIdStrategy> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AdaptiveDeviceIdStrategy> b;

    static {
        a = !AdaptiveDeviceIdStrategy_Factory.class.desiredAssertionStatus();
    }

    public AdaptiveDeviceIdStrategy_Factory(MembersInjector<AdaptiveDeviceIdStrategy> adaptiveDeviceIdStrategyMembersInjector) {
        if (a || adaptiveDeviceIdStrategyMembersInjector != null) {
            this.b = adaptiveDeviceIdStrategyMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AdaptiveDeviceIdStrategy get() {
        return (AdaptiveDeviceIdStrategy) MembersInjectors.injectMembers(this.b, new AdaptiveDeviceIdStrategy());
    }

    public static Factory<AdaptiveDeviceIdStrategy> create(MembersInjector<AdaptiveDeviceIdStrategy> adaptiveDeviceIdStrategyMembersInjector) {
        return new AdaptiveDeviceIdStrategy_Factory(adaptiveDeviceIdStrategyMembersInjector);
    }
}
