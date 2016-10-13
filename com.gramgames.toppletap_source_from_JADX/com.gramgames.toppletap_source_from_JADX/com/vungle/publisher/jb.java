package com.vungle.publisher;

import com.vungle.publisher.reporting.AdReportEventListener;
import com.vungle.publisher.reporting.AdReportEventListener.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jb implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<AdReportEventListener> b;

    static {
        a = !jb.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (AdReportEventListener) this.b.get();
    }

    private jb(Provider<AdReportEventListener> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<AdReportEventListener> provider) {
        return new jb(provider);
    }
}
