package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.device.ExternalStorageStateBroadcastReceiver;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dl implements MembersInjector<ExternalStorageStateBroadcastReceiver> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<EventBus> c;

    static {
        a = !dl.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ExternalStorageStateBroadcastReceiver externalStorageStateBroadcastReceiver = (ExternalStorageStateBroadcastReceiver) obj;
        if (externalStorageStateBroadcastReceiver == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        externalStorageStateBroadcastReceiver.a = (Context) this.b.get();
        externalStorageStateBroadcastReceiver.b = (EventBus) this.c.get();
    }

    private dl(Provider<Context> provider, Provider<EventBus> provider2) {
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

    public static MembersInjector<ExternalStorageStateBroadcastReceiver> a(Provider<Context> provider, Provider<EventBus> provider2) {
        return new dl(provider, provider2);
    }
}
