package com.vungle.publisher.reporting;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AdReportManager_Factory implements Factory<AdReportManager> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AdReportManager> b;

    static {
        a = !AdReportManager_Factory.class.desiredAssertionStatus();
    }

    public AdReportManager_Factory(MembersInjector<AdReportManager> adReportManagerMembersInjector) {
        if (a || adReportManagerMembersInjector != null) {
            this.b = adReportManagerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AdReportManager get() {
        return (AdReportManager) MembersInjectors.injectMembers(this.b, new AdReportManager());
    }

    public static Factory<AdReportManager> create(MembersInjector<AdReportManager> adReportManagerMembersInjector) {
        return new AdReportManager_Factory(adReportManagerMembersInjector);
    }
}
