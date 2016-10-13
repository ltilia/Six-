package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class TrackEventHttpTransactionFactory_Factory implements Factory<TrackEventHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<TrackEventHttpTransactionFactory> b;

    static {
        a = !TrackEventHttpTransactionFactory_Factory.class.desiredAssertionStatus();
    }

    public TrackEventHttpTransactionFactory_Factory(MembersInjector<TrackEventHttpTransactionFactory> trackEventHttpTransactionFactoryMembersInjector) {
        if (a || trackEventHttpTransactionFactoryMembersInjector != null) {
            this.b = trackEventHttpTransactionFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final TrackEventHttpTransactionFactory get() {
        return (TrackEventHttpTransactionFactory) MembersInjectors.injectMembers(this.b, new TrackEventHttpTransactionFactory());
    }

    public static Factory<TrackEventHttpTransactionFactory> create(MembersInjector<TrackEventHttpTransactionFactory> trackEventHttpTransactionFactoryMembersInjector) {
        return new TrackEventHttpTransactionFactory_Factory(trackEventHttpTransactionFactoryMembersInjector);
    }
}
