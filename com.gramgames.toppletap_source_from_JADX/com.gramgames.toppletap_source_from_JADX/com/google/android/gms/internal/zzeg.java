package com.google.android.gms.internal;

import android.content.Context;
import com.adjust.sdk.Constants;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzx;
import com.mopub.mobileads.CustomEventBannerAdapter;
import java.util.Map;

@zzhb
public class zzeg {
    private final Context mContext;
    private final String zzAY;
    private zzb<zzed> zzAZ;
    private zzb<zzed> zzBa;
    private zze zzBb;
    private int zzBc;
    private final VersionInfoParcel zzpT;
    private final Object zzpV;

    public interface zzb<T> {
        void zze(T t);
    }

    class 1 implements Runnable {
        final /* synthetic */ zze zzBd;
        final /* synthetic */ zzeg zzBe;

        class 1 implements com.google.android.gms.internal.zzed.zza {
            final /* synthetic */ zzed zzBf;
            final /* synthetic */ 1 zzBg;

            class 1 implements Runnable {
                final /* synthetic */ 1 zzBh;

                class 1 implements Runnable {
                    final /* synthetic */ 1 zzBi;

                    1(1 1) {
                        this.zzBi = 1;
                    }

                    public void run() {
                        this.zzBi.zzBh.zzBf.destroy();
                    }
                }

                1(1 1) {
                    this.zzBh = 1;
                }

                public void run() {
                    synchronized (this.zzBh.zzBg.zzBe.zzpV) {
                        if (this.zzBh.zzBg.zzBd.getStatus() == -1 || this.zzBh.zzBg.zzBd.getStatus() == 1) {
                            return;
                        }
                        this.zzBh.zzBg.zzBd.reject();
                        zzir.runOnUiThread(new 1(this));
                        zzin.v("Could not receive loaded message in a timely manner. Rejecting.");
                    }
                }
            }

            1(1 1, zzed com_google_android_gms_internal_zzed) {
                this.zzBg = 1;
                this.zzBf = com_google_android_gms_internal_zzed;
            }

            public void zzeo() {
                zzir.zzMc.postDelayed(new 1(this), (long) zza.zzBn);
            }
        }

        class 2 implements zzdf {
            final /* synthetic */ zzed zzBf;
            final /* synthetic */ 1 zzBg;

            2(1 1, zzed com_google_android_gms_internal_zzed) {
                this.zzBg = 1;
                this.zzBf = com_google_android_gms_internal_zzed;
            }

            public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
                synchronized (this.zzBg.zzBe.zzpV) {
                    if (this.zzBg.zzBd.getStatus() == -1 || this.zzBg.zzBd.getStatus() == 1) {
                        return;
                    }
                    this.zzBg.zzBe.zzBc = 0;
                    this.zzBg.zzBe.zzAZ.zze(this.zzBf);
                    this.zzBg.zzBd.zzh(this.zzBf);
                    this.zzBg.zzBe.zzBb = this.zzBg.zzBd;
                    zzin.v("Successfully loaded JS Engine.");
                }
            }
        }

        class 3 implements zzdf {
            final /* synthetic */ zzed zzBf;
            final /* synthetic */ 1 zzBg;
            final /* synthetic */ zzja zzBj;

            3(1 1, zzed com_google_android_gms_internal_zzed, zzja com_google_android_gms_internal_zzja) {
                this.zzBg = 1;
                this.zzBf = com_google_android_gms_internal_zzed;
                this.zzBj = com_google_android_gms_internal_zzja;
            }

