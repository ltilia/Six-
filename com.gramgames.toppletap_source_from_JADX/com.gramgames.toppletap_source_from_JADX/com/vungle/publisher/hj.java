package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.ReportAdHttpTransactionFactory;
import com.vungle.publisher.protocol.ReportLocalAdHttpRequest.Factory;
import com.vungle.publisher.protocol.ReportLocalAdHttpResponseHandler;
import com.vungle.publisher.protocol.ReportStreamingAdHttpRequest;
import com.vungle.publisher.protocol.ReportStreamingAdHttpResponseHandler;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hj implements MembersInjector<ReportAdHttpTransactionFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpTransaction> b;
    private final Provider<Factory> c;
    private final Provider<ReportLocalAdHttpResponseHandler> d;
    private final Provider<ReportStreamingAdHttpRequest.Factory> e;
    private final Provider<ReportStreamingAdHttpResponseHandler> f;

    static {
        a = !hj.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ReportAdHttpTransactionFactory reportAdHttpTransactionFactory = (ReportAdHttpTransactionFactory) obj;
        if (reportAdHttpTransactionFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gr.a(reportAdHttpTransactionFactory, this.b);
        reportAdHttpTransactionFactory.a = (Factory) this.c.get();
        reportAdHttpTransactionFactory.b = (ReportLocalAdHttpResponseHandler) this.d.get();
        reportAdHttpTransactionFactory.d = (ReportStreamingAdHttpRequest.Factory) this.e.get();
        reportAdHttpTransactionFactory.e = (ReportStreamingAdHttpResponseHandler) this.f.get();
    }

    private hj(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<ReportLocalAdHttpResponseHandler> provider3, Provider<ReportStreamingAdHttpRequest.Factory> provider4, Provider<ReportStreamingAdHttpResponseHandler> provider5) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
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
        throw new AssertionError();
    }

    public static MembersInjector<ReportAdHttpTransactionFactory> a(Provider<HttpTransaction> provider, Provider<Factory> provider2, Provider<ReportLocalAdHttpResponseHandler> provider3, Provider<ReportStreamingAdHttpRequest.Factory> provider4, Provider<ReportStreamingAdHttpResponseHandler> provider5) {
        return new hj(provider, provider2, provider3, provider4, provider5);
    }
}
