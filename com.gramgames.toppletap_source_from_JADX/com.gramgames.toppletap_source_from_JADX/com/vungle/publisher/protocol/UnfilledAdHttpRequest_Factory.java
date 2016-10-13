package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class UnfilledAdHttpRequest_Factory implements Factory<UnfilledAdHttpRequest> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<UnfilledAdHttpRequest> b;

    static {
        a = !UnfilledAdHttpRequest_Factory.class.desiredAssertionStatus();
    }

    public UnfilledAdHttpRequest_Factory(MembersInjector<UnfilledAdHttpRequest> unfilledAdHttpRequestMembersInjector) {
        if (a || unfilledAdHttpRequestMembersInjector != null) {
            this.b = unfilledAdHttpRequestMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final UnfilledAdHttpRequest get() {
        return (UnfilledAdHttpRequest) MembersInjectors.injectMembers(this.b, new UnfilledAdHttpRequest());
    }

    public static Factory<UnfilledAdHttpRequest> create(MembersInjector<UnfilledAdHttpRequest> unfilledAdHttpRequestMembersInjector) {
        return new UnfilledAdHttpRequest_Factory(unfilledAdHttpRequestMembersInjector);
    }
}
