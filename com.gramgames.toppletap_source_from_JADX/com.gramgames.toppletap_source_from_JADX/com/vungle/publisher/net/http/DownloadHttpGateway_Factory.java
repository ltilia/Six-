package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class DownloadHttpGateway_Factory implements Factory<DownloadHttpGateway> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<DownloadHttpGateway> b;

    static {
        a = !DownloadHttpGateway_Factory.class.desiredAssertionStatus();
    }

    public DownloadHttpGateway_Factory(MembersInjector<DownloadHttpGateway> downloadHttpGatewayMembersInjector) {
        if (a || downloadHttpGatewayMembersInjector != null) {
            this.b = downloadHttpGatewayMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final DownloadHttpGateway get() {
        return (DownloadHttpGateway) MembersInjectors.injectMembers(this.b, new DownloadHttpGateway());
    }

    public static Factory<DownloadHttpGateway> create(MembersInjector<DownloadHttpGateway> downloadHttpGatewayMembersInjector) {
        return new DownloadHttpGateway_Factory(downloadHttpGatewayMembersInjector);
    }
}
