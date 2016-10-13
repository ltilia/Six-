package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AppFingerprintHttpResponseHandler_Factory implements Factory<AppFingerprintHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AppFingerprintHttpResponseHandler> b;

    static {
        a = !AppFingerprintHttpResponseHandler_Factory.class.desiredAssertionStatus();
    }

    public AppFingerprintHttpResponseHandler_Factory(MembersInjector<AppFingerprintHttpResponseHandler> appFingerprintHttpResponseHandlerMembersInjector) {
        if (a || appFingerprintHttpResponseHandlerMembersInjector != null) {
            this.b = appFingerprintHttpResponseHandlerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AppFingerprintHttpResponseHandler get() {
        return (AppFingerprintHttpResponseHandler) MembersInjectors.injectMembers(this.b, new AppFingerprintHttpResponseHandler());
    }

    public static Factory<AppFingerprintHttpResponseHandler> create(MembersInjector<AppFingerprintHttpResponseHandler> appFingerprintHttpResponseHandlerMembersInjector) {
        return new AppFingerprintHttpResponseHandler_Factory(appFingerprintHttpResponseHandlerMembersInjector);
    }
}
