package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class LocalAdReport_Factory implements Factory<LocalAdReport> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<LocalAdReport> b;

    static {
        a = !LocalAdReport_Factory.class.desiredAssertionStatus();
    }

    public LocalAdReport_Factory(MembersInjector<LocalAdReport> localAdReportMembersInjector) {
        if (a || localAdReportMembersInjector != null) {
            this.b = localAdReportMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final LocalAdReport get() {
        return (LocalAdReport) MembersInjectors.injectMembers(this.b, new LocalAdReport());
    }

    public static Factory<LocalAdReport> create(MembersInjector<LocalAdReport> localAdReportMembersInjector) {
        return new LocalAdReport_Factory(localAdReportMembersInjector);
    }
}
