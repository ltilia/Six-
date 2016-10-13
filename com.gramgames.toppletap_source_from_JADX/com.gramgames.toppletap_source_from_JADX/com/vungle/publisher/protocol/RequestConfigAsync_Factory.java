package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestConfigAsync_Factory implements Factory<RequestConfigAsync> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<RequestConfigAsync> b;

    static {
        a = !RequestConfigAsync_Factory.class.desiredAssertionStatus();
    }

    public RequestConfigAsync_Factory(MembersInjector<RequestConfigAsync> requestConfigAsyncMembersInjector) {
        if (a || requestConfigAsyncMembersInjector != null) {
            this.b = requestConfigAsyncMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final RequestConfigAsync get() {
        return (RequestConfigAsync) MembersInjectors.injectMembers(this.b, new RequestConfigAsync());
    }

    public static Factory<RequestConfigAsync> create(MembersInjector<RequestConfigAsync> requestConfigAsyncMembersInjector) {
        return new RequestConfigAsync_Factory(requestConfigAsyncMembersInjector);
    }
}
