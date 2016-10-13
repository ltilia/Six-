package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.net.NetworkBroadcastReceiver;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fx implements MembersInjector<NetworkBroadcastReceiver> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<fw> c;
    private final Provider<EventBus> d;

    static {
        a = !fx.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        NetworkBroadcastReceiver networkBroadcastReceiver = (NetworkBroadcastReceiver) obj;
        if (networkBroadcastReceiver == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        networkBroadcastReceiver.b = (Context) this.b.get();
        networkBroadcastReceiver.c = (fw) this.c.get();
        networkBroadcastReceiver.d = (EventBus) this.d.get();
    }

    private fx(Provider<Context> provider, Provider<fw> provider2, Provider<EventBus> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<NetworkBroadcastReceiver> a(Provider<Context> provider, Provider<fw> provider2, Provider<EventBus> provider3) {
        return new fx(provider, provider2, provider3);
    }
}
