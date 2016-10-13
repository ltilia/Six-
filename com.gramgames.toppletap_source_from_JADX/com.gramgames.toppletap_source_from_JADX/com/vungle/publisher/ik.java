package com.vungle.publisher;

import com.vungle.publisher.protocol.message.ReportExceptions;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ik implements MembersInjector<ReportExceptions> {
    static final /* synthetic */ boolean a;
    private final Provider<ek> b;
    private final Provider<em> c;

    static {
        a = !ik.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ReportExceptions reportExceptions = (ReportExceptions) obj;
        if (reportExceptions == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        reportExceptions.b = (ek) this.b.get();
        reportExceptions.c = (em) this.c.get();
    }

    private ik(Provider<ek> provider, Provider<em> provider2) {
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

    public static MembersInjector<ReportExceptions> a(Provider<ek> provider, Provider<em> provider2) {
        return new ik(provider, provider2);
    }
}
