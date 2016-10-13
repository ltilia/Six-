package com.vungle.publisher.display.view;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class DisplayUtils_Factory implements Factory<DisplayUtils> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<DisplayUtils> b;

    static {
        a = !DisplayUtils_Factory.class.desiredAssertionStatus();
    }

    public DisplayUtils_Factory(MembersInjector<DisplayUtils> displayUtilsMembersInjector) {
        if (a || displayUtilsMembersInjector != null) {
            this.b = displayUtilsMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final DisplayUtils get() {
        return (DisplayUtils) MembersInjectors.injectMembers(this.b, new DisplayUtils());
    }

    public static Factory<DisplayUtils> create(MembersInjector<DisplayUtils> displayUtilsMembersInjector) {
        return new DisplayUtils_Factory(displayUtilsMembersInjector);
    }
}
