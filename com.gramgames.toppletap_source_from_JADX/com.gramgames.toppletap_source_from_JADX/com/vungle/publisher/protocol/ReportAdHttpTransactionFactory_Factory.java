package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ReportAdHttpTransactionFactory_Factory implements Factory<ReportAdHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ReportAdHttpTransactionFactory> b;

    static {
        a = !ReportAdHttpTransactionFactory_Factory.class.desiredAssertionStatus();
    }

    public ReportAdHttpTransactionFactory_Factory(MembersInjector<ReportAdHttpTransactionFactory> reportAdHttpTransactionFactoryMembersInjector) {
        if (a || reportAdHttpTransactionFactoryMembersInjector != null) {
            this.b = reportAdHttpTransactionFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ReportAdHttpTransactionFactory get() {
        return (ReportAdHttpTransactionFactory) MembersInjectors.injectMembers(this.b, new ReportAdHttpTransactionFactory());
    }

    public static Factory<ReportAdHttpTransactionFactory> create(MembersInjector<ReportAdHttpTransactionFactory> reportAdHttpTransactionFactoryMembersInjector) {
        return new ReportAdHttpTransactionFactory_Factory(reportAdHttpTransactionFactoryMembersInjector);
    }
}
