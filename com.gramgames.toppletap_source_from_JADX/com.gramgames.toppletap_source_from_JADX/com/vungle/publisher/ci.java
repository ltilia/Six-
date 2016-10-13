package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.LocalAdPlay;
import com.vungle.publisher.db.model.LocalAdPlay.Factory;
import com.vungle.publisher.db.model.LocalAdReportEvent;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ci implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<LocalAdPlay> c;
    private final Provider<LocalAdReportEvent.Factory> d;

    static {
        a = !ci.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.c = (DatabaseHelper) this.b.get();
        factory.a = this.c;
        factory.b = (LocalAdReportEvent.Factory) this.d.get();
    }

    private ci(Provider<DatabaseHelper> provider, Provider<LocalAdPlay> provider2, Provider<LocalAdReportEvent.Factory> provider3) {
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

    public static MembersInjector<Factory> a(Provider<DatabaseHelper> provider, Provider<LocalAdPlay> provider2, Provider<LocalAdReportEvent.Factory> provider3) {
        return new ci(provider, provider2, provider3);
    }
}
