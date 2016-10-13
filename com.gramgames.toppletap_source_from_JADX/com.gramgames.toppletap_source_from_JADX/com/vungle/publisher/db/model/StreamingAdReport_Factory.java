package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class StreamingAdReport_Factory implements Factory<StreamingAdReport> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<StreamingAdReport> b;

    static {
        a = !StreamingAdReport_Factory.class.desiredAssertionStatus();
    }

    public StreamingAdReport_Factory(MembersInjector<StreamingAdReport> streamingAdReportMembersInjector) {
        if (a || streamingAdReportMembersInjector != null) {
            this.b = streamingAdReportMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final StreamingAdReport get() {
        return (StreamingAdReport) MembersInjectors.injectMembers(this.b, new StreamingAdReport());
    }

    public static Factory<StreamingAdReport> create(MembersInjector<StreamingAdReport> streamingAdReportMembersInjector) {
        return new StreamingAdReport_Factory(streamingAdReportMembersInjector);
    }
}
