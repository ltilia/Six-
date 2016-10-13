package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class EventTracking_Factory implements Factory<EventTracking> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<EventTracking> b;

    static {
        a = !EventTracking_Factory.class.desiredAssertionStatus();
    }

    public EventTracking_Factory(MembersInjector<EventTracking> eventTrackingMembersInjector) {
        if (a || eventTrackingMembersInjector != null) {
            this.b = eventTrackingMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final EventTracking get() {
        return (EventTracking) MembersInjectors.injectMembers(this.b, new EventTracking());
    }

    public static Factory<EventTracking> create(MembersInjector<EventTracking> eventTrackingMembersInjector) {
        return new EventTracking_Factory(eventTrackingMembersInjector);
    }
}
