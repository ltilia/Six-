package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.StreamingAd;
import com.vungle.publisher.db.model.StreamingAd.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dg implements MembersInjector<StreamingAd> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;

    static {
        a = !dg.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        StreamingAd streamingAd = (StreamingAd) obj;
        if (streamingAd == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        streamingAd.t = (DatabaseHelper) this.b.get();
        streamingAd.u = (Factory) this.c.get();
    }

    private dg(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
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

    public static MembersInjector<StreamingAd> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
        return new dg(provider, provider2);
    }
}
