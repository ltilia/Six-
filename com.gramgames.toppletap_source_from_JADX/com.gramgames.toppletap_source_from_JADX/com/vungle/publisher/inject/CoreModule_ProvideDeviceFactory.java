package com.vungle.publisher.inject;

import com.vungle.publisher.ek;
import com.vungle.publisher.env.AndroidDevice;
import com.vungle.publisher.fi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CoreModule_ProvideDeviceFactory implements Factory<ek> {
    static final /* synthetic */ boolean a;
    private final fi b;
    private final Provider<AndroidDevice> c;

    static {
        a = !CoreModule_ProvideDeviceFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideDeviceFactory(fi module, Provider<AndroidDevice> androidDeviceProvider) {
        if (a || module != null) {
            this.b = module;
            if (a || androidDeviceProvider != null) {
                this.c = androidDeviceProvider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public final ek get() {
        return (ek) Preconditions.checkNotNull((AndroidDevice) this.c.get(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ek> create(fi module, Provider<AndroidDevice> androidDeviceProvider) {
        return new CoreModule_ProvideDeviceFactory(module, androidDeviceProvider);
    }
}
