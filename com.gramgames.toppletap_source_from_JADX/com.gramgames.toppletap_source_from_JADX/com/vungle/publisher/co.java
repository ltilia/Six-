package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAd.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class co implements MembersInjector<LocalAd> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;

    static {
        a = !co.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        LocalAd localAd = (LocalAd) obj;
        if (localAd == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        localAd.t = (DatabaseHelper) this.b.get();
        localAd.D = (Factory) this.c.get();
    }

    private co(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
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

    public static MembersInjector<LocalAd> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
        return new co(provider, provider2);
    }
}
