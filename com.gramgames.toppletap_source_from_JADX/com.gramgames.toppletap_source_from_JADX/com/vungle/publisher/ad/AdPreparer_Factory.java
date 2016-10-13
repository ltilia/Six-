package com.vungle.publisher.ad;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AdPreparer_Factory implements Factory<AdPreparer> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AdPreparer> b;

    static {
        a = !AdPreparer_Factory.class.desiredAssertionStatus();
    }

    public AdPreparer_Factory(MembersInjector<AdPreparer> adPreparerMembersInjector) {
        if (a || adPreparerMembersInjector != null) {
            this.b = adPreparerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AdPreparer get() {
        return (AdPreparer) MembersInjectors.injectMembers(this.b, new AdPreparer());
    }

    public static Factory<AdPreparer> create(MembersInjector<AdPreparer> adPreparerMembersInjector) {
        return new AdPreparer_Factory(adPreparerMembersInjector);
    }
}
