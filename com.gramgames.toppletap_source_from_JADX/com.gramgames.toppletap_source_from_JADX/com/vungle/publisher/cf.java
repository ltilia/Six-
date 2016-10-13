package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.EventTracking;
import com.vungle.publisher.db.model.EventTracking.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cf implements MembersInjector<EventTracking> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;

    static {
        a = !cf.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        EventTracking eventTracking = (EventTracking) obj;
        if (eventTracking == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eventTracking.t = (DatabaseHelper) this.b.get();
        eventTracking.d = (Factory) this.c.get();
    }

    private cf(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
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

    public static MembersInjector<EventTracking> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
        return new cf(provider, provider2);
    }
}
