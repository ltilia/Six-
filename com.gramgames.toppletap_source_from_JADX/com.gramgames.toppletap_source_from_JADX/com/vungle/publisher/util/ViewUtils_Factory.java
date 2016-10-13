package com.vungle.publisher.util;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class ViewUtils_Factory implements Factory<ViewUtils> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<ViewUtils> b;

    static {
        a = !ViewUtils_Factory.class.desiredAssertionStatus();
    }

    public ViewUtils_Factory(MembersInjector<ViewUtils> viewUtilsMembersInjector) {
        if (a || viewUtilsMembersInjector != null) {
            this.b = viewUtilsMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final ViewUtils get() {
        return (ViewUtils) MembersInjectors.injectMembers(this.b, new ViewUtils());
    }

    public static Factory<ViewUtils> create(MembersInjector<ViewUtils> viewUtilsMembersInjector) {
        return new ViewUtils_Factory(viewUtilsMembersInjector);
    }
}
