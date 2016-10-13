package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestConfigHttpRequest_Factory implements Factory<RequestConfigHttpRequest> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<RequestConfigHttpRequest> b;

    static {
        a = !RequestConfigHttpRequest_Factory.class.desiredAssertionStatus();
    }

    public RequestConfigHttpRequest_Factory(MembersInjector<RequestConfigHttpRequest> requestConfigHttpRequestMembersInjector) {
        if (a || requestConfigHttpRequestMembersInjector != null) {
            this.b = requestConfigHttpRequestMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final RequestConfigHttpRequest get() {
        return (RequestConfigHttpRequest) MembersInjectors.injectMembers(this.b, new RequestConfigHttpRequest());
    }

    public static Factory<RequestConfigHttpRequest> create(MembersInjector<RequestConfigHttpRequest> requestConfigHttpRequestMembersInjector) {
        return new RequestConfigHttpRequest_Factory(requestConfigHttpRequestMembersInjector);
    }
}
