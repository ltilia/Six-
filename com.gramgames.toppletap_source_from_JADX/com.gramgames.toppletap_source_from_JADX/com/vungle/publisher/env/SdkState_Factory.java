package com.vungle.publisher.env;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class SdkState_Factory implements Factory<SdkState> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<SdkState> b;

    static {
        a = !SdkState_Factory.class.desiredAssertionStatus();
    }

    public SdkState_Factory(MembersInjector<SdkState> sdkStateMembersInjector) {
        if (a || sdkStateMembersInjector != null) {
            this.b = sdkStateMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final SdkState get() {
        return (SdkState) MembersInjectors.injectMembers(this.b, new SdkState());
    }

    public static Factory<SdkState> create(MembersInjector<SdkState> sdkStateMembersInjector) {
        return new SdkState_Factory(sdkStateMembersInjector);
    }
}
