package com.vungle.publisher.env;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AdvertisingDeviceIdStrategy_Factory implements Factory<AdvertisingDeviceIdStrategy> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AdvertisingDeviceIdStrategy> b;

    static {
        a = !AdvertisingDeviceIdStrategy_Factory.class.desiredAssertionStatus();
    }

    public AdvertisingDeviceIdStrategy_Factory(MembersInjector<AdvertisingDeviceIdStrategy> advertisingDeviceIdStrategyMembersInjector) {
        if (a || advertisingDeviceIdStrategyMembersInjector != null) {
            this.b = advertisingDeviceIdStrategyMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AdvertisingDeviceIdStrategy get() {
        return (AdvertisingDeviceIdStrategy) MembersInjectors.injectMembers(this.b, new AdvertisingDeviceIdStrategy());
    }

    public static Factory<AdvertisingDeviceIdStrategy> create(MembersInjector<AdvertisingDeviceIdStrategy> advertisingDeviceIdStrategyMembersInjector) {
        return new AdvertisingDeviceIdStrategy_Factory(advertisingDeviceIdStrategyMembersInjector);
    }
}
