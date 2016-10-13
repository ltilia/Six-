package com.vungle.publisher.protocol.message;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ReportExceptions_Factory implements Factory<ReportExceptions> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ReportExceptions> b;

    static {
        a = !ReportExceptions_Factory.class.desiredAssertionStatus();
    }

    public ReportExceptions_Factory(MembersInjector<ReportExceptions> reportExceptionsMembersInjector) {
        if (a || reportExceptionsMembersInjector != null) {
            this.b = reportExceptionsMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ReportExceptions get() {
        return (ReportExceptions) MembersInjectors.injectMembers(this.b, new ReportExceptions());
    }

    public static Factory<ReportExceptions> create(MembersInjector<ReportExceptions> reportExceptionsMembersInjector) {
        return new ReportExceptions_Factory(reportExceptionsMembersInjector);
    }
}
