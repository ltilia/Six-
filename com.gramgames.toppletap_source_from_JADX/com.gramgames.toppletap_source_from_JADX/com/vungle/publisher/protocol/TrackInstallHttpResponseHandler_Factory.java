package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class TrackInstallHttpResponseHandler_Factory implements Factory<TrackInstallHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<TrackInstallHttpResponseHandler> b;

    static {
        a = !TrackInstallHttpResponseHandler_Factory.class.desiredAssertionStatus();
    }

    public TrackInstallHttpResponseHandler_Factory(MembersInjector<TrackInstallHttpResponseHandler> trackInstallHttpResponseHandlerMembersInjector) {
        if (a || trackInstallHttpResponseHandlerMembersInjector != null) {
            this.b = trackInstallHttpResponseHandlerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final TrackInstallHttpResponseHandler get() {
        return (TrackInstallHttpResponseHandler) MembersInjectors.injectMembers(this.b, new TrackInstallHttpResponseHandler());
    }

    public static Factory<TrackInstallHttpResponseHandler> create(MembersInjector<TrackInstallHttpResponseHandler> trackInstallHttpResponseHandlerMembersInjector) {
        return new TrackInstallHttpResponseHandler_Factory(trackInstallHttpResponseHandlerMembersInjector);
    }
}
