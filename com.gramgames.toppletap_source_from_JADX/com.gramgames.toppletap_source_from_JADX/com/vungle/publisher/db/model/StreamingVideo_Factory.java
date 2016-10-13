package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class StreamingVideo_Factory implements Factory<StreamingVideo> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<StreamingVideo> b;

    static {
        a = !StreamingVideo_Factory.class.desiredAssertionStatus();
    }

    public StreamingVideo_Factory(MembersInjector<StreamingVideo> streamingVideoMembersInjector) {
        if (a || streamingVideoMembersInjector != null) {
            this.b = streamingVideoMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final StreamingVideo get() {
        return (StreamingVideo) MembersInjectors.injectMembers(this.b, new StreamingVideo());
    }

    public static Factory<StreamingVideo> create(MembersInjector<StreamingVideo> streamingVideoMembersInjector) {
        return new StreamingVideo_Factory(streamingVideoMembersInjector);
    }
}
