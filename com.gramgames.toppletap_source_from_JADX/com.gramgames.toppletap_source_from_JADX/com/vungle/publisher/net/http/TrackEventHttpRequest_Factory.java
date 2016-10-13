package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class TrackEventHttpRequest_Factory implements Factory<TrackEventHttpRequest> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<TrackEventHttpRequest> b;

    static {
        a = !TrackEventHttpRequest_Factory.class.desiredAssertionStatus();
    }

    public TrackEventHttpRequest_Factory(MembersInjector<TrackEventHttpRequest> trackEventHttpRequestMembersInjector) {
        if (a || trackEventHttpRequestMembersInjector != null) {
            this.b = trackEventHttpRequestMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final TrackEventHttpRequest get() {
        return (TrackEventHttpRequest) MembersInjectors.injectMembers(this.b, new TrackEventHttpRequest());
    }

    public static Factory<TrackEventHttpRequest> create(MembersInjector<TrackEventHttpRequest> trackEventHttpRequestMembersInjector) {
        return new TrackEventHttpRequest_Factory(trackEventHttpRequestMembersInjector);
    }
}
