package com.vungle.publisher;

import com.vungle.publisher.db.model.EventTrackingHttpLogEntry.Factory;
import com.vungle.publisher.protocol.EventTrackingHttpLogEntryDeleteDelegate;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hf implements MembersInjector<EventTrackingHttpLogEntryDeleteDelegate> {
    static final /* synthetic */ boolean a;
    private final Provider<Factory> b;

    static {
        a = !hf.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        EventTrackingHttpLogEntryDeleteDelegate eventTrackingHttpLogEntryDeleteDelegate = (EventTrackingHttpLogEntryDeleteDelegate) obj;
        if (eventTrackingHttpLogEntryDeleteDelegate == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eventTrackingHttpLogEntryDeleteDelegate.a = (Factory) this.b.get();
    }

    private hf(Provider<Factory> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<EventTrackingHttpLogEntryDeleteDelegate> a(Provider<Factory> provider) {
        return new hf(provider);
    }
}
