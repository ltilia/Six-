package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class DownloadHttpTransactionFactory_Factory implements Factory<DownloadHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<DownloadHttpTransactionFactory> b;

    static {
        a = !DownloadHttpTransactionFactory_Factory.class.desiredAssertionStatus();
    }

    public DownloadHttpTransactionFactory_Factory(MembersInjector<DownloadHttpTransactionFactory> downloadHttpTransactionFactoryMembersInjector) {
        if (a || downloadHttpTransactionFactoryMembersInjector != null) {
            this.b = downloadHttpTransactionFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final DownloadHttpTransactionFactory get() {
        return (DownloadHttpTransactionFactory) MembersInjectors.injectMembers(this.b, new DownloadHttpTransactionFactory());
    }

    public static Factory<DownloadHttpTransactionFactory> create(MembersInjector<DownloadHttpTransactionFactory> downloadHttpTransactionFactoryMembersInjector) {
        return new DownloadHttpTransactionFactory_Factory(downloadHttpTransactionFactoryMembersInjector);
    }
}
