package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class StreamingAd_Factory implements Factory<StreamingAd> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<StreamingAd> b;

    static {
        a = !StreamingAd_Factory.class.desiredAssertionStatus();
    }

    public StreamingAd_Factory(MembersInjector<StreamingAd> streamingAdMembersInjector) {
        if (a || streamingAdMembersInjector != null) {
            this.b = streamingAdMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final StreamingAd get() {
        return (StreamingAd) MembersInjectors.injectMembers(this.b, new StreamingAd());
    }

    public static Factory<StreamingAd> create(MembersInjector<StreamingAd> streamingAdMembersInjector) {
        return new StreamingAd_Factory(streamingAdMembersInjector);
    }
}
