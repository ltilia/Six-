package com.google.android.gms.internal;

import java.util.concurrent.Future;

@zzhb
public abstract class zzim implements zzit<Future> {
    private volatile Thread zzLM;
    private boolean zzLN;
    private final Runnable zzx;

    class 1 implements Runnable {
        final /* synthetic */ zzim zzLO;

        1(zzim com_google_android_gms_internal_zzim) {
            this.zzLO = com_google_android_gms_internal_zzim;
        }

        public final void run() {
            this.zzLO.zzLM = Thread.currentThread();
            this.zzLO.zzbr();
        }
    }

    public zzim() {
        this.zzx = new 1(this);
        this.zzLN = false;
    }

    public zzim(boolean z) {
        this.zzx = new 1(this);
        this.zzLN = z;
    }

    public final void cancel() {
        onStop();
        if (this.zzLM != null) {
            this.zzLM.interrupt();
        }
    }

    public abstract void onStop();

    public abstract void zzbr();

    public /* synthetic */ Object zzgd() {
        return zzhn();
    }

    public final Future zzhn() {
        return this.zzLN ? zziq.zza(1, this.zzx) : zziq.zza(this.zzx);
    }
}
