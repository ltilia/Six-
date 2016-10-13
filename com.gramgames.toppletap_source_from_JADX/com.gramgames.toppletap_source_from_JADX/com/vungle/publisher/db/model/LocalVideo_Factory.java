package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class LocalVideo_Factory implements Factory<LocalVideo> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<LocalVideo> b;

    static {
        a = !LocalVideo_Factory.class.desiredAssertionStatus();
    }

    public LocalVideo_Factory(MembersInjector<LocalVideo> localVideoMembersInjector) {
        if (a || localVideoMembersInjector != null) {
            this.b = localVideoMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final LocalVideo get() {
        return (LocalVideo) MembersInjectors.injectMembers(this.b, new LocalVideo());
    }

    public static Factory<LocalVideo> create(MembersInjector<LocalVideo> localVideoMembersInjector) {
        return new LocalVideo_Factory(localVideoMembersInjector);
    }
}
