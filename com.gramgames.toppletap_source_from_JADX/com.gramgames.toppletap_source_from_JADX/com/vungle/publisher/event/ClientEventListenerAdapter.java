package com.vungle.publisher.event;

import com.google.android.exoplayer.DefaultLoadControl;
import com.vungle.log.Logger;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.al;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.ay;
import com.vungle.publisher.az;
import com.vungle.publisher.bb;
import com.vungle.publisher.bc;
import com.vungle.publisher.bd;
import com.vungle.publisher.be;
import com.vungle.publisher.bg;
import com.vungle.publisher.bj;
import com.vungle.publisher.et;
import com.vungle.publisher.j;
import com.vungle.publisher.t;
import com.vungle.publisher.u;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ClientEventListenerAdapter extends et {
    public EventListener a;
    @Inject
    public ScheduledPriorityExecutor b;
    @Inject
    public AdManager c;
    private int d;
    private int e;
    private AtomicBoolean f;

    class 1 implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ ClientEventListenerAdapter b;

        1(ClientEventListenerAdapter clientEventListenerAdapter, boolean z) {
            this.b = clientEventListenerAdapter;
            this.a = z;
        }

        public final void run() {
            this.b.a.onAdPlayableChanged(this.a);
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ ClientEventListenerAdapter a;

        2(ClientEventListenerAdapter clientEventListenerAdapter) {
            this.a = clientEventListenerAdapter;
        }

        public final void run() {
            this.a.a.onAdStart();
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ ClientEventListenerAdapter d;

        3(ClientEventListenerAdapter clientEventListenerAdapter, boolean z, int i, int i2) {
            this.d = clientEventListenerAdapter;
            this.a = z;
            this.b = i;
            this.c = i2;
        }

        public final void run() {
            this.d.a.onVideoView(this.a, this.b, this.c);
        }
    }

    class 4 implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ ClientEventListenerAdapter b;

        4(ClientEventListenerAdapter clientEventListenerAdapter, boolean z) {
            this.b = clientEventListenerAdapter;
            this.a = z;
        }

        public final void run() {
            this.b.a.onAdEnd(this.a);
        }
    }

    class 5 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ ClientEventListenerAdapter b;

        5(ClientEventListenerAdapter clientEventListenerAdapter, String str) {
            this.b = clientEventListenerAdapter;
            this.a = str;
        }

        public final void run() {
            this.b.a.onAdUnavailable(this.a);
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Provider<ClientEventListenerAdapter> a;

        @Inject
        Factory() {
        }
    }

    /* compiled from: vungle */
    public final class Factory_Factory implements dagger.internal.Factory<Factory> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<Factory> b;

        static {
            a = !Factory_Factory.class.desiredAssertionStatus();
        }

        public Factory_Factory(MembersInjector<Factory> factoryMembersInjector) {
            if (a || factoryMembersInjector != null) {
                this.b = factoryMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final Factory get() {
            return (Factory) MembersInjectors.injectMembers(this.b, new Factory());
        }

        public static dagger.internal.Factory<Factory> create(MembersInjector<Factory> factoryMembersInjector) {
            return new Factory_Factory(factoryMembersInjector);
        }
    }

    @Inject
    ClientEventListenerAdapter() {
        this.f = new AtomicBoolean();
    }

    public void onEvent(j jVar) {
        boolean a = this.c.a();
        if (this.f.compareAndSet(!a, a)) {
            a(new 1(this, a));
        }
    }

    public void onEvent(al alVar) {
        Logger.d(Logger.EVENT_TAG, "onAdStart() callback");
        this.d = 0;
        this.e = 0;
        a(new 2(this));
    }

    public void onEvent(bj playAdSuccessEvent) {
        a(playAdSuccessEvent.a);
    }

    public void onEvent(ay errorEndPlayEvent) {
        if (errorEndPlayEvent instanceof bg) {
            Logger.d(Logger.EVENT_TAG, "onAdEnd() - activity destroyed");
        } else {
            Logger.d(Logger.EVENT_TAG, "onAdEnd() - error during playback");
        }
        a(false);
    }

    private void a(boolean z) {
        int i = this.d;
        int i2 = this.e;
        boolean z2 = ((float) i) / ((float) i2) > DefaultLoadControl.DEFAULT_HIGH_BUFFER_LOAD;
        Logger.d(Logger.EVENT_TAG, "onVideoEnd(" + z2 + ", " + i + ", " + i2 + ") callback");
        a(new 3(this, z2, i, i2));
        Logger.d(Logger.EVENT_TAG, "onAdEnd(" + z + ") callback");
        a(new 4(this, z));
    }

    public void onEvent(bb bbVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(error) callback");
        a("Error launching ad");
    }

    public void onEvent(az azVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(already playing) callback");
        a("Ad already playing");
    }

    public void onEvent(bc bcVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(not initialized) callback");
        a("Vungle Publisher SDK was not successfully initialized - please check the logs");
    }

    public void onEvent(bd throttledCancelPlayEvent) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(throttled) callback");
        a("Only " + throttledCancelPlayEvent.a + " of minimum " + throttledCancelPlayEvent.b + " seconds elapsed between ads");
    }

    public void onEvent(be beVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(unavailable) callback");
        a("No cached or streaming ad available");
    }

    public void onEvent(t durationPlayVideoEvent) {
        this.e = durationPlayVideoEvent.a;
    }

    public void onEvent(u endPlayVideoEvent) {
        int i = endPlayVideoEvent.a;
        if (i > this.d) {
            Logger.d(Logger.EVENT_TAG, "new watched millis " + i);
            this.d = i;
            return;
        }
        Logger.d(Logger.EVENT_TAG, "shorter watched millis " + i);
    }

    private void a(String str) {
        a(new 5(this, str));
    }

    private void a(Runnable runnable) {
        this.b.a(runnable, b.clientEvent);
    }
}
