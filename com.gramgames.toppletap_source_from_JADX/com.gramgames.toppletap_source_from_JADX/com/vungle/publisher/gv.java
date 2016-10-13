package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.net.http.AppFingerprintHttpTransactionFactory;
import com.vungle.publisher.net.http.ReportExceptionsHttpTransactionFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gv implements MembersInjector<gu> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<Factory> c;
    private final Provider<ScheduledPriorityExecutor> d;
    private final Provider<ReportExceptionsHttpTransactionFactory> e;
    private final Provider<AppFingerprintHttpTransactionFactory> f;

    static {
        a = !gv.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        gu guVar = (gu) obj;
        if (guVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        guVar.c = (Context) this.b.get();
        guVar.d = (Factory) this.c.get();
        guVar.e = (ScheduledPriorityExecutor) this.d.get();
        guVar.a = (ReportExceptionsHttpTransactionFactory) this.e.get();
        guVar.b = (AppFingerprintHttpTransactionFactory) this.f.get();
        guVar.f = (Factory) this.c.get();
    }

    private gv(Provider<Context> provider, Provider<Factory> provider2, Provider<ScheduledPriorityExecutor> provider3, Provider<ReportExceptionsHttpTransactionFactory> provider4, Provider<AppFingerprintHttpTransactionFactory> provider5) {
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

    public static MembersInjector<gu> a(Provider<Context> provider, Provider<Factory> provider2, Provider<ScheduledPriorityExecutor> provider3, Provider<ReportExceptionsHttpTransactionFactory> provider4, Provider<AppFingerprintHttpTransactionFactory> provider5) {
        return new gv(provider, provider2, provider3, provider4, provider5);
    }
}
