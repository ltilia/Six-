package com.vungle.publisher.protocol;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class EventTrackingHttpLogEntryDeleteDelegate_Factory implements Factory<EventTrackingHttpLogEntryDeleteDelegate> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<EventTrackingHttpLogEntryDeleteDelegate> b;

    static {
        a = !EventTrackingHttpLogEntryDeleteDelegate_Factory.class.desiredAssertionStatus();
    }

    public EventTrackingHttpLogEntryDeleteDelegate_Factory(MembersInjector<EventTrackingHttpLogEntryDeleteDelegate> eventTrackingHttpLogEntryDeleteDelegateMembersInjector) {
        if (a || eventTrackingHttpLogEntryDeleteDelegateMembersInjector != null) {
            this.b = eventTrackingHttpLogEntryDeleteDelegateMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final EventTrackingHttpLogEntryDeleteDelegate get() {
        return (EventTrackingHttpLogEntryDeleteDelegate) MembersInjectors.injectMembers(this.b, new EventTrackingHttpLogEntryDeleteDelegate());
    }

    public static Factory<EventTrackingHttpLogEntryDeleteDelegate> create(MembersInjector<EventTrackingHttpLogEntryDeleteDelegate> eventTrackingHttpLogEntryDeleteDelegateMembersInjector) {
        return new EventTrackingHttpLogEntryDeleteDelegate_Factory(eventTrackingHttpLogEntryDeleteDelegateMembersInjector);
    }
}
