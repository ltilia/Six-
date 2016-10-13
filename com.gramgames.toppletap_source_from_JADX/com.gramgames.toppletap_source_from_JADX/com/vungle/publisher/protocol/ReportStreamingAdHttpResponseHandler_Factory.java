package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ReportStreamingAdHttpResponseHandler_Factory implements Factory<ReportStreamingAdHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ReportStreamingAdHttpResponseHandler> b;

    static {
        a = !ReportStreamingAdHttpResponseHandler_Factory.class.desiredAssertionStatus();
    }

    public ReportStreamingAdHttpResponseHandler_Factory(MembersInjector<ReportStreamingAdHttpResponseHandler> reportStreamingAdHttpResponseHandlerMembersInjector) {
        if (a || reportStreamingAdHttpResponseHandlerMembersInjector != null) {
            this.b = reportStreamingAdHttpResponseHandlerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ReportStreamingAdHttpResponseHandler get() {
        return (ReportStreamingAdHttpResponseHandler) MembersInjectors.injectMembers(this.b, new ReportStreamingAdHttpResponseHandler());
    }

    public static Factory<ReportStreamingAdHttpResponseHandler> create(MembersInjector<ReportStreamingAdHttpResponseHandler> reportStreamingAdHttpResponseHandlerMembersInjector) {
        return new ReportStreamingAdHttpResponseHandler_Factory(reportStreamingAdHttpResponseHandlerMembersInjector);
    }
}
