package com.vungle.publisher;

import com.vungle.publisher.net.http.ReportExceptionsHttpResponseHandler;
import com.vungle.publisher.net.http.ReportExceptionsHttpResponseHandler.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gx implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ReportExceptionsHttpResponseHandler> b;

    static {
        a = !gx.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = this.b;
    }

    private gx(Provider<ReportExceptionsHttpResponseHandler> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<ReportExceptionsHttpResponseHandler> provider) {
        return new gx(provider);
    }
}
