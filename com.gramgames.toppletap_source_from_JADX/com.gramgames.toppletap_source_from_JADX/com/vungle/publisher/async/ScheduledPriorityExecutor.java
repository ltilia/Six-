package com.vungle.publisher.async;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.vungle.log.Logger;
import com.vungle.publisher.db.model.LoggedException.Factory;
import dagger.Lazy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.simple.parser.Yytoken;

@Singleton
/* compiled from: vungle */
public class ScheduledPriorityExecutor {
    @Inject
    public Lazy<Factory> a;
    private final a b;
    private final c c;
    private final c d;
    private final c e;
    private final BlockingQueue<Runnable> f;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[b.values().length];
            try {
                a[b.clientEvent.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.externalNetworkRequest.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    class a extends Handler {
        final /* synthetic */ ScheduledPriorityExecutor a;

        class a implements Comparable<a>, Runnable {
            final /* synthetic */ a a;
            private final Runnable b;
            private final long c;
            private final b d;

            public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
                return this.d.compareTo(((a) obj).d);
            }

            a(a aVar, Runnable runnable, b bVar) {
                this(aVar, runnable, bVar, (byte) 0);
            }

            private a(a aVar, Runnable runnable, b bVar, byte b) {
                this.a = aVar;
                this.b = runnable;
                this.c = -1;
                this.d = bVar;
            }

            public final void run() {
                try {
                    this.b.run();
                    try {
                        if (this.c > 0) {
                            this.a.postDelayed(this, this.c);
                        }
                    } catch (Throwable e) {
                        ((Factory) this.a.a.a.get()).a(Logger.ASYNC_TAG, "error rescheduling " + this, e);
                    }
                } catch (Throwable e2) {
                    ((Factory) this.a.a.a.get()).a(Logger.ASYNC_TAG, "error executing " + this, e2);
                    try {
                        if (this.c > 0) {
                            this.a.postDelayed(this, this.c);
                        }
                    } catch (Throwable e22) {
                        ((Factory) this.a.a.a.get()).a(Logger.ASYNC_TAG, "error rescheduling " + this, e22);
                    }
                } catch (Throwable e222) {
                    Throwable th = e222;
                    try {
                        if (this.c > 0) {
                            this.a.postDelayed(this, this.c);
                        }
                    } catch (Throwable e2222) {
                        ((Factory) this.a.a.a.get()).a(Logger.ASYNC_TAG, "error rescheduling " + this, e2222);
                    }
                }
            }

            public final boolean equals(Object object) {
                if (object != null && (object instanceof a)) {
                    if (this.b.equals(((a) object).b)) {
                        return true;
                    }
                }
                return false;
            }

            public final int hashCode() {
                return this.b.hashCode();
            }

            public final String toString() {
                return "{PriorityRunnable:: taskType: " + this.d + ", repeatMillis: " + this.c + "}";
            }
        }

        a(ScheduledPriorityExecutor scheduledPriorityExecutor, Looper looper) {
            this.a = scheduledPriorityExecutor;
            super(looper);
        }

        public final void handleMessage(Message message) {
            Object obj = message.obj;
            if (obj == null || !(obj instanceof a)) {
                Logger.w(Logger.ASYNC_TAG, "unhandled message " + message);
                return;
            }
            c b;
            b a = ((a) obj).d;
            if (a != null) {
                switch (1.a[a.ordinal()]) {
                    case Yytoken.TYPE_LEFT_BRACE /*1*/:
                        b = this.a.c;
                        break;
                }
            }
            b = this.a.e;
            Logger.d(Logger.ASYNC_TAG, "processing " + obj);
            Logger.v(Logger.ASYNC_TAG, b + ", max pool size " + b.getMaximumPoolSize() + ", largest pool size " + b.getLargestPoolSize());
            b.execute((Runnable) obj);
        }
    }

    public enum b {
        deviceId,
        databaseWrite,
        requestStreamingAd,
        reportAd,
        reportInstall,
        requestLocalAd,
        prepareLocalAd,
        prepareLocalViewable,
        downloadLocalAd,
        requestConfig,
        sessionEndTimer,
        sessionEnd,
        sessionStart,
        unfilledAd,
        deleteExpiredAds,
        otherTask,
        externalNetworkRequest,
        clientEvent,
        appFingerprint,
        reportExceptions
    }

    class c extends ThreadPoolExecutor {
        final /* synthetic */ ScheduledPriorityExecutor a;

        class 1 implements ThreadFactory {
            int a;
            final /* synthetic */ ScheduledPriorityExecutor b;
            final /* synthetic */ String c;

            1(ScheduledPriorityExecutor scheduledPriorityExecutor, String str) {
                this.b = scheduledPriorityExecutor;
                this.c = str;
                this.a = 0;
            }

            public final Thread newThread(Runnable runnable) {
                StringBuilder append = new StringBuilder().append(this.c);
                int i = this.a;
                this.a = i + 1;
                String stringBuilder = append.append(i).toString();
                Logger.v(Logger.ASYNC_TAG, "starting " + stringBuilder);
                return new Thread(runnable, stringBuilder);
            }
        }

        c(ScheduledPriorityExecutor scheduledPriorityExecutor, int i, String str) {
            this.a = scheduledPriorityExecutor;
            super(2, 2, 30, TimeUnit.SECONDS, i, new 1(scheduledPriorityExecutor, str));
            allowCoreThreadTimeOut(true);
        }

        protected final void afterExecute(Runnable runnable, Throwable throwable) {
            super.afterExecute(runnable, throwable);
            if (throwable != null) {
                ((Factory) this.a.a.get()).a(Logger.ASYNC_TAG, "error after executing runnable", throwable);
            }
        }
    }

    @Inject
    ScheduledPriorityExecutor() {
        this.f = new PriorityBlockingQueue();
        HandlerThread handlerThread = new HandlerThread("VungleAsyncMasterThread");
        handlerThread.start();
        this.c = new c(this, new LinkedBlockingQueue(), "VungleAsyncClientEventThread-");
        this.c.allowCoreThreadTimeOut(true);
        this.d = new c(this, new LinkedBlockingQueue(), "VungleAsyncExternalNetworkRequestThread-");
        this.d.allowCoreThreadTimeOut(true);
        this.b = new a(this, handlerThread.getLooper());
        this.e = new c(this, this.f, "VungleAsyncMainThread-");
        this.e.allowCoreThreadTimeOut(true);
    }

    public final void a(Runnable runnable, b bVar) {
        this.b.sendMessage(b(runnable, bVar));
    }

    public final void a(Runnable runnable, long j) {
        a(runnable, b.otherTask, j);
    }

    public final void a(Runnable runnable, b bVar, long j) {
        Logger.d(Logger.ASYNC_TAG, "scheduling " + bVar + " delayed " + j + " ms");
        this.b.sendMessageDelayed(b(runnable, bVar), j);
    }

    private Message b(Runnable runnable, b bVar) {
        a aVar = this.b;
        int ordinal = bVar.ordinal();
        aVar.getClass();
        return aVar.obtainMessage(ordinal, new a(aVar, runnable, bVar));
    }

    public final void a(b bVar) {
        this.b.removeMessages(bVar.ordinal());
    }
}
