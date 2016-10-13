package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.AdReportExtra;
import com.vungle.publisher.db.model.AdReportExtra.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class by implements MembersInjector<AdReportExtra> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;

    static {
        a = !by.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AdReportExtra adReportExtra = (AdReportExtra) obj;
        if (adReportExtra == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        adReportExtra.t = (DatabaseHelper) this.b.get();
        adReportExtra.d = (Factory) this.c.get();
    }

    private by(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
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

    public static MembersInjector<AdReportExtra> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
        return new by(provider, provider2);
    }
}
