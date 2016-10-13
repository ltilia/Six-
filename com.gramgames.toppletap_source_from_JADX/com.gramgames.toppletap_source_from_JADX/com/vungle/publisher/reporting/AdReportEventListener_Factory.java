package com.vungle.publisher.reporting;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AdReportEventListener_Factory implements Factory<AdReportEventListener> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AdReportEventListener> b;

    static {
        a = !AdReportEventListener_Factory.class.desiredAssertionStatus();
    }

    public AdReportEventListener_Factory(MembersInjector<AdReportEventListener> adReportEventListenerMembersInjector) {
        if (a || adReportEventListenerMembersInjector != null) {
            this.b = adReportEventListenerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AdReportEventListener get() {
        return (AdReportEventListener) MembersInjectors.injectMembers(this.b, new AdReportEventListener());
    }

    public static Factory<AdReportEventListener> create(MembersInjector<AdReportEventListener> adReportEventListenerMembersInjector) {
        return new AdReportEventListener_Factory(adReportEventListenerMembersInjector);
    }
}
