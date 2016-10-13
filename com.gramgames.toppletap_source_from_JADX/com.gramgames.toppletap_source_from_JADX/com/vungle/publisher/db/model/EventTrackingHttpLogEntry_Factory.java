package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class EventTrackingHttpLogEntry_Factory implements Factory<EventTrackingHttpLogEntry> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<EventTrackingHttpLogEntry> b;

    static {
        a = !EventTrackingHttpLogEntry_Factory.class.desiredAssertionStatus();
    }

    public EventTrackingHttpLogEntry_Factory(MembersInjector<EventTrackingHttpLogEntry> eventTrackingHttpLogEntryMembersInjector) {
        if (a || eventTrackingHttpLogEntryMembersInjector != null) {
            this.b = eventTrackingHttpLogEntryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final EventTrackingHttpLogEntry get() {
        return (EventTrackingHttpLogEntry) MembersInjectors.injectMembers(this.b, new EventTrackingHttpLogEntry());
    }

    public static Factory<EventTrackingHttpLogEntry> create(MembersInjector<EventTrackingHttpLogEntry> eventTrackingHttpLogEntryMembersInjector) {
        return new EventTrackingHttpLogEntry_Factory(eventTrackingHttpLogEntryMembersInjector);
    }
}
