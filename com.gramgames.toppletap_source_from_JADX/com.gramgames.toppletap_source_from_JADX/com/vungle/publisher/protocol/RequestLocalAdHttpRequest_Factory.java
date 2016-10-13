package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestLocalAdHttpRequest_Factory implements Factory<RequestLocalAdHttpRequest> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<RequestLocalAdHttpRequest> b;

    static {
        a = !RequestLocalAdHttpRequest_Factory.class.desiredAssertionStatus();
    }

    public RequestLocalAdHttpRequest_Factory(MembersInjector<RequestLocalAdHttpRequest> requestLocalAdHttpRequestMembersInjector) {
        if (a || requestLocalAdHttpRequestMembersInjector != null) {
            this.b = requestLocalAdHttpRequestMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final RequestLocalAdHttpRequest get() {
        return (RequestLocalAdHttpRequest) MembersInjectors.injectMembers(this.b, new RequestLocalAdHttpRequest());
    }

    public static Factory<RequestLocalAdHttpRequest> create(MembersInjector<RequestLocalAdHttpRequest> requestLocalAdHttpRequestMembersInjector) {
        return new RequestLocalAdHttpRequest_Factory(requestLocalAdHttpRequestMembersInjector);
    }
}
