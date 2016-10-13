package com.vungle.publisher;

import com.vungle.publisher.display.view.VideoFragment.VideoEventListener;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ed implements MembersInjector<VideoEventListener> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;

    static {
        a = !ed.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        VideoEventListener videoEventListener = (VideoEventListener) obj;
        if (videoEventListener == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eu.a(videoEventListener, this.b);
    }

    private ed(Provider<EventBus> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<VideoEventListener> a(Provider<EventBus> provider) {
        return new ed(provider);
    }
}
