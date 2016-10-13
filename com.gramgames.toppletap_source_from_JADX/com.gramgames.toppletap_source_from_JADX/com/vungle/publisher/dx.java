package com.vungle.publisher;

import com.vungle.publisher.display.view.PostRollFragment;
import com.vungle.publisher.display.view.PostRollFragment.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dx implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<PostRollFragment> b;

    static {
        a = !dx.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = this.b;
    }

    private dx(Provider<PostRollFragment> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<PostRollFragment> provider) {
        return new dx(provider);
    }
}
