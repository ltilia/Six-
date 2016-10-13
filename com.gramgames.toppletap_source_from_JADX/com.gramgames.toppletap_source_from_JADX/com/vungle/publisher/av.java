package com.vungle.publisher;

import com.vungle.publisher.ad.event.VolumeChangeEvent.Factory;
import com.vungle.publisher.device.AudioHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class av implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<AudioHelper> b;

    static {
        a = !av.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (AudioHelper) this.b.get();
    }

    private av(Provider<AudioHelper> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<AudioHelper> provider) {
        return new av(provider);
    }
}
