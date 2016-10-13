package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzeg.zzd;
import com.google.android.gms.internal.zzji.zza;
import com.google.android.gms.internal.zzji.zzc;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzba extends zzau {
    private zzd zzsF;
    private boolean zzsG;

    class 1 implements zzc<zzeh> {
        final /* synthetic */ JSONObject zzsH;
        final /* synthetic */ zzba zzsI;

        1(zzba com_google_android_gms_internal_zzba, JSONObject jSONObject) {
            this.zzsI = com_google_android_gms_internal_zzba;
            this.zzsH = jSONObject;
        }

        public void zzd(zzeh com_google_android_gms_internal_zzeh) {
            this.zzsI.zza(this.zzsH);
        }

        public /* synthetic */ void zze(Object obj) {
            zzd((zzeh) obj);
        }
    }

    class 2 implements zza {
        final /* synthetic */ zzba zzsI;

        2(zzba com_google_android_gms_internal_zzba) {
            this.zzsI = com_google_android_gms_internal_zzba;
        }

        public void run() {
        }
    }

    class 3 implements zzc<zzeh> {
        final /* synthetic */ zzba zzsI;

        3(zzba com_google_android_gms_internal_zzba) {
            this.zzsI = com_google_android_gms_internal_zzba;
        }

        public void zzd(zzeh com_google_android_gms_internal_zzeh) {
            this.zzsI.zzsG = true;
            this.zzsI.zzb(com_google_android_gms_internal_zzeh);
            this.zzsI.zzcd();
            this.zzsI.zzh(false);
        }

        public /* synthetic */ void zze(Object obj) {
            zzd((zzeh) obj);
        }
    }

    class 4 implements zza {
        final /* synthetic */ zzba zzsI;

        4(zzba com_google_android_gms_internal_zzba) {
            this.zzsI = com_google_android_gms_internal_zzba;
        }

        public void run() {
            this.zzsI.destroy();
        }
    }

    class 5 implements zzc<zzeh> {
        final /* synthetic */ zzba zzsI;
        final /* synthetic */ JSONObject zzsJ;

        5(zzba com_google_android_gms_internal_zzba, JSONObject jSONObject) {
            this.zzsI = com_google_android_gms_internal_zzba;
            this.zzsJ = jSONObject;
        }

        public void zzd(zzeh com_google_android_gms_internal_zzeh) {
            com_google_android_gms_internal_zzeh.zza("AFMA_updateActiveView", this.zzsJ);
        }

        public /* synthetic */ void zze(Object obj) {
            zzd((zzeh) obj);
        }
    }

    class 6 implements zzc<zzeh> {
        final /* synthetic */ zzba zzsI;

        6(zzba com_google_android_gms_internal_zzba) {
            this.zzsI = com_google_android_gms_internal_zzba;
        }

        public void zzd(zzeh com_google_android_gms_internal_zzeh) {
            this.zzsI.zzc(com_google_android_gms_internal_zzeh);
        }

        public /* synthetic */ void zze(Object obj) {
            zzd((zzeh) obj);
        }
    }

    public zzba(Context context, AdSizeParcel adSizeParcel, zzif com_google_android_gms_internal_zzif, VersionInfoParcel versionInfoParcel, zzbb com_google_android_gms_internal_zzbb, zzeg com_google_android_gms_internal_zzeg) {
        super(context, adSizeParcel, com_google_android_gms_internal_zzif, versionInfoParcel, com_google_android_gms_internal_zzbb);
        this.zzsF = com_google_android_gms_internal_zzeg.zzer();
        try {
            this.zzsF.zza(new 1(this, zzd(com_google_android_gms_internal_zzbb.zzcq().zzco())), new 2(this));
        } catch (JSONException e) {
        } catch (Throwable e2) {
            zzb.zzb("Failure while processing active view data.", e2);
        }
        this.zzsF.zza(new 3(this), new 4(this));
        zzb.zzaI("Tracking ad unit: " + this.zzrZ.zzcu());
    }

    protected void destroy() {
        synchronized (this.zzpV) {
            super.destroy();
            this.zzsF.zza(new 6(this), new zzji.zzb());
            this.zzsF.release();
        }
    }

    protected void zzb(JSONObject jSONObject) {
        this.zzsF.zza(new 5(this, jSONObject), new zzji.zzb());
    }

    protected boolean zzcl() {
        return this.zzsG;
    }
}
