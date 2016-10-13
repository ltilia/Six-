package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AdReportExtra_Factory implements Factory<AdReportExtra> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AdReportExtra> b;

    static {
        a = !AdReportExtra_Factory.class.desiredAssertionStatus();
    }

    public AdReportExtra_Factory(MembersInjector<AdReportExtra> adReportExtraMembersInjector) {
        if (a || adReportExtraMembersInjector != null) {
            this.b = adReportExtraMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AdReportExtra get() {
        return (AdReportExtra) MembersInjectors.injectMembers(this.b, new AdReportExtra());
    }

    public static Factory<AdReportExtra> create(MembersInjector<AdReportExtra> adReportExtraMembersInjector) {
        return new AdReportExtra_Factory(adReportExtraMembersInjector);
    }
}
