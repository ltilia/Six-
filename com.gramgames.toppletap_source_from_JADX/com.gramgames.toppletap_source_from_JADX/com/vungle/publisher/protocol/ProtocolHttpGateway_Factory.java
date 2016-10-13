package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ProtocolHttpGateway_Factory implements Factory<ProtocolHttpGateway> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ProtocolHttpGateway> b;

    static {
        a = !ProtocolHttpGateway_Factory.class.desiredAssertionStatus();
    }

    public ProtocolHttpGateway_Factory(MembersInjector<ProtocolHttpGateway> protocolHttpGatewayMembersInjector) {
        if (a || protocolHttpGatewayMembersInjector != null) {
            this.b = protocolHttpGatewayMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ProtocolHttpGateway get() {
        return (ProtocolHttpGateway) MembersInjectors.injectMembers(this.b, new ProtocolHttpGateway());
    }

    public static Factory<ProtocolHttpGateway> create(MembersInjector<ProtocolHttpGateway> protocolHttpGatewayMembersInjector) {
        return new ProtocolHttpGateway_Factory(protocolHttpGatewayMembersInjector);
    }
}
