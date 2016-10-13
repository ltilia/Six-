package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestStreamingAdHttpResponseHandler_Factory implements Factory<RequestStreamingAdHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<RequestStreamingAdHttpResponseHandler> b;

    static {
        a = !RequestStreamingAdHttpResponseHandler_Factory.class.desiredAssertionStatus();
    }

    public RequestStreamingAdHttpResponseHandler_Factory(MembersInjector<RequestStreamingAdHttpResponseHandler> requestStreamingAdHttpResponseHandlerMembersInjector) {
        if (a || requestStreamingAdHttpResponseHandlerMembersInjector != null) {
            this.b = requestStreamingAdHttpResponseHandlerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final RequestStreamingAdHttpResponseHandler get() {
        return (RequestStreamingAdHttpResponseHandler) MembersInjectors.injectMembers(this.b, new RequestStreamingAdHttpResponseHandler());
    }

    public static Factory<RequestStreamingAdHttpResponseHandler> create(MembersInjector<RequestStreamingAdHttpResponseHandler> requestStreamingAdHttpResponseHandlerMembersInjector) {
        return new RequestStreamingAdHttpResponseHandler_Factory(requestStreamingAdHttpResponseHandlerMembersInjector);
    }
}
