package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ReportExceptionsHttpTransactionFactory_Factory implements Factory<ReportExceptionsHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ReportExceptionsHttpTransactionFactory> b;

    static {
        a = !ReportExceptionsHttpTransactionFactory_Factory.class.desiredAssertionStatus();
    }

    public ReportExceptionsHttpTransactionFactory_Factory(MembersInjector<ReportExceptionsHttpTransactionFactory> reportExceptionsHttpTransactionFactoryMembersInjector) {
        if (a || reportExceptionsHttpTransactionFactoryMembersInjector != null) {
            this.b = reportExceptionsHttpTransactionFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ReportExceptionsHttpTransactionFactory get() {
        return (ReportExceptionsHttpTransactionFactory) MembersInjectors.injectMembers(this.b, new ReportExceptionsHttpTransactionFactory());
    }

    public static Factory<ReportExceptionsHttpTransactionFactory> create(MembersInjector<ReportExceptionsHttpTransactionFactory> reportExceptionsHttpTransactionFactoryMembersInjector) {
        return new ReportExceptionsHttpTransactionFactory_Factory(reportExceptionsHttpTransactionFactoryMembersInjector);
    }
}
