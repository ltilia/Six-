package com.vungle.publisher.inject;

import com.vungle.publisher.env.AdvertisingDeviceIdStrategy;
import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import com.vungle.publisher.fk;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class IdStrategyModule_ProvideDeviceIdStrategyFactory implements Factory<DeviceIdStrategy> {
    static final /* synthetic */ boolean a;
    private final fk b;
    private final Provider<AdvertisingDeviceIdStrategy> c;

    static {
        a = !IdStrategyModule_ProvideDeviceIdStrategyFactory.class.desiredAssertionStatus();
    }

    public IdStrategyModule_ProvideDeviceIdStrategyFactory(fk module, Provider<AdvertisingDeviceIdStrategy> deviceIdStrategyProvider) {
        if (a || module != null) {
            this.b = module;
            if (a || deviceIdStrategyProvider != null) {
                this.c = deviceIdStrategyProvider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public final DeviceIdStrategy get() {
        return (DeviceIdStrategy) Preconditions.checkNotNull((AdvertisingDeviceIdStrategy) this.c.get(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<DeviceIdStrategy> create(fk module, Provider<AdvertisingDeviceIdStrategy> deviceIdStrategyProvider) {
        return new IdStrategyModule_ProvideDeviceIdStrategyFactory(module, deviceIdStrategyProvider);
    }
}
