package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.EventTrackingHttpLogEntry;
import com.vungle.publisher.db.model.EventTrackingHttpLogEntry.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ce implements MembersInjector<EventTrackingHttpLogEntry> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;

    static {
        a = !ce.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        EventTrackingHttpLogEntry eventTrackingHttpLogEntry = (EventTrackingHttpLogEntry) obj;
        if (eventTrackingHttpLogEntry == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eventTrackingHttpLogEntry.t = (DatabaseHelper) this.b.get();
        eventTrackingHttpLogEntry.h = (Factory) this.c.get();
    }

    private ce(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
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

    public static MembersInjector<EventTrackingHttpLogEntry> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2) {
        return new ce(provider, provider2);
    }
}
