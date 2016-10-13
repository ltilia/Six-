package com.vungle.publisher.protocol.message;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class RequestLocalAd_Factory implements Factory<RequestLocalAd> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<RequestLocalAd> b;

    static {
        a = !RequestLocalAd_Factory.class.desiredAssertionStatus();
    }

    public RequestLocalAd_Factory(MembersInjector<RequestLocalAd> requestLocalAdMembersInjector) {
        if (a || requestLocalAdMembersInjector != null) {
            this.b = requestLocalAdMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final RequestLocalAd get() {
        return (RequestLocalAd) MembersInjectors.injectMembers(this.b, new RequestLocalAd());
    }

    public static Factory<RequestLocalAd> create(MembersInjector<RequestLocalAd> requestLocalAdMembersInjector) {
        return new RequestLocalAd_Factory(requestLocalAdMembersInjector);
    }
}
