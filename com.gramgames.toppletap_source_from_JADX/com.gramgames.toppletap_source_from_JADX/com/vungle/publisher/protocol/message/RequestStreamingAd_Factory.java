package com.vungle.publisher.protocol.message;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestStreamingAd_Factory implements Factory<RequestStreamingAd> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<RequestStreamingAd> b;

    static {
        a = !RequestStreamingAd_Factory.class.desiredAssertionStatus();
    }

    public RequestStreamingAd_Factory(MembersInjector<RequestStreamingAd> requestStreamingAdMembersInjector) {
        if (a || requestStreamingAdMembersInjector != null) {
            this.b = requestStreamingAdMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final RequestStreamingAd get() {
        return (RequestStreamingAd) MembersInjectors.injectMembers(this.b, new RequestStreamingAd());
    }

    public static Factory<RequestStreamingAd> create(MembersInjector<RequestStreamingAd> requestStreamingAdMembersInjector) {
        return new RequestStreamingAd_Factory(requestStreamingAdMembersInjector);
    }
}
