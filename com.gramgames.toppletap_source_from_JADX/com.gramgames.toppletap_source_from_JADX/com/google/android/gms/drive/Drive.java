package com.google.android.gms.drive;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.drive.internal.zzaa;
import com.google.android.gms.drive.internal.zzac;
import com.google.android.gms.drive.internal.zzs;
import com.google.android.gms.drive.internal.zzu;
import com.google.android.gms.drive.internal.zzx;

public final class Drive {
    public static final Api<NoOptions> API;
    public static final DriveApi DriveApi;
    public static final DrivePreferencesApi DrivePreferencesApi;
    public static final Scope SCOPE_APPFOLDER;
    public static final Scope SCOPE_FILE;
    public static final zzc<zzu> zzUI;
    public static final Scope zzaoE;
    public static final Scope zzaoF;
    public static final Api<zzb> zzaoG;
    public static final zzd zzaoH;
    public static final zzf zzaoI;

    public static abstract class zza<O extends ApiOptions> extends com.google.android.gms.common.api.Api.zza<zzu, O> {
        protected abstract Bundle zza(O o);

        public zzu zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, O o, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzu(context, looper, com_google_android_gms_common_internal_zzf, connectionCallbacks, onConnectionFailedListener, zza(o));
        }
    }

    static class 1 extends zza<NoOptions> {
        1() {
        }

        protected Bundle zza(NoOptions noOptions) {
            return new Bundle();
        }
    }

    static class 2 extends zza<zzb> {
        2() {
        }

        protected Bundle zza(zzb com_google_android_gms_drive_Drive_zzb) {
            return com_google_android_gms_drive_Drive_zzb == null ? new Bundle() : com_google_android_gms_drive_Drive_zzb.zzsw();
        }
    }

    public static class zzb implements Optional {
        private final Bundle zzUH;

        public Bundle zzsw() {
            return this.zzUH;
        }
    }

    static {
        zzUI = new zzc();
        SCOPE_FILE = new Scope(Scopes.DRIVE_FILE);
        SCOPE_APPFOLDER = new Scope(Scopes.DRIVE_APPFOLDER);
        zzaoE = new Scope("https://www.googleapis.com/auth/drive");
        zzaoF = new Scope("https://www.googleapis.com/auth/drive.apps");
        API = new Api("Drive.API", new 1(), zzUI);
        zzaoG = new Api("Drive.INTERNAL_API", new 2(), zzUI);
        DriveApi = new zzs();
        zzaoH = new zzx();
        zzaoI = new zzac();
        DrivePreferencesApi = new zzaa();
    }

    private Drive() {
    }
}
