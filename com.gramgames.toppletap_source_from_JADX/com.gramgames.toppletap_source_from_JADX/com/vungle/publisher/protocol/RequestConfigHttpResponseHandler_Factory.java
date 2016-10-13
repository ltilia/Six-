package com.vungle.publisher.protocol;

import com.vungle.publisher.hr;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestConfigHttpResponseHandler_Factory implements Factory<hr> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<hr> b;

    static {
        a = !RequestConfigHttpResponseHandler_Factory.class.desiredAssertionStatus();
    }

    public RequestConfigHttpResponseHandler_Factory(MembersInjector<hr> requestConfigHttpResponseHandlerMembersInjector) {
        if (a || requestConfigHttpResponseHandlerMembersInjector != null) {
            this.b = requestConfigHttpResponseHandlerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final hr get() {
        return (hr) MembersInjectors.injectMembers(this.b, new hr());
    }

    public static Factory<hr> create(MembersInjector<hr> requestConfigHttpResponseHandlerMembersInjector) {
        return new RequestConfigHttpResponseHandler_Factory(requestConfigHttpResponseHandlerMembersInjector);
    }
}
