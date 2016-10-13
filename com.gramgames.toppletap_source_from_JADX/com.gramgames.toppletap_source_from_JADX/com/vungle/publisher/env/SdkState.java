package com.vungle.publisher.env;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.ads.AdError;
import com.vungle.log.Logger;
import com.vungle.publisher.ak;
import com.vungle.publisher.ao;
import com.vungle.publisher.ap;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.ax;
import com.vungle.publisher.bg;
import com.vungle.publisher.db.DatabaseBroadcastReceiver;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.device.ExternalStorageStateBroadcastReceiver;
import com.vungle.publisher.ek;
import com.vungle.publisher.et;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.inject.annotations.EnvSharedPreferences;
import com.vungle.publisher.je;
import com.vungle.publisher.jg;
import com.vungle.publisher.net.NetworkBroadcastReceiver;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.protocol.ProtocolHttpGateway.5;
import com.vungle.publisher.protocol.ProtocolHttpGateway.6;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class SdkState {
    @Inject
    public Context a;
    @Inject
    public DatabaseBroadcastReceiver b;
    @Inject
    public ek c;
    @Inject
    public EventBus d;
    @Inject
    public ExternalStorageStateBroadcastReceiver e;
    @Inject
    public NetworkBroadcastReceiver f;
    @Inject
    public ScheduledPriorityExecutor g;
    @Inject
    public AdThrottleEndRunnable h;
    @Inject
    public ProtocolHttpGateway i;
    @Inject
    public Factory j;
    @Inject
    public Lazy<EndAdEventListener> k;
    public final AtomicBoolean l;
    public long m;
    public final AtomicBoolean n;
    @Inject
    @EnvSharedPreferences
    public SharedPreferences o;
    private long p;
    private final AtomicInteger q;

    class 1 implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ long b;
        final /* synthetic */ long c;
        final /* synthetic */ SdkState d;

        1(SdkState sdkState, int i, long j, long j2) {
            this.d = sdkState;
            this.a = i;
            this.b = j;
            this.c = j2;
        }

        public final void run() {
            try {
                if (SdkState.a(this.d, this.a)) {
                    ProtocolHttpGateway protocolHttpGateway = this.d.i;
                    protocolHttpGateway.e.a(new 5(protocolHttpGateway, this.b, this.c), b.sessionEnd);
                }
            } catch (Throwable e) {
                this.d.j.a(Logger.AD_TAG, "error sending session end", e);
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class AdThrottleEndRunnable implements Runnable {
        @Inject
        public EventBus a;

        @Inject
        AdThrottleEndRunnable() {
        }

        public void run() {
            this.a.a(new ap());
        }
    }

    /* compiled from: vungle */
    public final class AdThrottleEndRunnable_Factory implements dagger.internal.Factory<AdThrottleEndRunnable> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<AdThrottleEndRunnable> b;

        static {
            a = !AdThrottleEndRunnable_Factory.class.desiredAssertionStatus();
        }

        public AdThrottleEndRunnable_Factory(MembersInjector<AdThrottleEndRunnable> adThrottleEndRunnableMembersInjector) {
            if (a || adThrottleEndRunnableMembersInjector != null) {
                this.b = adThrottleEndRunnableMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final AdThrottleEndRunnable get() {
            return (AdThrottleEndRunnable) MembersInjectors.injectMembers(this.b, new AdThrottleEndRunnable());
        }

        public static dagger.internal.Factory<AdThrottleEndRunnable> create(MembersInjector<AdThrottleEndRunnable> adThrottleEndRunnableMembersInjector) {
            return new AdThrottleEndRunnable_Factory(adThrottleEndRunnableMembersInjector);
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class EndAdEventListener extends et {
        @Inject
        public SdkState a;

        @Inject
        EndAdEventListener() {
        }

        public void onEvent(ax axVar) {
            Logger.d(Logger.EVENT_TAG, "SdkState received end ad event");
            this.a.b(true);
        }
    }

    /* compiled from: vungle */
    public final class EndAdEventListener_Factory implements dagger.internal.Factory<EndAdEventListener> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<EndAdEventListener> b;

        static {
            a = !EndAdEventListener_Factory.class.desiredAssertionStatus();
        }

        public EndAdEventListener_Factory(MembersInjector<EndAdEventListener> endAdEventListenerMembersInjector) {
            if (a || endAdEventListenerMembersInjector != null) {
                this.b = endAdEventListenerMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final EndAdEventListener get() {
            return (EndAdEventListener) MembersInjectors.injectMembers(this.b, new EndAdEventListener());
        }

        public static dagger.internal.Factory<EndAdEventListener> create(MembersInjector<EndAdEventListener> endAdEventListenerMembersInjector) {
            return new EndAdEventListener_Factory(endAdEventListenerMembersInjector);
        }
    }

    static /* synthetic */ boolean a(SdkState sdkState, int i) {
        if (!sdkState.q.compareAndSet(i, 0)) {
            return false;
        }
        sdkState.p = 0;
        return true;
    }

    @Inject
    SdkState() {
        this.l = new AtomicBoolean();
        this.q = new AtomicInteger();
        this.n = new AtomicBoolean();
    }

    public static boolean a() {
        boolean z = !TextUtils.isEmpty(je.a("com.vungle.debug"));
        if (z) {
            Logger.d(Logger.AD_TAG, "in debug mode");
        } else {
            Logger.v(Logger.AD_TAG, "not in debug mode");
        }
        return z;
    }

    public final boolean b() {
        boolean z = true;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long c = c();
        int elapsedRealtime2 = (int) ((SystemClock.elapsedRealtime() - c()) / 1000);
        if (elapsedRealtime2 < 0) {
            Logger.d(Logger.AD_TAG, "negative adDelayElapsedSeconds " + elapsedRealtime2 + ", currentTimestampMillis " + elapsedRealtime + ", lastAdEndMillis " + c);
        } else {
            int e = e();
            if (elapsedRealtime2 < e) {
                z = false;
            }
            if (z) {
                Logger.v(Logger.AD_TAG, elapsedRealtime2 + " / " + e + " ad delay seconds elapsed");
            } else {
                Logger.d(Logger.AD_TAG, elapsedRealtime2 + " / " + e + " ad delay seconds elapsed");
            }
        }
        return z;
    }

    public final long c() {
        long j = this.o.getLong("VgLastViewedTime", 0);
        Logger.v(Logger.AD_TAG, "returning last ad end millis: " + j);
        return j;
    }

    public final void a(long j) {
        this.o.edit().putLong("VgSleepWakeupTime", System.currentTimeMillis() + j).apply();
    }

    public static long d() {
        return SystemClock.elapsedRealtime();
    }

    public final int e() {
        return this.o.getInt("VgAdDelayDuration", 0);
    }

    public final void a(boolean z) {
        Object obj;
        BroadcastReceiver broadcastReceiver = this.e;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addDataScheme("file");
        broadcastReceiver.a.registerReceiver(broadcastReceiver, intentFilter);
        broadcastReceiver = this.f;
        broadcastReceiver.b.registerReceiver(broadcastReceiver, NetworkBroadcastReceiver.a);
        broadcastReceiver = this.b;
        broadcastReceiver.a.registerReceiver(broadcastReceiver, new IntentFilter("com.vungle.publisher.db.DUMP_TABLES"));
        this.c.q();
        this.g.a(b.sessionEnd);
        if (jg.a(this.q)) {
            this.p = System.currentTimeMillis();
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            ProtocolHttpGateway protocolHttpGateway = this.i;
            protocolHttpGateway.e.a(new 6(protocolHttpGateway, this.p), b.sessionStart);
        }
        if (z) {
            this.d.a(new ak());
        }
    }

    public final void b(boolean z) {
        if (this.l.compareAndSet(true, false)) {
            Logger.d(Logger.AD_TAG, "ending playing ad onResume()");
            ((EndAdEventListener) this.k.get()).unregister();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Logger.v(Logger.AD_TAG, "setting last ad end millis: " + elapsedRealtime);
            this.o.edit().putLong("VgLastViewedTime", elapsedRealtime).apply();
            this.m = 0;
            int e = e();
            if (e > 0) {
                this.d.a(new ao());
                this.g.a(this.h, (long) (e * AdError.NETWORK_ERROR_CODE));
            }
            if (!z) {
                this.d.a(new bg(this.m));
            }
        }
    }

    public final long f() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.p;
        int i = this.q.get();
        BroadcastReceiver broadcastReceiver = this.e;
        try {
            broadcastReceiver.a.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            Logger.w(Logger.DEVICE_TAG, "error unregistering external storage state broadcast receiver - not registered");
        }
        broadcastReceiver = this.f;
        try {
            broadcastReceiver.b.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e2) {
            Logger.w(Logger.NETWORK_TAG, "error unregistering network broadcast receiver - not registered");
        }
        broadcastReceiver = this.b;
        try {
            broadcastReceiver.a.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e3) {
            Logger.w(Logger.DATABASE_DUMP_TAG, "error unregistering database broadcast receiver - not registered");
        }
        this.g.a(new 1(this, i, j, currentTimeMillis), b.sessionEndTimer, 10000);
        return currentTimeMillis;
    }
}
