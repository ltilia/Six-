package com.vungle.publisher;

import android.content.Context;
import android.content.SharedPreferences;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.DatabaseBroadcastReceiver;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.device.ExternalStorageStateBroadcastReceiver;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.env.SdkState.AdThrottleEndRunnable;
import com.vungle.publisher.env.SdkState.EndAdEventListener;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.net.NetworkBroadcastReceiver;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import dagger.MembersInjector;
import dagger.internal.DoubleCheckLazy;
import javax.inject.Provider;

/* compiled from: vungle */
public final class eq implements MembersInjector<SdkState> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;
    private final Provider<DatabaseBroadcastReceiver> c;
    private final Provider<ek> d;
    private final Provider<EventBus> e;
    private final Provider<ExternalStorageStateBroadcastReceiver> f;
    private final Provider<NetworkBroadcastReceiver> g;
    private final Provider<ScheduledPriorityExecutor> h;
    private final Provider<AdThrottleEndRunnable> i;
    private final Provider<ProtocolHttpGateway> j;
    private final Provider<Factory> k;
    private final Provider<EndAdEventListener> l;
    private final Provider<SharedPreferences> m;

    static {
        a = !eq.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        SdkState sdkState = (SdkState) obj;
        if (sdkState == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        sdkState.a = (Context) this.b.get();
        sdkState.b = (DatabaseBroadcastReceiver) this.c.get();
        sdkState.c = (ek) this.d.get();
        sdkState.d = (EventBus) this.e.get();
        sdkState.e = (ExternalStorageStateBroadcastReceiver) this.f.get();
        sdkState.f = (NetworkBroadcastReceiver) this.g.get();
        sdkState.g = (ScheduledPriorityExecutor) this.h.get();
        sdkState.h = (AdThrottleEndRunnable) this.i.get();
        sdkState.i = (ProtocolHttpGateway) this.j.get();
        sdkState.j = (Factory) this.k.get();
        sdkState.k = DoubleCheckLazy.create(this.l);
        sdkState.o = (SharedPreferences) this.m.get();
    }

    private eq(Provider<Context> provider, Provider<DatabaseBroadcastReceiver> provider2, Provider<ek> provider3, Provider<EventBus> provider4, Provider<ExternalStorageStateBroadcastReceiver> provider5, Provider<NetworkBroadcastReceiver> provider6, Provider<ScheduledPriorityExecutor> provider7, Provider<AdThrottleEndRunnable> provider8, Provider<ProtocolHttpGateway> provider9, Provider<Factory> provider10, Provider<EndAdEventListener> provider11, Provider<SharedPreferences> provider12) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
                            if (a || provider6 != null) {
                                this.g = provider6;
                                if (a || provider7 != null) {
                                    this.h = provider7;
                                    if (a || provider8 != null) {
                                        this.i = provider8;
                                        if (a || provider9 != null) {
                                            this.j = provider9;
                                            if (a || provider10 != null) {
                                                this.k = provider10;
                                                if (a || provider11 != null) {
                                                    this.l = provider11;
                                                    if (a || provider12 != null) {
                                                        this.m = provider12;
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
                                    throw new AssertionError();
                                }
                                throw new AssertionError();
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<SdkState> a(Provider<Context> provider, Provider<DatabaseBroadcastReceiver> provider2, Provider<ek> provider3, Provider<EventBus> provider4, Provider<ExternalStorageStateBroadcastReceiver> provider5, Provider<NetworkBroadcastReceiver> provider6, Provider<ScheduledPriorityExecutor> provider7, Provider<AdThrottleEndRunnable> provider8, Provider<ProtocolHttpGateway> provider9, Provider<Factory> provider10, Provider<EndAdEventListener> provider11, Provider<SharedPreferences> provider12) {
        return new eq(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }
}
