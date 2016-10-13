package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ReportLocalAdHttpResponseHandler_Factory implements Factory<ReportLocalAdHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ReportLocalAdHttpResponseHandler> b;

    static {
        a = !ReportLocalAdHttpResponseHandler_Factory.class.desiredAssertionStatus();
    }

    public ReportLocalAdHttpResponseHandler_Factory(MembersInjector<ReportLocalAdHttpResponseHandler> reportLocalAdHttpResponseHandlerMembersInjector) {
        if (a || reportLocalAdHttpResponseHandlerMembersInjector != null) {
            this.b = reportLocalAdHttpResponseHandlerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ReportLocalAdHttpResponseHandler get() {
        return (ReportLocalAdHttpResponseHandler) MembersInjectors.injectMembers(this.b, new ReportLocalAdHttpResponseHandler());
    }

    public static Factory<ReportLocalAdHttpResponseHandler> create(MembersInjector<ReportLocalAdHttpResponseHandler> reportLocalAdHttpResponseHandlerMembersInjector) {
        return new ReportLocalAdHttpResponseHandler_Factory(reportLocalAdHttpResponseHandlerMembersInjector);
    }
}
