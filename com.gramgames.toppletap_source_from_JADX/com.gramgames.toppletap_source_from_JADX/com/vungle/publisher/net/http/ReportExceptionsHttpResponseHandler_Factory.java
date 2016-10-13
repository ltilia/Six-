package com.vungle.publisher.net.http;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ReportExceptionsHttpResponseHandler_Factory implements Factory<ReportExceptionsHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ReportExceptionsHttpResponseHandler> b;

    static {
        a = !ReportExceptionsHttpResponseHandler_Factory.class.desiredAssertionStatus();
    }

    public ReportExceptionsHttpResponseHandler_Factory(MembersInjector<ReportExceptionsHttpResponseHandler> reportExceptionsHttpResponseHandlerMembersInjector) {
        if (a || reportExceptionsHttpResponseHandlerMembersInjector != null) {
            this.b = reportExceptionsHttpResponseHandlerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ReportExceptionsHttpResponseHandler get() {
        return (ReportExceptionsHttpResponseHandler) MembersInjectors.injectMembers(this.b, new ReportExceptionsHttpResponseHandler());
    }

    public static Factory<ReportExceptionsHttpResponseHandler> create(MembersInjector<ReportExceptionsHttpResponseHandler> reportExceptionsHttpResponseHandlerMembersInjector) {
        return new ReportExceptionsHttpResponseHandler_Factory(reportExceptionsHttpResponseHandlerMembersInjector);
    }
}
