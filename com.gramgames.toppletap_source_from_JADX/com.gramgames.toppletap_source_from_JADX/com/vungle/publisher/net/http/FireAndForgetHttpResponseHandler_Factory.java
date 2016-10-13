package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class FireAndForgetHttpResponseHandler_Factory implements Factory<FireAndForgetHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<FireAndForgetHttpResponseHandler> b;

    static {
        a = !FireAndForgetHttpResponseHandler_Factory.class.desiredAssertionStatus();
    }

    public FireAndForgetHttpResponseHandler_Factory(MembersInjector<FireAndForgetHttpResponseHandler> fireAndForgetHttpResponseHandlerMembersInjector) {
        if (a || fireAndForgetHttpResponseHandlerMembersInjector != null) {
            this.b = fireAndForgetHttpResponseHandlerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final FireAndForgetHttpResponseHandler get() {
        return (FireAndForgetHttpResponseHandler) MembersInjectors.injectMembers(this.b, new FireAndForgetHttpResponseHandler());
    }

    public static Factory<FireAndForgetHttpResponseHandler> create(MembersInjector<FireAndForgetHttpResponseHandler> fireAndForgetHttpResponseHandlerMembersInjector) {
        return new FireAndForgetHttpResponseHandler_Factory(fireAndForgetHttpResponseHandlerMembersInjector);
    }
}
