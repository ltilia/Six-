package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class HttpTransport_Factory implements Factory<HttpTransport> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<HttpTransport> b;

    static {
        a = !HttpTransport_Factory.class.desiredAssertionStatus();
    }

    public HttpTransport_Factory(MembersInjector<HttpTransport> httpTransportMembersInjector) {
        if (a || httpTransportMembersInjector != null) {
            this.b = httpTransportMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final HttpTransport get() {
        return (HttpTransport) MembersInjectors.injectMembers(this.b, new HttpTransport());
    }

    public static Factory<HttpTransport> create(MembersInjector<HttpTransport> httpTransportMembersInjector) {
        return new HttpTransport_Factory(httpTransportMembersInjector);
    }
}
