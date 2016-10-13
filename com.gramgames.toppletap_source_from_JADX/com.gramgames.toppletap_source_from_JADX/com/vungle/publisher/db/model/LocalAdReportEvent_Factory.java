package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class LocalAdReportEvent_Factory implements Factory<LocalAdReportEvent> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<LocalAdReportEvent> b;

    static {
        a = !LocalAdReportEvent_Factory.class.desiredAssertionStatus();
    }

    public LocalAdReportEvent_Factory(MembersInjector<LocalAdReportEvent> localAdReportEventMembersInjector) {
        if (a || localAdReportEventMembersInjector != null) {
            this.b = localAdReportEventMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final LocalAdReportEvent get() {
        return (LocalAdReportEvent) MembersInjectors.injectMembers(this.b, new LocalAdReportEvent());
    }

    public static Factory<LocalAdReportEvent> create(MembersInjector<LocalAdReportEvent> localAdReportEventMembersInjector) {
        return new LocalAdReportEvent_Factory(localAdReportEventMembersInjector);
    }
}
