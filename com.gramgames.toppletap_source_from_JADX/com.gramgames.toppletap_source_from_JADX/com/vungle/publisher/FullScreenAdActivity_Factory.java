package com.vungle.publisher;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class FullScreenAdActivity_Factory implements Factory<FullScreenAdActivity> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<FullScreenAdActivity> b;

    static {
        a = !FullScreenAdActivity_Factory.class.desiredAssertionStatus();
    }

    public FullScreenAdActivity_Factory(MembersInjector<FullScreenAdActivity> fullScreenAdActivityMembersInjector) {
        if (a || fullScreenAdActivityMembersInjector != null) {
            this.b = fullScreenAdActivityMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final FullScreenAdActivity get() {
        return (FullScreenAdActivity) MembersInjectors.injectMembers(this.b, new FullScreenAdActivity());
    }

    public static Factory<FullScreenAdActivity> create(MembersInjector<FullScreenAdActivity> fullScreenAdActivityMembersInjector) {
        return new FullScreenAdActivity_Factory(fullScreenAdActivityMembersInjector);
    }
}
