package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.protocol.EventTrackingHttpLogEntryDeleteDelegate;
import com.vungle.publisher.protocol.ReportLocalAdHttpResponseHandler;
import com.vungle.publisher.reporting.AdReportManager;
import dagger.MembersInjector;
import dagger.internal.DoubleCheckLazy;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hl implements MembersInjector<ReportLocalAdHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<Factory> c;
    private final Provider<AdReportManager> d;
    private final Provider<EventTrackingHttpLogEntryDeleteDelegate> e;

    static {
        a = !hl.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ReportLocalAdHttpResponseHandler reportLocalAdHttpResponseHandler = (ReportLocalAdHttpResponseHandler) obj;
        if (reportLocalAdHttpResponseHandler == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gq.a(reportLocalAdHttpResponseHandler, this.b);
        gq.b(reportLocalAdHttpResponseHandler, this.c);
        reportLocalAdHttpResponseHandler.a = DoubleCheckLazy.create(this.d);
        reportLocalAdHttpResponseHandler.b = (EventTrackingHttpLogEntryDeleteDelegate) this.e.get();
    }

    private hl(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<AdReportManager> provider3, Provider<EventTrackingHttpLogEntryDeleteDelegate> provider4) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ReportLocalAdHttpResponseHandler> a(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<AdReportManager> provider3, Provider<EventTrackingHttpLogEntryDeleteDelegate> provider4) {
        return new hl(provider, provider2, provider3, provider4);
    }
}
