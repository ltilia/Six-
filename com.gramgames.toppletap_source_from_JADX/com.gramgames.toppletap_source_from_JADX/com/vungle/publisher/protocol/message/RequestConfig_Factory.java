package com.vungle.publisher.protocol.message;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestConfig_Factory implements Factory<RequestConfig> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<RequestConfig> b;

    static {
        a = !RequestConfig_Factory.class.desiredAssertionStatus();
    }

    public RequestConfig_Factory(MembersInjector<RequestConfig> requestConfigMembersInjector) {
        if (a || requestConfigMembersInjector != null) {
            this.b = requestConfigMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final RequestConfig get() {
        return (RequestConfig) MembersInjectors.injectMembers(this.b, new RequestConfig());
    }

    public static Factory<RequestConfig> create(MembersInjector<RequestConfig> requestConfigMembersInjector) {
        return new RequestConfig_Factory(requestConfigMembersInjector);
    }
}
