package com.vungle.publisher.ad.prepare;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class PrepareViewableRunnable_Factory implements Factory<PrepareViewableRunnable> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<PrepareViewableRunnable> b;

    static {
        a = !PrepareViewableRunnable_Factory.class.desiredAssertionStatus();
    }

    public PrepareViewableRunnable_Factory(MembersInjector<PrepareViewableRunnable> prepareViewableRunnableMembersInjector) {
        if (a || prepareViewableRunnableMembersInjector != null) {
            this.b = prepareViewableRunnableMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final PrepareViewableRunnable get() {
        return (PrepareViewableRunnable) MembersInjectors.injectMembers(this.b, new PrepareViewableRunnable());
    }

    public static Factory<PrepareViewableRunnable> create(MembersInjector<PrepareViewableRunnable> prepareViewableRunnableMembersInjector) {
        return new PrepareViewableRunnable_Factory(prepareViewableRunnableMembersInjector);
    }
}
