package com.vungle.publisher.device;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AudioHelper_Factory implements Factory<AudioHelper> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AudioHelper> b;

    static {
        a = !AudioHelper_Factory.class.desiredAssertionStatus();
    }

    public AudioHelper_Factory(MembersInjector<AudioHelper> audioHelperMembersInjector) {
        if (a || audioHelperMembersInjector != null) {
            this.b = audioHelperMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AudioHelper get() {
        return (AudioHelper) MembersInjectors.injectMembers(this.b, new AudioHelper());
    }

    public static Factory<AudioHelper> create(MembersInjector<AudioHelper> audioHelperMembersInjector) {
        return new AudioHelper_Factory(audioHelperMembersInjector);
    }
}
