package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zze<TResult> implements zzf<TResult> {
    private OnSuccessListener<? super TResult> aDp;
    private final Executor avv;
    private final Object zzail;

    class 1 implements Runnable {
        final /* synthetic */ Task aDi;
        final /* synthetic */ zze aDq;

        1(zze com_google_android_gms_tasks_zze, Task task) {
            this.aDq = com_google_android_gms_tasks_zze;
            this.aDi = task;
        }

        public void run() {
            synchronized (this.aDq.zzail) {
                if (this.aDq.aDp != null) {
                    this.aDq.aDp.onSuccess(this.aDi.getResult());
                }
            }
        }
    }

    public zze(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzail = new Object();
        this.avv = executor;
        this.aDp = onSuccessListener;
    }

    public void cancel() {
        synchronized (this.zzail) {
            this.aDp = null;
        }
    }

    public void onComplete(@NonNull Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.zzail) {
                if (this.aDp == null) {
                    return;
                }
                this.avv.execute(new 1(this, task));
            }
        }
    }
}
