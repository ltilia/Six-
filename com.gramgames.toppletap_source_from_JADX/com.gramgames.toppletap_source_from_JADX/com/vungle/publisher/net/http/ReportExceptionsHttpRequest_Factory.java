package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ReportExceptionsHttpRequest_Factory implements Factory<ReportExceptionsHttpRequest> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ReportExceptionsHttpRequest> b;

    static {
        a = !ReportExceptionsHttpRequest_Factory.class.desiredAssertionStatus();
    }

    public ReportExceptionsHttpRequest_Factory(MembersInjector<ReportExceptionsHttpRequest> reportExceptionsHttpRequestMembersInjector) {
        if (a || reportExceptionsHttpRequestMembersInjector != null) {
            this.b = reportExceptionsHttpRequestMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ReportExceptionsHttpRequest get() {
        return (ReportExceptionsHttpRequest) MembersInjectors.injectMembers(this.b, new ReportExceptionsHttpRequest());
    }

    public static Factory<ReportExceptionsHttpRequest> create(MembersInjector<ReportExceptionsHttpRequest> reportExceptionsHttpRequestMembersInjector) {
        return new ReportExceptionsHttpRequest_Factory(reportExceptionsHttpRequestMembersInjector);
    }
}
