package com.vungle.publisher.device.data;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AppFingerprintManager_Factory implements Factory<AppFingerprintManager> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AppFingerprintManager> b;

    static {
        a = !AppFingerprintManager_Factory.class.desiredAssertionStatus();
    }

    public AppFingerprintManager_Factory(MembersInjector<AppFingerprintManager> appFingerprintManagerMembersInjector) {
        if (a || appFingerprintManagerMembersInjector != null) {
            this.b = appFingerprintManagerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AppFingerprintManager get() {
        return (AppFingerprintManager) MembersInjectors.injectMembers(this.b, new AppFingerprintManager());
    }

    public static Factory<AppFingerprintManager> create(MembersInjector<AppFingerprintManager> appFingerprintManagerMembersInjector) {
        return new AppFingerprintManager_Factory(appFingerprintManagerMembersInjector);
    }
}
