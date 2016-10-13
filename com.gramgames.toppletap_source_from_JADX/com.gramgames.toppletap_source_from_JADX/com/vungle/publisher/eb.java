package com.vungle.publisher;

import com.vungle.publisher.display.view.VideoFragment;
import com.vungle.publisher.display.view.VideoFragment.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class eb implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<VideoFragment> b;

    static {
        a = !eb.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = this.b;
    }

    private eb(Provider<VideoFragment> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<VideoFragment> provider) {
        return new eb(provider);
    }
}
