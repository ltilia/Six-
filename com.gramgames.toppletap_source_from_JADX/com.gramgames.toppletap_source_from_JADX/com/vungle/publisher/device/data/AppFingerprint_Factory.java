package com.vungle.publisher.device.data;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AppFingerprint_Factory implements Factory<AppFingerprint> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AppFingerprint> b;

    static {
        a = !AppFingerprint_Factory.class.desiredAssertionStatus();
    }

    public AppFingerprint_Factory(MembersInjector<AppFingerprint> appFingerprintMembersInjector) {
        if (a || appFingerprintMembersInjector != null) {
            this.b = appFingerprintMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AppFingerprint get() {
        return (AppFingerprint) MembersInjectors.injectMembers(this.b, new AppFingerprint());
    }

    public static Factory<AppFingerprint> create(MembersInjector<AppFingerprint> appFingerprintMembersInjector) {
        return new AppFingerprint_Factory(appFingerprintMembersInjector);
    }
}
