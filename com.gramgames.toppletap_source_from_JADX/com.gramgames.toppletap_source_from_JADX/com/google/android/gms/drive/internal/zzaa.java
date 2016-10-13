package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.drive.DrivePreferencesApi.FileUploadPreferencesResult;
import com.google.android.gms.drive.FileUploadPreferences;

public class zzaa implements DrivePreferencesApi {

    private abstract class zzc extends zzt<FileUploadPreferencesResult> {
        final /* synthetic */ zzaa zzaru;

        public zzc(zzaa com_google_android_gms_drive_internal_zzaa, GoogleApiClient googleApiClient) {
            this.zzaru = com_google_android_gms_drive_internal_zzaa;
            super(googleApiClient);
        }

        protected FileUploadPreferencesResult zzG(Status status) {
            return new zzb(status, null, null);
        }

        protected /* synthetic */ Result zzc(Status status) {
            return zzG(status);
        }
    }

    class 1 extends zzc {
        final /* synthetic */ zzaa zzaru;

        1(zzaa com_google_android_gms_drive_internal_zzaa, GoogleApiClient googleApiClient) {
            this.zzaru = com_google_android_gms_drive_internal_zzaa;
            super(com_google_android_gms_drive_internal_zzaa, googleApiClient);
        }

        protected void zza(zzu com_google_android_gms_drive_internal_zzu) throws RemoteException {
            com_google_android_gms_drive_internal_zzu.zzte().zzd(new zza(this, null));
        }
    }

    class 2 extends com.google.android.gms.drive.internal.zzt.zza {
        final /* synthetic */ zzaa zzaru;
        final /* synthetic */ FileUploadPreferencesImpl zzarv;

        2(zzaa com_google_android_gms_drive_internal_zzaa, GoogleApiClient googleApiClient, FileUploadPreferencesImpl fileUploadPreferencesImpl) {
            this.zzaru = com_google_android_gms_drive_internal_zzaa;
            this.zzarv = fileUploadPreferencesImpl;
            super(googleApiClient);
        }

        protected void zza(zzu com_google_android_gms_drive_internal_zzu) throws RemoteException {
            com_google_android_gms_drive_internal_zzu.zzte().zza(new SetFileUploadPreferencesRequest(this.zzarv), new zzbu(this));
        }
    }

    private class zza extends zzd {
        private final com.google.android.gms.common.api.internal.zza.zzb<FileUploadPreferencesResult> zzamC;
        final /* synthetic */ zzaa zzaru;

        private zza(zzaa com_google_android_gms_drive_internal_zzaa, com.google.android.gms.common.api.internal.zza.zzb<FileUploadPreferencesResult> com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_drive_DrivePreferencesApi_FileUploadPreferencesResult) {
            this.zzaru = com_google_android_gms_drive_internal_zzaa;
            this.zzamC = com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_drive_DrivePreferencesApi_FileUploadPreferencesResult;
        }

        public void onError(Status status) throws RemoteException {
            this.zzamC.zzs(new zzb(status, null, null));
        }

        public void zza(OnDeviceUsagePreferenceResponse onDeviceUsagePreferenceResponse) throws RemoteException {
            this.zzamC.zzs(new zzb(Status.zzagC, onDeviceUsagePreferenceResponse.zztp(), null));
        }
    }

    private class zzb implements FileUploadPreferencesResult {
        private final Status zzUX;
        final /* synthetic */ zzaa zzaru;
        private final FileUploadPreferences zzarw;

        private zzb(zzaa com_google_android_gms_drive_internal_zzaa, Status status, FileUploadPreferences fileUploadPreferences) {
            this.zzaru = com_google_android_gms_drive_internal_zzaa;
            this.zzUX = status;
            this.zzarw = fileUploadPreferences;
        }

        public FileUploadPreferences getFileUploadPreferences() {
            return this.zzarw;
        }

        public Status getStatus() {
            return this.zzUX;
        }
    }

    public PendingResult<FileUploadPreferencesResult> getFileUploadPreferences(GoogleApiClient apiClient) {
        return apiClient.zza(new 1(this, apiClient));
    }

    public PendingResult<Status> setFileUploadPreferences(GoogleApiClient apiClient, FileUploadPreferences fileUploadPreferences) {
        if (fileUploadPreferences instanceof FileUploadPreferencesImpl) {
            return apiClient.zzb(new 2(this, apiClient, (FileUploadPreferencesImpl) fileUploadPreferences));
        }
        throw new IllegalArgumentException("Invalid preference value");
    }
}
