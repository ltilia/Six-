package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.AdReportExtra;
import com.vungle.publisher.db.model.StreamingAd;
import com.vungle.publisher.db.model.StreamingAdPlay;
import com.vungle.publisher.db.model.StreamingAdReport;
import com.vungle.publisher.db.model.StreamingAdReport.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dc implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<AdReportExtra.Factory> c;
    private final Provider<StreamingAdPlay.Factory> d;
    private final Provider<StreamingAd.Factory> e;
    private final Provider<StreamingAdReport> f;

    static {
        a = !dc.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.c = (DatabaseHelper) this.b.get();
        factory.a = (AdReportExtra.Factory) this.c.get();
        factory.b = (StreamingAdPlay.Factory) this.d.get();
        factory.d = (StreamingAd.Factory) this.e.get();
        factory.e = this.f;
    }

    private dc(Provider<DatabaseHelper> provider, Provider<AdReportExtra.Factory> provider2, Provider<StreamingAdPlay.Factory> provider3, Provider<StreamingAd.Factory> provider4, Provider<StreamingAdReport> provider5) {
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

    public static MembersInjector<Factory> a(Provider<DatabaseHelper> provider, Provider<AdReportExtra.Factory> provider2, Provider<StreamingAdPlay.Factory> provider3, Provider<StreamingAd.Factory> provider4, Provider<StreamingAdReport> provider5) {
        return new dc(provider, provider2, provider3, provider4, provider5);
    }
}
