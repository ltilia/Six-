package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestStreamingAdHttpRequest_Factory implements Factory<RequestStreamingAdHttpRequest> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<RequestStreamingAdHttpRequest> b;

    static {
        a = !RequestStreamingAdHttpRequest_Factory.class.desiredAssertionStatus();
    }

    public RequestStreamingAdHttpRequest_Factory(MembersInjector<RequestStreamingAdHttpRequest> requestStreamingAdHttpRequestMembersInjector) {
        if (a || requestStreamingAdHttpRequestMembersInjector != null) {
            this.b = requestStreamingAdHttpRequestMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final RequestStreamingAdHttpRequest get() {
        return (RequestStreamingAdHttpRequest) MembersInjectors.injectMembers(this.b, new RequestStreamingAdHttpRequest());
    }

    public static Factory<RequestStreamingAdHttpRequest> create(MembersInjector<RequestStreamingAdHttpRequest> requestStreamingAdHttpRequestMembersInjector) {
        return new RequestStreamingAdHttpRequest_Factory(requestStreamingAdHttpRequestMembersInjector);
    }
}
