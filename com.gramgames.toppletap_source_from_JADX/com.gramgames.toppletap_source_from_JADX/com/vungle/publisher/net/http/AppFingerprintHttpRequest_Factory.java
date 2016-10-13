package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AppFingerprintHttpRequest_Factory implements Factory<AppFingerprintHttpRequest> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AppFingerprintHttpRequest> b;

    static {
        a = !AppFingerprintHttpRequest_Factory.class.desiredAssertionStatus();
    }

    public AppFingerprintHttpRequest_Factory(MembersInjector<AppFingerprintHttpRequest> appFingerprintHttpRequestMembersInjector) {
        if (a || appFingerprintHttpRequestMembersInjector != null) {
            this.b = appFingerprintHttpRequestMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AppFingerprintHttpRequest get() {
        return (AppFingerprintHttpRequest) MembersInjectors.injectMembers(this.b, new AppFingerprintHttpRequest());
    }

    public static Factory<AppFingerprintHttpRequest> create(MembersInjector<AppFingerprintHttpRequest> appFingerprintHttpRequestMembersInjector) {
        return new AppFingerprintHttpRequest_Factory(appFingerprintHttpRequestMembersInjector);
    }
}
