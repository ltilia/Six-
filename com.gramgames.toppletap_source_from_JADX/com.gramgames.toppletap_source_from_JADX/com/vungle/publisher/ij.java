package com.vungle.publisher;

import com.vungle.publisher.protocol.message.ReportExceptions;
import com.vungle.publisher.protocol.message.ReportExceptions.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ij implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ReportExceptions> b;

    static {
        a = !ij.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = this.b;
    }

    private ij(Provider<ReportExceptions> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<ReportExceptions> provider) {
        return new ij(provider);
    }
}
