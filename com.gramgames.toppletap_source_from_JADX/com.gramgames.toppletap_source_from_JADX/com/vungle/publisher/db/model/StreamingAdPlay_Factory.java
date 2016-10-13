package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class StreamingAdPlay_Factory implements Factory<StreamingAdPlay> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<StreamingAdPlay> b;

    static {
        a = !StreamingAdPlay_Factory.class.desiredAssertionStatus();
    }

    public StreamingAdPlay_Factory(MembersInjector<StreamingAdPlay> streamingAdPlayMembersInjector) {
        if (a || streamingAdPlayMembersInjector != null) {
            this.b = streamingAdPlayMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final StreamingAdPlay get() {
        return (StreamingAdPlay) MembersInjectors.injectMembers(this.b, new StreamingAdPlay());
    }

    public static Factory<StreamingAdPlay> create(MembersInjector<StreamingAdPlay> streamingAdPlayMembersInjector) {
        return new StreamingAdPlay_Factory(streamingAdPlayMembersInjector);
    }
}
