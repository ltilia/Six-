package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AppFingerprintHttpTransactionFactory_Factory implements Factory<AppFingerprintHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AppFingerprintHttpTransactionFactory> b;

    static {
        a = !AppFingerprintHttpTransactionFactory_Factory.class.desiredAssertionStatus();
    }

    public AppFingerprintHttpTransactionFactory_Factory(MembersInjector<AppFingerprintHttpTransactionFactory> appFingerprintHttpTransactionFactoryMembersInjector) {
        if (a || appFingerprintHttpTransactionFactoryMembersInjector != null) {
            this.b = appFingerprintHttpTransactionFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AppFingerprintHttpTransactionFactory get() {
        return (AppFingerprintHttpTransactionFactory) MembersInjectors.injectMembers(this.b, new AppFingerprintHttpTransactionFactory());
    }

    public static Factory<AppFingerprintHttpTransactionFactory> create(MembersInjector<AppFingerprintHttpTransactionFactory> appFingerprintHttpTransactionFactoryMembersInjector) {
        return new AppFingerprintHttpTransactionFactory_Factory(appFingerprintHttpTransactionFactoryMembersInjector);
    }
}
