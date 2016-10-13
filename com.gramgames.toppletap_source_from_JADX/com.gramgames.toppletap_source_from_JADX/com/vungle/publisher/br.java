package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.ad.event.VolumeChangeEvent.Factory;
import com.vungle.publisher.audio.VolumeChangeContentObserver;
import com.vungle.publisher.device.AudioHelper;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class br implements MembersInjector<VolumeChangeContentObserver> {
    static final /* synthetic */ boolean a;
    private final Provider<AudioHelper> b;
    private final Provider<Factory> c;
    private final Provider<EventBus> d;
    private final Provider<Context> e;

    static {
        a = !br.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        VolumeChangeContentObserver volumeChangeContentObserver = (VolumeChangeContentObserver) obj;
        if (volumeChangeContentObserver == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        volumeChangeContentObserver.c = (AudioHelper) this.b.get();
        volumeChangeContentObserver.d = (Factory) this.c.get();
        volumeChangeContentObserver.e = (EventBus) this.d.get();
        volumeChangeContentObserver.f = (Context) this.e.get();
    }

    private br(Provider<AudioHelper> provider, Provider<Factory> provider2, Provider<EventBus> provider3, Provider<Context> provider4) {
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

    public static MembersInjector<VolumeChangeContentObserver> a(Provider<AudioHelper> provider, Provider<Factory> provider2, Provider<EventBus> provider3, Provider<Context> provider4) {
        return new br(provider, provider2, provider3, provider4);
    }
}
