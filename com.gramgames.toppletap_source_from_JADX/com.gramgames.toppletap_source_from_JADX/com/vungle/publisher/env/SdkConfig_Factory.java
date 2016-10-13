package com.vungle.publisher.env;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class SdkConfig_Factory implements Factory<SdkConfig> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<SdkConfig> b;

    static {
        a = !SdkConfig_Factory.class.desiredAssertionStatus();
    }

    public SdkConfig_Factory(MembersInjector<SdkConfig> sdkConfigMembersInjector) {
        if (a || sdkConfigMembersInjector != null) {
            this.b = sdkConfigMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final SdkConfig get() {
        return (SdkConfig) MembersInjectors.injectMembers(this.b, new SdkConfig());
    }

    public static Factory<SdkConfig> create(MembersInjector<SdkConfig> sdkConfigMembersInjector) {
        return new SdkConfig_Factory(sdkConfigMembersInjector);
    }
}
