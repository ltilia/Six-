package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.protocol.ReportStreamingAdHttpResponseHandler;
import com.vungle.publisher.reporting.AdReportManager;
import dagger.MembersInjector;
import dagger.internal.DoubleCheckLazy;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hn implements MembersInjector<ReportStreamingAdHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<Factory> c;
    private final Provider<AdReportManager> d;

    static {
        a = !hn.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ReportStreamingAdHttpResponseHandler reportStreamingAdHttpResponseHandler = (ReportStreamingAdHttpResponseHandler) obj;
        if (reportStreamingAdHttpResponseHandler == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gq.a(reportStreamingAdHttpResponseHandler, this.b);
        gq.b(reportStreamingAdHttpResponseHandler, this.c);
        reportStreamingAdHttpResponseHandler.a = DoubleCheckLazy.create(this.d);
    }

    private hn(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<AdReportManager> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ReportStreamingAdHttpResponseHandler> a(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<AdReportManager> provider3) {
        return new hn(provider, provider2, provider3);
    }
}
