package com.vungle.publisher.display.view;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class VideoFragment_Factory implements Factory<VideoFragment> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<VideoFragment> b;

    static {
        a = !VideoFragment_Factory.class.desiredAssertionStatus();
    }

    public VideoFragment_Factory(MembersInjector<VideoFragment> videoFragmentMembersInjector) {
        if (a || videoFragmentMembersInjector != null) {
            this.b = videoFragmentMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final VideoFragment get() {
        return (VideoFragment) MembersInjectors.injectMembers(this.b, new VideoFragment());
    }

    public static Factory<VideoFragment> create(MembersInjector<VideoFragment> videoFragmentMembersInjector) {
        return new VideoFragment_Factory(videoFragmentMembersInjector);
    }
}
