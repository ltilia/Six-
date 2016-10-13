package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.LocalAdPlay;
import com.vungle.publisher.db.model.LocalAdReportEvent;
import com.vungle.publisher.db.model.LocalAdReportEvent.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cm implements MembersInjector<LocalAdReportEvent> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;
    private final Provider<LocalAdPlay.Factory> d;

    static {
        a = !cm.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        LocalAdReportEvent localAdReportEvent = (LocalAdReportEvent) obj;
        if (localAdReportEvent == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        localAdReportEvent.t = (DatabaseHelper) this.b.get();
        localAdReportEvent.e = (Factory) this.c.get();
        localAdReportEvent.f = (LocalAdPlay.Factory) this.d.get();
    }

    private cm(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<LocalAdPlay.Factory> provider3) {
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

    public static MembersInjector<LocalAdReportEvent> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<LocalAdPlay.Factory> provider3) {
        return new cm(provider, provider2, provider3);
    }
}
