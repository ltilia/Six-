package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.AdReportExtra.Factory;
import com.vungle.publisher.db.model.LocalAdPlay;
import com.vungle.publisher.db.model.LocalAdReport;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cn implements MembersInjector<LocalAdReport> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;
    private final Provider<LocalAdReport.Factory> d;
    private final Provider<LocalAdPlay.Factory> e;

    static {
        a = !cn.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        LocalAdReport localAdReport = (LocalAdReport) obj;
        if (localAdReport == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        localAdReport.t = (DatabaseHelper) this.b.get();
        localAdReport.o = (Factory) this.c.get();
        localAdReport.q = (LocalAdReport.Factory) this.d.get();
        localAdReport.u = (LocalAdPlay.Factory) this.e.get();
    }

    private cn(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<LocalAdReport.Factory> provider3, Provider<LocalAdPlay.Factory> provider4) {
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

    public static MembersInjector<LocalAdReport> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<LocalAdReport.Factory> provider3, Provider<LocalAdPlay.Factory> provider4) {
        return new cn(provider, provider2, provider3, provider4);
    }
}
