package com.vungle.publisher.net.http;

import com.vungle.publisher.gu;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class IngestHttpGateway_Factory implements Factory<gu> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<gu> b;

    static {
        a = !IngestHttpGateway_Factory.class.desiredAssertionStatus();
    }

    public IngestHttpGateway_Factory(MembersInjector<gu> ingestHttpGatewayMembersInjector) {
        if (a || ingestHttpGatewayMembersInjector != null) {
            this.b = ingestHttpGatewayMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final gu get() {
        return (gu) MembersInjectors.injectMembers(this.b, new gu());
    }

    public static Factory<gu> create(MembersInjector<gu> ingestHttpGatewayMembersInjector) {
        return new IngestHttpGateway_Factory(ingestHttpGatewayMembersInjector);
    }
}
