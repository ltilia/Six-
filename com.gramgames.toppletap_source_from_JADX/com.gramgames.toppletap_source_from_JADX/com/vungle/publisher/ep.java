package com.vungle.publisher;

import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.env.SdkState.EndAdEventListener;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ep implements MembersInjector<EndAdEventListener> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<SdkState> c;

    static {
        a = !ep.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        EndAdEventListener endAdEventListener = (EndAdEventListener) obj;
        if (endAdEventListener == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eu.a(endAdEventListener, this.b);
        endAdEventListener.a = (SdkState) this.c.get();
    }

    private ep(Provider<EventBus> provider, Provider<SdkState> provider2) {
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

    public static MembersInjector<EndAdEventListener> a(Provider<EventBus> provider, Provider<SdkState> provider2) {
        return new ep(provider, provider2);
    }
}
