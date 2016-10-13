package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class TrackInstallHttpTransactionFactory_Factory implements Factory<TrackInstallHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<TrackInstallHttpTransactionFactory> b;

    static {
        a = !TrackInstallHttpTransactionFactory_Factory.class.desiredAssertionStatus();
    }

    public TrackInstallHttpTransactionFactory_Factory(MembersInjector<TrackInstallHttpTransactionFactory> trackInstallHttpTransactionFactoryMembersInjector) {
        if (a || trackInstallHttpTransactionFactoryMembersInjector != null) {
            this.b = trackInstallHttpTransactionFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final TrackInstallHttpTransactionFactory get() {
        return (TrackInstallHttpTransactionFactory) MembersInjectors.injectMembers(this.b, new TrackInstallHttpTransactionFactory());
    }

    public static Factory<TrackInstallHttpTransactionFactory> create(MembersInjector<TrackInstallHttpTransactionFactory> trackInstallHttpTransactionFactoryMembersInjector) {
        return new TrackInstallHttpTransactionFactory_Factory(trackInstallHttpTransactionFactoryMembersInjector);
    }
}
