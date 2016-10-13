package com.vungle.publisher;

import android.content.ContentValues;
import com.google.android.gms.games.Games;
import com.vungle.log.Logger;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.ad.AdManager.AdAvailabilityEventListener;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.cg.a;
import com.vungle.publisher.db.model.Viewable.Factory;
import com.vungle.publisher.device.data.AppFingerprintManager;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.exception.ExceptionManager;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.reporting.AdReportManager;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class InitializationEventListener extends et {
    @Inject
    AdManager a;
    @Inject
    ScheduledPriorityExecutor b;
    @Inject
    ProtocolHttpGateway c;
    @Inject
    AdReportManager d;
    @Inject
    InitialConfigUpdatedEventListener e;
    @Inject
    GlobalEventListener f;
    @Inject
    SdkState g;
    private final jm i;
    private final AtomicBoolean j;

    class 1 implements Runnable {
        final /* synthetic */ InitializationEventListener a;

        1(InitializationEventListener initializationEventListener) {
            this.a = initializationEventListener;
        }

        public final void run() {
            this.a.e.register();
            this.a.f.register();
            this.a.g.a(true);
            AdReportManager adReportManager = this.a.d;
            if (adReportManager.e.o.getBoolean("IsVgAppInstalled", false)) {
                Logger.v(Logger.REPORT_TAG, "install already reported");
            } else {
                Logger.d(Logger.REPORT_TAG, "reporting install");
                adReportManager.d.c(new hb());
            }
            adReportManager.a();
            AdManager adManager = this.a.a;
            Factory factory = adManager.o;
            ContentValues contentValues = new ContentValues();
            contentValues.put(Games.EXTRA_STATUS, a.aware.toString());
            Logger.d(Logger.DATABASE_TAG, "updated " + factory.a.getWritableDatabase().updateWithOnConflict("viewable", contentValues, "status IN(?,?)", new String[]{a.queued.toString(), a.downloading.toString()}, 3) + " " + a.downloading + " viewables to status " + a.aware);
            adManager.b(true);
            ((AdAvailabilityEventListener) adManager.j.get()).register();
            this.a.c.a();
        }
    }

    @Singleton
    /* compiled from: vungle */
    static class GlobalEventListener extends et {
        @Inject
        AppFingerprintManager a;

        @Inject
        GlobalEventListener() {
        }

        public void onEvent(fy fyVar) {
            this.a.a();
        }

        public void onEvent(al alVar) {
            this.a.a();
        }
    }

    /* compiled from: vungle */
    public final class GlobalEventListener_Factory implements dagger.internal.Factory<GlobalEventListener> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<GlobalEventListener> b;

        static {
            a = !GlobalEventListener_Factory.class.desiredAssertionStatus();
        }

        public GlobalEventListener_Factory(MembersInjector<GlobalEventListener> globalEventListenerMembersInjector) {
            if (a || globalEventListenerMembersInjector != null) {
                this.b = globalEventListenerMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final GlobalEventListener get() {
            return (GlobalEventListener) MembersInjectors.injectMembers(this.b, new GlobalEventListener());
        }

        public static dagger.internal.Factory<GlobalEventListener> create(MembersInjector<GlobalEventListener> globalEventListenerMembersInjector) {
            return new GlobalEventListener_Factory(globalEventListenerMembersInjector);
        }
    }

    /* compiled from: vungle */
    public final class GlobalEventListener_MembersInjector implements MembersInjector<GlobalEventListener> {
        static final /* synthetic */ boolean a;
        private final Provider<EventBus> b;
        private final Provider<AppFingerprintManager> c;

        static {
            a = !GlobalEventListener_MembersInjector.class.desiredAssertionStatus();
        }

        public GlobalEventListener_MembersInjector(Provider<EventBus> eventBusProvider, Provider<AppFingerprintManager> appFingerprintManagerProvider) {
            if (a || eventBusProvider != null) {
                this.b = eventBusProvider;
                if (a || appFingerprintManagerProvider != null) {
                    this.c = appFingerprintManagerProvider;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }

        public static MembersInjector<GlobalEventListener> create(Provider<EventBus> eventBusProvider, Provider<AppFingerprintManager> appFingerprintManagerProvider) {
            return new GlobalEventListener_MembersInjector(eventBusProvider, appFingerprintManagerProvider);
        }

        public final void injectMembers(GlobalEventListener instance) {
            if (instance == null) {
                throw new NullPointerException("Cannot inject members into a null reference");
            }
            eu.a(instance, this.b);
            instance.a = (AppFingerprintManager) this.c.get();
        }

        public static void injectAppFingerprintManager(GlobalEventListener instance, Provider<AppFingerprintManager> appFingerprintManagerProvider) {
            instance.a = (AppFingerprintManager) appFingerprintManagerProvider.get();
        }
    }

    @Singleton
    /* compiled from: vungle */
    static class InitialConfigUpdatedEventListener extends et {
        @Inject
        ExceptionManager a;

        @Inject
        InitialConfigUpdatedEventListener() {
        }

        public void onEvent(fy fyVar) {
            unregister();
            ExceptionManager exceptionManager = this.a;
            try {
                if (exceptionManager.b.b()) {
                    Logger.v(Logger.DATA_TAG, "sdk configured to send logged exceptions");
                    List d = exceptionManager.c.d(10);
                    int size = d.size();
                    if (size > 0) {
                        Logger.d(Logger.DATA_TAG, "sending " + size + " logged exceptions");
                        gu guVar = exceptionManager.a;
                        guVar.e.a(new com.vungle.publisher.gu.1(guVar, d), b.reportExceptions);
                        return;
                    }
                    return;
                }
                Logger.v(Logger.DATA_TAG, "sdk not configured to send logged exceptions");
            } catch (Throwable e) {
                Logger.e(Logger.DATA_TAG, "error sending exceptions. irony?", e);
            }
        }
    }

    /* compiled from: vungle */
    public final class InitialConfigUpdatedEventListener_Factory implements dagger.internal.Factory<InitialConfigUpdatedEventListener> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<InitialConfigUpdatedEventListener> b;

        static {
            a = !InitialConfigUpdatedEventListener_Factory.class.desiredAssertionStatus();
        }

        public InitialConfigUpdatedEventListener_Factory(MembersInjector<InitialConfigUpdatedEventListener> initialConfigUpdatedEventListenerMembersInjector) {
            if (a || initialConfigUpdatedEventListenerMembersInjector != null) {
                this.b = initialConfigUpdatedEventListenerMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final InitialConfigUpdatedEventListener get() {
            return (InitialConfigUpdatedEventListener) MembersInjectors.injectMembers(this.b, new InitialConfigUpdatedEventListener());
        }

        public static dagger.internal.Factory<InitialConfigUpdatedEventListener> create(MembersInjector<InitialConfigUpdatedEventListener> initialConfigUpdatedEventListenerMembersInjector) {
            return new InitialConfigUpdatedEventListener_Factory(initialConfigUpdatedEventListenerMembersInjector);
        }
    }

    /* compiled from: vungle */
    public final class InitialConfigUpdatedEventListener_MembersInjector implements MembersInjector<InitialConfigUpdatedEventListener> {
        static final /* synthetic */ boolean a;
        private final Provider<EventBus> b;
        private final Provider<ExceptionManager> c;

        static {
            a = !InitialConfigUpdatedEventListener_MembersInjector.class.desiredAssertionStatus();
        }

        public InitialConfigUpdatedEventListener_MembersInjector(Provider<EventBus> eventBusProvider, Provider<ExceptionManager> exceptionManagerProvider) {
            if (a || eventBusProvider != null) {
                this.b = eventBusProvider;
                if (a || exceptionManagerProvider != null) {
                    this.c = exceptionManagerProvider;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }

        public static MembersInjector<InitialConfigUpdatedEventListener> create(Provider<EventBus> eventBusProvider, Provider<ExceptionManager> exceptionManagerProvider) {
            return new InitialConfigUpdatedEventListener_MembersInjector(eventBusProvider, exceptionManagerProvider);
        }

        public final void injectMembers(InitialConfigUpdatedEventListener instance) {
            if (instance == null) {
                throw new NullPointerException("Cannot inject members into a null reference");
            }
            eu.a(instance, this.b);
            instance.a = (ExceptionManager) this.c.get();
        }

        public static void injectExceptionManager(InitialConfigUpdatedEventListener instance, Provider<ExceptionManager> exceptionManagerProvider) {
            instance.a = (ExceptionManager) exceptionManagerProvider.get();
        }
    }

    @Inject
    InitializationEventListener() {
        this.i = new jm();
        this.j = new AtomicBoolean(false);
    }

    public void onEvent(er erVar) {
        Logger.d(Logger.DEVICE_TAG, "device ID available");
        if (this.i.a(1) == 3) {
            a();
        }
    }

    public void onEvent(bv bvVar) {
        Logger.d(Logger.DATABASE_TAG, "on database ready");
        if (this.i.a(0) == 3) {
            a();
        }
    }

    private void a() {
        if (this.j.compareAndSet(false, true)) {
            unregister();
            this.b.a(new 1(this), 2000);
        }
    }
}
