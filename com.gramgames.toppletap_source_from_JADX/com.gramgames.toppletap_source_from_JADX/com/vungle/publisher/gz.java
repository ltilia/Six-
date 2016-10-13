package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.ReportExceptionsHttpRequest.Factory;
import com.vungle.publisher.net.http.ReportExceptionsHttpResponseHandler;
import com.vungle.publisher.net.http.ReportExceptionsHttpTransactionFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gz implements MembersInjector<ReportExceptionsHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;
    private final Provider<Factory> c;
    private final Provider<ReportExceptionsHttpResponseHandler.Factory> d;

    static {
        a = !gz.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ReportExceptionsHttpTransactionFactory reportExceptionsHttpTransactionFactory = (ReportExceptionsHttpTransactionFactory) obj;
        if (reportExceptionsHttpTransactionFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        reportExceptionsHttpTransactionFactory.c = this.b;
        reportExceptionsHttpTransactionFactory.a = (Factory) this.c.get();
        reportExceptionsHttpTransactionFactory.b = (ReportExceptionsHttpResponseHandler.Factory) this.d.get();
    }

    private gz(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<ReportExceptionsHttpResponseHandler.Factory> provider3) {
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

    public static MembersInjector<ReportExceptionsHttpTransactionFactory> a(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<ReportExceptionsHttpResponseHandler.Factory> provider3) {
        return new gz(provider, provider2, provider3);
    }
}
