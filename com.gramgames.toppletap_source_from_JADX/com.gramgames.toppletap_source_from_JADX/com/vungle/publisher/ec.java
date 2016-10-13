package com.vungle.publisher;

import com.vungle.publisher.display.view.VideoFragment.VideoEventListener;
import com.vungle.publisher.display.view.VideoFragment.VideoEventListener.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ec implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<VideoEventListener> b;

    static {
        a = !ec.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (VideoEventListener) this.b.get();
    }

    private ec(Provider<VideoEventListener> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<VideoEventListener> provider) {
        return new ec(provider);
    }
}
