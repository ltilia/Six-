package com.vungle.publisher.net.http;

import com.vungle.publisher.gh;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ExternalHttpGateway_Factory implements Factory<gh> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<gh> b;

    static {
        a = !ExternalHttpGateway_Factory.class.desiredAssertionStatus();
    }

    public ExternalHttpGateway_Factory(MembersInjector<gh> externalHttpGatewayMembersInjector) {
        if (a || externalHttpGatewayMembersInjector != null) {
            this.b = externalHttpGatewayMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final gh get() {
        return (gh) MembersInjectors.injectMembers(this.b, new gh());
    }

    public static Factory<gh> create(MembersInjector<gh> externalHttpGatewayMembersInjector) {
        return new ExternalHttpGateway_Factory(externalHttpGatewayMembersInjector);
    }
}