            public void zza(zzjp com_google_android_gms_internal_zzjp, Map<String, String> map) {
                synchronized (this.zzBg.zzBe.zzpV) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzaJ("JS Engine is requesting an update");
                    if (this.zzBg.zzBe.zzBc == 0) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzaJ("Starting reload.");
                        this.zzBg.zzBe.zzBc = 2;
                        this.zzBg.zzBe.zzeq();
                    }
                    this.zzBf.zzb("/requestReload", (zzdf) this.zzBj.get());
                }
            }
        }

        class 4 implements Runnable {
            final /* synthetic */ zzed zzBf;
            final /* synthetic */ 1 zzBg;

            class 1 implements Runnable {
                final /* synthetic */ 4 zzBk;

                1(4 4) {
                    this.zzBk = 4;
                }

                public void run() {
                    this.zzBk.zzBf.destroy();
                }
            }

            4(1 1, zzed com_google_android_gms_internal_zzed) {
                this.zzBg = 1;
                this.zzBf = com_google_android_gms_internal_zzed;
            }

            public void run() {
                synchronized (this.zzBg.zzBe.zzpV) {
                    if (this.zzBg.zzBd.getStatus() == -1 || this.zzBg.zzBd.getStatus() == 1) {
                        return;
                    }
                    this.zzBg.zzBd.reject();
                    zzir.runOnUiThread(new 1(this));
                    zzin.v("Could not receive loaded message in a timely manner. Rejecting.");
                }
            }
        }

        1(zzeg com_google_android_gms_internal_zzeg, zze com_google_android_gms_internal_zzeg_zze) {
            this.zzBe = com_google_android_gms_internal_zzeg;
            this.zzBd = com_google_android_gms_internal_zzeg_zze;
        }

        public void run() {
            zzed zza = this.zzBe.zza(this.zzBe.mContext, this.zzBe.zzpT);
            zza.zza(new 1(this, zza));
            zza.zza("/jsLoaded", new 2(this, zza));
            zzja com_google_android_gms_internal_zzja = new zzja();
            zzdf 3 = new 3(this, zza, com_google_android_gms_internal_zzja);
            com_google_android_gms_internal_zzja.set(3);
            zza.zza("/requestReload", 3);
            if (this.zzBe.zzAY.endsWith(".js")) {
                zza.zzZ(this.zzBe.zzAY);
            } else if (this.zzBe.zzAY.startsWith("<html>")) {
                zza.zzab(this.zzBe.zzAY);
            } else {
                zza.zzaa(this.zzBe.zzAY);
            }
            zzir.zzMc.postDelayed(new 4(this, zza), (long) zza.zzBm);
        }
    }

    class 2 implements com.google.android.gms.internal.zzji.zzc<zzed> {
        final /* synthetic */ zzeg zzBe;
        final /* synthetic */ zze zzBl;

        2(zzeg com_google_android_gms_internal_zzeg, zze com_google_android_gms_internal_zzeg_zze) {
            this.zzBe = com_google_android_gms_internal_zzeg;
            this.zzBl = com_google_android_gms_internal_zzeg_zze;
        }

        public void zza(zzed com_google_android_gms_internal_zzed) {
            synchronized (this.zzBe.zzpV) {
                this.zzBe.zzBc = 0;
                if (!(this.zzBe.zzBb == null || this.zzBl == this.zzBe.zzBb)) {
                    zzin.v("New JS engine is loaded, marking previous one as destroyable.");
                    this.zzBe.zzBb.zzeu();
                }
                this.zzBe.zzBb = this.zzBl;
            }
        }

        public /* synthetic */ void zze(Object obj) {
            zza((zzed) obj);
        }
    }

    class 3 implements com.google.android.gms.internal.zzji.zza {
        final /* synthetic */ zzeg zzBe;
        final /* synthetic */ zze zzBl;

        3(zzeg com_google_android_gms_internal_zzeg, zze com_google_android_gms_internal_zzeg_zze) {
            this.zzBe = com_google_android_gms_internal_zzeg;
            this.zzBl = com_google_android_gms_internal_zzeg_zze;
        }

        public void run() {
            synchronized (this.zzBe.zzpV) {
                this.zzBe.zzBc = 1;
                zzin.v("Failed loading new engine. Marking new engine destroyable.");
                this.zzBl.zzeu();
            }
        }
    }

    static class zza {
        static int zzBm;
        static int zzBn;

        static {
            zzBm = Constants.SOCKET_TIMEOUT;
            zzBn = CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY;
        }
    }

    public static class zzc<T> implements zzb<T> {
        public void zze(T t) {
        }
    }

    public static class zzd extends zzjj<zzeh> {
        private final zze zzBo;
        private boolean zzBp;
        private final Object zzpV;

        class 1 implements com.google.android.gms.internal.zzji.zzc<zzeh> {
            final /* synthetic */ zzd zzBq;

            1(zzd com_google_android_gms_internal_zzeg_zzd) {
                this.zzBq = com_google_android_gms_internal_zzeg_zzd;
            }

            public void zzd(zzeh com_google_android_gms_internal_zzeh) {
                zzin.v("Ending javascript session.");
                ((zzei) com_google_android_gms_internal_zzeh).zzew();
            }

            public /* synthetic */ void zze(Object obj) {
                zzd((zzeh) obj);
            }
        }

        class 2 implements com.google.android.gms.internal.zzji.zzc<zzeh> {
            final /* synthetic */ zzd zzBq;

            2(zzd com_google_android_gms_internal_zzeg_zzd) {
                this.zzBq = com_google_android_gms_internal_zzeg_zzd;
            }

            public void zzd(zzeh com_google_android_gms_internal_zzeh) {
                zzin.v("Releasing engine reference.");
                this.zzBq.zzBo.zzet();
            }

            public /* synthetic */ void zze(Object obj) {
                zzd((zzeh) obj);
            }
        }

        class 3 implements com.google.android.gms.internal.zzji.zza {
            final /* synthetic */ zzd zzBq;

            3(zzd com_google_android_gms_internal_zzeg_zzd) {
                this.zzBq = com_google_android_gms_internal_zzeg_zzd;
            }

            public void run() {
                this.zzBq.zzBo.zzet();
            }
        }

        public zzd(zze com_google_android_gms_internal_zzeg_zze) {
            this.zzpV = new Object();
            this.zzBo = com_google_android_gms_internal_zzeg_zze;
        }

        public void release() {
            synchronized (this.zzpV) {
                if (this.zzBp) {
                    return;
                }
                this.zzBp = true;
                zza(new 1(this), new com.google.android.gms.internal.zzji.zzb());
                zza(new 2(this), new 3(this));
            }
        }
    }

    public static class zze extends zzjj<zzed> {
        private zzb<zzed> zzBa;
        private boolean zzBr;
        private int zzBs;
        private final Object zzpV;

        class 1 implements com.google.android.gms.internal.zzji.zzc<zzed> {
            final /* synthetic */ zzd zzBt;
            final /* synthetic */ zze zzBu;

            1(zze com_google_android_gms_internal_zzeg_zze, zzd com_google_android_gms_internal_zzeg_zzd) {
                this.zzBu = com_google_android_gms_internal_zzeg_zze;
                this.zzBt = com_google_android_gms_internal_zzeg_zzd;
            }

            public void zza(zzed com_google_android_gms_internal_zzed) {
                zzin.v("Getting a new session for JS Engine.");
                this.zzBt.zzh(com_google_android_gms_internal_zzed.zzen());
            }

            public /* synthetic */ void zze(Object obj) {
                zza((zzed) obj);
            }
        }

        class 2 implements com.google.android.gms.internal.zzji.zza {
            final /* synthetic */ zzd zzBt;
            final /* synthetic */ zze zzBu;

            2(zze com_google_android_gms_internal_zzeg_zze, zzd com_google_android_gms_internal_zzeg_zzd) {
                this.zzBu = com_google_android_gms_internal_zzeg_zze;
                this.zzBt = com_google_android_gms_internal_zzeg_zzd;
            }

            public void run() {
                zzin.v("Rejecting reference for JS Engine.");
                this.zzBt.reject();
            }
        }

        class 3 implements com.google.android.gms.internal.zzji.zzc<zzed> {
            final /* synthetic */ zze zzBu;

            class 1 implements Runnable {
                final /* synthetic */ zzed zzBv;
                final /* synthetic */ 3 zzBw;

                1(3 3, zzed com_google_android_gms_internal_zzed) {
                    this.zzBw = 3;
                    this.zzBv = com_google_android_gms_internal_zzed;
                }

                public void run() {
                    this.zzBw.zzBu.zzBa.zze(this.zzBv);
                    this.zzBv.destroy();
                }
            }

            3(zze com_google_android_gms_internal_zzeg_zze) {
                this.zzBu = com_google_android_gms_internal_zzeg_zze;
            }

            public void zza(zzed com_google_android_gms_internal_zzed) {
                zzir.runOnUiThread(new 1(this, com_google_android_gms_internal_zzed));
            }

            public /* synthetic */ void zze(Object obj) {
                zza((zzed) obj);
            }
        }

        public zze(zzb<zzed> com_google_android_gms_internal_zzeg_zzb_com_google_android_gms_internal_zzed) {
            this.zzpV = new Object();
            this.zzBa = com_google_android_gms_internal_zzeg_zzb_com_google_android_gms_internal_zzed;
            this.zzBr = false;
            this.zzBs = 0;
        }

        public zzd zzes() {
            zzd com_google_android_gms_internal_zzeg_zzd = new zzd(this);
            synchronized (this.zzpV) {
                zza(new 1(this, com_google_android_gms_internal_zzeg_zzd), new 2(this, com_google_android_gms_internal_zzeg_zzd));
                zzx.zzab(this.zzBs >= 0);
                this.zzBs++;
            }
            return com_google_android_gms_internal_zzeg_zzd;
        }

        protected void zzet() {
            boolean z = true;
            synchronized (this.zzpV) {
                if (this.zzBs < 1) {
                    z = false;
                }
                zzx.zzab(z);
                zzin.v("Releasing 1 reference for JS Engine");
                this.zzBs--;
                zzev();
            }
        }

        public void zzeu() {
            boolean z = true;
            synchronized (this.zzpV) {
                if (this.zzBs < 0) {
                    z = false;
                }
                zzx.zzab(z);
                zzin.v("Releasing root reference. JS Engine will be destroyed once other references are released.");
                this.zzBr = true;
                zzev();
            }
        }

        protected void zzev() {
            synchronized (this.zzpV) {
                zzx.zzab(this.zzBs >= 0);
                if (this.zzBr && this.zzBs == 0) {
                    zzin.v("No reference is left (including root). Cleaning up engine.");
                    zza(new 3(this), new com.google.android.gms.internal.zzji.zzb());
                } else {
                    zzin.v("There are still references to the engine. Not destroying.");
                }
            }
        }
    }

    public zzeg(Context context, VersionInfoParcel versionInfoParcel, String str) {
        this.zzpV = new Object();
        this.zzBc = 1;
        this.zzAY = str;
        this.mContext = context.getApplicationContext();
        this.zzpT = versionInfoParcel;
        this.zzAZ = new zzc();
        this.zzBa = new zzc();
    }

    public zzeg(Context context, VersionInfoParcel versionInfoParcel, String str, zzb<zzed> com_google_android_gms_internal_zzeg_zzb_com_google_android_gms_internal_zzed, zzb<zzed> com_google_android_gms_internal_zzeg_zzb_com_google_android_gms_internal_zzed2) {
        this(context, versionInfoParcel, str);
        this.zzAZ = com_google_android_gms_internal_zzeg_zzb_com_google_android_gms_internal_zzed;
        this.zzBa = com_google_android_gms_internal_zzeg_zzb_com_google_android_gms_internal_zzed2;
    }

    private zze zzep() {
        zze com_google_android_gms_internal_zzeg_zze = new zze(this.zzBa);
        zzir.runOnUiThread(new 1(this, com_google_android_gms_internal_zzeg_zze));
        return com_google_android_gms_internal_zzeg_zze;
    }

    protected zzed zza(Context context, VersionInfoParcel versionInfoParcel) {
        return new zzef(context, versionInfoParcel, null);
    }

    protected zze zzeq() {
        zze zzep = zzep();
        zzep.zza(new 2(this, zzep), new 3(this, zzep));
        return zzep;
    }

    public zzd zzer() {
        zzd zzes;
        synchronized (this.zzpV) {
            if (this.zzBb == null || this.zzBb.getStatus() == -1) {
                this.zzBc = 2;
                this.zzBb = zzeq();
                zzes = this.zzBb.zzes();
            } else if (this.zzBc == 0) {
                zzes = this.zzBb.zzes();
            } else if (this.zzBc == 1) {
                this.zzBc = 2;
                zzeq();
                zzes = this.zzBb.zzes();
            } else if (this.zzBc == 2) {
                zzes = this.zzBb.zzes();
            } else {
                zzes = this.zzBb.zzes();
            }
        }
        return zzes;
    }
}
