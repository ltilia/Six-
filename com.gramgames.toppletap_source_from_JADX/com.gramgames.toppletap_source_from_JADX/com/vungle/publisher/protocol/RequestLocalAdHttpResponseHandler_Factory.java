package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestLocalAdHttpResponseHandler_Factory implements Factory<RequestLocalAdHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<RequestLocalAdHttpResponseHandler> b;

    static {
        a = !RequestLocalAdHttpResponseHandler_Factory.class.desiredAssertionStatus();
    }

    public RequestLocalAdHttpResponseHandler_Factory(MembersInjector<RequestLocalAdHttpResponseHandler> requestLocalAdHttpResponseHandlerMembersInjector) {
        if (a || requestLocalAdHttpResponseHandlerMembersInjector != null) {
            this.b = requestLocalAdHttpResponseHandlerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final RequestLocalAdHttpResponseHandler get() {
        return (RequestLocalAdHttpResponseHandler) MembersInjectors.injectMembers(this.b, new RequestLocalAdHttpResponseHandler());
    }

    public static Factory<RequestLocalAdHttpResponseHandler> create(MembersInjector<RequestLocalAdHttpResponseHandler> requestLocalAdHttpResponseHandlerMembersInjector) {
        return new RequestLocalAdHttpResponseHandler_Factory(requestLocalAdHttpResponseHandlerMembersInjector);
    }
}
