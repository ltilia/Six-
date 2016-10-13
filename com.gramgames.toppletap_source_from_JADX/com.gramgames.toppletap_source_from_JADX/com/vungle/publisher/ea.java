package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.display.view.DisplayUtils;
import com.vungle.publisher.display.view.ProgressBar.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ea implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<DisplayUtils> c;

    static {
        a = !ea.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (Context) this.b.get();
        factory.b = (DisplayUtils) this.c.get();
    }

    private ea(Provider<Context> provider, Provider<DisplayUtils> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<Context> provider, Provider<DisplayUtils> provider2) {
        return new ea(provider, provider2);
    }
}
