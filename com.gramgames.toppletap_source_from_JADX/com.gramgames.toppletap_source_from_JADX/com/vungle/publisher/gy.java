package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.net.http.ReportExceptionsHttpResponseHandler;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gy implements MembersInjector<ReportExceptionsHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<Factory> c;

    static {
        a = !gy.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ReportExceptionsHttpResponseHandler reportExceptionsHttpResponseHandler = (ReportExceptionsHttpResponseHandler) obj;
        if (reportExceptionsHttpResponseHandler == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        reportExceptionsHttpResponseHandler.f = (ScheduledPriorityExecutor) this.b.get();
        reportExceptionsHttpResponseHandler.g = (Factory) this.c.get();
        reportExceptionsHttpResponseHandler.b = (Factory) this.c.get();
    }

    private gy(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ReportExceptionsHttpResponseHandler> a(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2) {
        return new gy(provider, provider2);
    }
}
