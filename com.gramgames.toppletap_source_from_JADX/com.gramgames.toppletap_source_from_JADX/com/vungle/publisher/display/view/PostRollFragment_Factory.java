package com.vungle.publisher.display.view;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class PostRollFragment_Factory implements Factory<PostRollFragment> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<PostRollFragment> b;

    static {
        a = !PostRollFragment_Factory.class.desiredAssertionStatus();
    }

    public PostRollFragment_Factory(MembersInjector<PostRollFragment> postRollFragmentMembersInjector) {
        if (a || postRollFragmentMembersInjector != null) {
            this.b = postRollFragmentMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final PostRollFragment get() {
        return (PostRollFragment) MembersInjectors.injectMembers(this.b, new PostRollFragment());
    }

    public static Factory<PostRollFragment> create(MembersInjector<PostRollFragment> postRollFragmentMembersInjector) {
        return new PostRollFragment_Factory(postRollFragmentMembersInjector);
    }
}
