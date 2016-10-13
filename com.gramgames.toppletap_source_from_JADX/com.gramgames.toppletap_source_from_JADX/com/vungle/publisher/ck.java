package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.AdReportExtra;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAdPlay;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.LocalAdReport.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ck implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<AdReportExtra.Factory> c;
    private final Provider<LocalAd.Factory> d;
    private final Provider<LocalAdPlay.Factory> e;
    private final Provider<LocalAdReport> f;

    static {
        a = !ck.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.c = (DatabaseHelper) this.b.get();
        factory.a = (AdReportExtra.Factory) this.c.get();
        factory.b = (LocalAd.Factory) this.d.get();
        factory.d = (LocalAdPlay.Factory) this.e.get();
        factory.e = this.f;
    }

    private ck(Provider<DatabaseHelper> provider, Provider<AdReportExtra.Factory> provider2, Provider<LocalAd.Factory> provider3, Provider<LocalAdPlay.Factory> provider4, Provider<LocalAdReport> provider5) {
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

    public static MembersInjector<Factory> a(Provider<DatabaseHelper> provider, Provider<AdReportExtra.Factory> provider2, Provider<LocalAd.Factory> provider3, Provider<LocalAdPlay.Factory> provider4, Provider<LocalAdReport> provider5) {
        return new ck(provider, provider2, provider3, provider4, provider5);
    }
}
