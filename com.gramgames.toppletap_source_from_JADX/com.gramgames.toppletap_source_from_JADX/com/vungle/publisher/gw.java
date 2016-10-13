package com.vungle.publisher;

import com.vungle.publisher.net.http.ReportExceptionsHttpRequest;
import com.vungle.publisher.net.http.ReportExceptionsHttpRequest.Factory;
import com.vungle.publisher.protocol.message.ReportExceptions;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gw implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ek> b;
    private final Provider<String> c;
    private final Provider<ReportExceptions.Factory> d;
    private final Provider<ReportExceptionsHttpRequest> e;

    static {
        a = !gw.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.b = (ek) this.b.get();
        factory.c = (String) this.c.get();
        factory.a = (ReportExceptions.Factory) this.d.get();
        factory.d = this.e;
    }

    private gw(Provider<ek> provider, Provider<String> provider2, Provider<ReportExceptions.Factory> provider3, Provider<ReportExceptionsHttpRequest> provider4) {
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

    public static MembersInjector<Factory> a(Provider<ek> provider, Provider<String> provider2, Provider<ReportExceptions.Factory> provider3, Provider<ReportExceptionsHttpRequest> provider4) {
        return new gw(provider, provider2, provider3, provider4);
    }
}
