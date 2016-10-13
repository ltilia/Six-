package com.google.android.gms.internal;

import android.os.Process;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzr;
import com.mopub.mobileads.ChartboostShared;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@zzhb
public final class zziq {
    private static final ExecutorService zzLU;
    private static final ExecutorService zzLV;

    static class 1 implements Callable<Void> {
        final /* synthetic */ Runnable zzLW;

        1(Runnable runnable) {
            this.zzLW = runnable;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzdt();
        }

        public Void zzdt() {
            this.zzLW.run();
            return null;
        }
    }

    static class 2 implements Callable<Void> {
        final /* synthetic */ Runnable zzLW;

        2(Runnable runnable) {
            this.zzLW = runnable;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzdt();
        }

        public Void zzdt() {
            this.zzLW.run();
            return null;
        }
    }

    static class 3 implements Runnable {
        final /* synthetic */ zzjd zzLX;
        final /* synthetic */ Callable zzLY;

        3(zzjd com_google_android_gms_internal_zzjd, Callable callable) {
            this.zzLX = com_google_android_gms_internal_zzjd;
            this.zzLY = callable;
        }

        public void run() {
            try {
                Process.setThreadPriority(10);
                this.zzLX.zzg(this.zzLY.call());
            } catch (Throwable e) {
                zzr.zzbF().zzb(e, true);
                this.zzLX.cancel(true);
            }
        }
    }

    static class 4 implements Runnable {
        final /* synthetic */ zzjd zzLX;
        final /* synthetic */ Future zzLZ;

        4(zzjd com_google_android_gms_internal_zzjd, Future future) {
            this.zzLX = com_google_android_gms_internal_zzjd;
            this.zzLZ = future;
        }

        public void run() {
            if (this.zzLX.isCancelled()) {
                this.zzLZ.cancel(true);
            }
        }
    }

    static class 5 implements ThreadFactory {
        private final AtomicInteger zzMa;
        final /* synthetic */ String zzMb;

        5(String str) {
            this.zzMb = str;
            this.zzMa = new AtomicInteger(1);
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AdWorker(" + this.zzMb + ") #" + this.zzMa.getAndIncrement());
        }
    }

    static {
        zzLU = Executors.newFixedThreadPool(10, zzaB(ChartboostShared.LOCATION_DEFAULT));
        zzLV = Executors.newFixedThreadPool(5, zzaB("Loader"));
    }

    public static zzjg<Void> zza(int i, Runnable runnable) {
        return i == 1 ? zza(zzLV, new 1(runnable)) : zza(zzLU, new 2(runnable));
    }

    public static zzjg<Void> zza(Runnable runnable) {
        return zza(0, runnable);
    }

    public static <T> zzjg<T> zza(Callable<T> callable) {
        return zza(zzLU, (Callable) callable);
    }

    public static <T> zzjg<T> zza(ExecutorService executorService, Callable<T> callable) {
        Object com_google_android_gms_internal_zzjd = new zzjd();
        try {
            com_google_android_gms_internal_zzjd.zzc(new 4(com_google_android_gms_internal_zzjd, executorService.submit(new 3(com_google_android_gms_internal_zzjd, callable))));
        } catch (Throwable e) {
            zzb.zzd("Thread execution is rejected.", e);
            com_google_android_gms_internal_zzjd.cancel(true);
        }
        return com_google_android_gms_internal_zzjd;
    }

    private static ThreadFactory zzaB(String str) {
        return new 5(str);
    }
}
