package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.LocalAdPlay;
import com.vungle.publisher.db.model.LocalAdPlay.Factory;
import com.vungle.publisher.db.model.LocalAdReportEvent;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cj implements MembersInjector<LocalAdPlay> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;
    private final Provider<LocalAdReportEvent.Factory> d;

    static {
        a = !cj.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        LocalAdPlay localAdPlay = (LocalAdPlay) obj;
        if (localAdPlay == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        localAdPlay.t = (DatabaseHelper) this.b.get();
        localAdPlay.e = (Factory) this.c.get();
        localAdPlay.f = (LocalAdReportEvent.Factory) this.d.get();
    }

    private cj(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<LocalAdReportEvent.Factory> provider3) {
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

    public static MembersInjector<LocalAdPlay> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<LocalAdReportEvent.Factory> provider3) {
        return new cj(provider, provider2, provider3);
    }
}
