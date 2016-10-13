package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.AdReportExtra.Factory;
import com.vungle.publisher.db.model.StreamingAdPlay;
import com.vungle.publisher.db.model.StreamingAdReport;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class df implements MembersInjector<StreamingAdReport> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;
    private final Provider<StreamingAdReport.Factory> d;
    private final Provider<StreamingAdPlay.Factory> e;

    static {
        a = !df.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        StreamingAdReport streamingAdReport = (StreamingAdReport) obj;
        if (streamingAdReport == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        streamingAdReport.t = (DatabaseHelper) this.b.get();
        streamingAdReport.o = (Factory) this.c.get();
        streamingAdReport.p = (StreamingAdReport.Factory) this.d.get();
        streamingAdReport.q = (StreamingAdPlay.Factory) this.e.get();
    }

    private df(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<StreamingAdReport.Factory> provider3, Provider<StreamingAdPlay.Factory> provider4) {
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

    public static MembersInjector<StreamingAdReport> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<StreamingAdReport.Factory> provider3, Provider<StreamingAdPlay.Factory> provider4) {
        return new df(provider, provider2, provider3, provider4);
    }
}
