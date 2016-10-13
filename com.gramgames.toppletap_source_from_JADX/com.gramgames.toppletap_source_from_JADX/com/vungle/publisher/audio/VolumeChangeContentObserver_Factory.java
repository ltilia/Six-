package com.vungle.publisher.audio;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class VolumeChangeContentObserver_Factory implements Factory<VolumeChangeContentObserver> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<VolumeChangeContentObserver> b;

    static {
        a = !VolumeChangeContentObserver_Factory.class.desiredAssertionStatus();
    }

    public VolumeChangeContentObserver_Factory(MembersInjector<VolumeChangeContentObserver> volumeChangeContentObserverMembersInjector) {
        if (a || volumeChangeContentObserverMembersInjector != null) {
            this.b = volumeChangeContentObserverMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final VolumeChangeContentObserver get() {
        return (VolumeChangeContentObserver) MembersInjectors.injectMembers(this.b, new VolumeChangeContentObserver());
    }

    public static Factory<VolumeChangeContentObserver> create(MembersInjector<VolumeChangeContentObserver> volumeChangeContentObserverMembersInjector) {
        return new VolumeChangeContentObserver_Factory(volumeChangeContentObserverMembersInjector);
    }
}
