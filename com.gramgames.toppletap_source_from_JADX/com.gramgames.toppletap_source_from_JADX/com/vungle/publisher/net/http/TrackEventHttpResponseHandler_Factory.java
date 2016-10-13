package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class TrackEventHttpResponseHandler_Factory implements Factory<TrackEventHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<TrackEventHttpResponseHandler> b;

    static {
        a = !TrackEventHttpResponseHandler_Factory.class.desiredAssertionStatus();
    }

    public TrackEventHttpResponseHandler_Factory(MembersInjector<TrackEventHttpResponseHandler> trackEventHttpResponseHandlerMembersInjector) {
        if (a || trackEventHttpResponseHandlerMembersInjector != null) {
            this.b = trackEventHttpResponseHandlerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final TrackEventHttpResponseHandler get() {
        return (TrackEventHttpResponseHandler) MembersInjectors.injectMembers(this.b, new TrackEventHttpResponseHandler());
    }

    public static Factory<TrackEventHttpResponseHandler> create(MembersInjector<TrackEventHttpResponseHandler> trackEventHttpResponseHandlerMembersInjector) {
        return new TrackEventHttpResponseHandler_Factory(trackEventHttpResponseHandlerMembersInjector);
    }
}
