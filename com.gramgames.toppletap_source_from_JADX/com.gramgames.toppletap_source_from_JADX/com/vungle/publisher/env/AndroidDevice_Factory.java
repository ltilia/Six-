package com.vungle.publisher.env;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AndroidDevice_Factory implements Factory<AndroidDevice> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AndroidDevice> b;

    static {
        a = !AndroidDevice_Factory.class.desiredAssertionStatus();
    }

    public AndroidDevice_Factory(MembersInjector<AndroidDevice> androidDeviceMembersInjector) {
        if (a || androidDeviceMembersInjector != null) {
            this.b = androidDeviceMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AndroidDevice get() {
        return (AndroidDevice) MembersInjectors.injectMembers(this.b, new AndroidDevice());
    }

    public static Factory<AndroidDevice> create(MembersInjector<AndroidDevice> androidDeviceMembersInjector) {
        return new AndroidDevice_Factory(androidDeviceMembersInjector);
    }
}
