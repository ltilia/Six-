package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.AdReport.Factory;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.StreamingAdReport;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bw implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<LocalAdReport.Factory> c;
    private final Provider<StreamingAdReport.Factory> d;

    static {
        a = !bw.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (DatabaseHelper) this.b.get();
        factory.b = (LocalAdReport.Factory) this.c.get();
        factory.c = (StreamingAdReport.Factory) this.d.get();
    }

    private bw(Provider<DatabaseHelper> provider, Provider<LocalAdReport.Factory> provider2, Provider<StreamingAdReport.Factory> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<DatabaseHelper> provider, Provider<LocalAdReport.Factory> provider2, Provider<StreamingAdReport.Factory> provider3) {
        return new bw(provider, provider2, provider3);
    }
}
