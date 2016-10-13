package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.device.AudioHelper;
import com.vungle.publisher.display.view.MuteButton.Factory;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.util.ViewUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dv implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<ViewUtils> c;
    private final Provider<AudioHelper> d;
    private final Provider<EventBus> e;

    static {
        a = !dv.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (Context) this.b.get();
        factory.b = (ViewUtils) this.c.get();
        factory.c = (AudioHelper) this.d.get();
        factory.d = (EventBus) this.e.get();
    }

    private dv(Provider<Context> provider, Provider<ViewUtils> provider2, Provider<AudioHelper> provider3, Provider<EventBus> provider4) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<Context> provider, Provider<ViewUtils> provider2, Provider<AudioHelper> provider3, Provider<EventBus> provider4) {
        return new dv(provider, provider2, provider3, provider4);
    }
}
