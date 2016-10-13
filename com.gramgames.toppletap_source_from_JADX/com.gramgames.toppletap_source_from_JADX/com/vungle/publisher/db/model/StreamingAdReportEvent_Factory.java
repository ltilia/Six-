package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class StreamingAdReportEvent_Factory implements Factory<StreamingAdReportEvent> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<StreamingAdReportEvent> b;

    static {
        a = !StreamingAdReportEvent_Factory.class.desiredAssertionStatus();
    }

    public StreamingAdReportEvent_Factory(MembersInjector<StreamingAdReportEvent> streamingAdReportEventMembersInjector) {
        if (a || streamingAdReportEventMembersInjector != null) {
            this.b = streamingAdReportEventMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final StreamingAdReportEvent get() {
        return (StreamingAdReportEvent) MembersInjectors.injectMembers(this.b, new StreamingAdReportEvent());
    }

    public static Factory<StreamingAdReportEvent> create(MembersInjector<StreamingAdReportEvent> streamingAdReportEventMembersInjector) {
        return new StreamingAdReportEvent_Factory(streamingAdReportEventMembersInjector);
    }
}
