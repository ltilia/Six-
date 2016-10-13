package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class TrackInstallHttpRequest_Factory implements Factory<TrackInstallHttpRequest> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<TrackInstallHttpRequest> b;

    static {
        a = !TrackInstallHttpRequest_Factory.class.desiredAssertionStatus();
    }

    public TrackInstallHttpRequest_Factory(MembersInjector<TrackInstallHttpRequest> trackInstallHttpRequestMembersInjector) {
        if (a || trackInstallHttpRequestMembersInjector != null) {
            this.b = trackInstallHttpRequestMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final TrackInstallHttpRequest get() {
        return (TrackInstallHttpRequest) MembersInjectors.injectMembers(this.b, new TrackInstallHttpRequest());
    }

    public static Factory<TrackInstallHttpRequest> create(MembersInjector<TrackInstallHttpRequest> trackInstallHttpRequestMembersInjector) {
        return new TrackInstallHttpRequest_Factory(trackInstallHttpRequestMembersInjector);
    }
}
