package com.vungle.publisher.ad.prepare;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class PrepareAdRunnable_Factory implements Factory<PrepareAdRunnable> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<PrepareAdRunnable> b;

    static {
        a = !PrepareAdRunnable_Factory.class.desiredAssertionStatus();
    }

    public PrepareAdRunnable_Factory(MembersInjector<PrepareAdRunnable> prepareAdRunnableMembersInjector) {
        if (a || prepareAdRunnableMembersInjector != null) {
            this.b = prepareAdRunnableMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final PrepareAdRunnable get() {
        return (PrepareAdRunnable) MembersInjectors.injectMembers(this.b, new PrepareAdRunnable());
    }

    public static Factory<PrepareAdRunnable> create(MembersInjector<PrepareAdRunnable> prepareAdRunnableMembersInjector) {
        return new PrepareAdRunnable_Factory(prepareAdRunnableMembersInjector);
    }
}
