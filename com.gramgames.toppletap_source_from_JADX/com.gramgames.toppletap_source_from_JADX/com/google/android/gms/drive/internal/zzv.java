package com.google.android.gms.drive.internal;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.internal.zzt.zza;
import com.google.android.gms.drive.zzi;
import com.google.android.gms.internal.zzna;
import java.io.InputStream;
import java.io.OutputStream;

public class zzv implements DriveContents {
    private boolean mClosed;
    private final Contents zzara;
    private boolean zzarb;
    private boolean zzarc;

    class 1 extends zzc {
        final /* synthetic */ zzv zzard;

        1(zzv com_google_android_gms_drive_internal_zzv, GoogleApiClient googleApiClient) {
            this.zzard = com_google_android_gms_drive_internal_zzv;
            super(googleApiClient);
        }

        protected void zza(zzu com_google_android_gms_drive_internal_zzu) throws RemoteException {
            com_google_android_gms_drive_internal_zzu.zzte().zza(new OpenContentsRequest(this.zzard.getDriveId(), DriveFile.MODE_WRITE_ONLY, this.zzard.zzara.getRequestId()), new zzbl(this, null));
        }
    }

    class 2 extends zza {
        final /* synthetic */ zzv zzard;
        final /* synthetic */ MetadataChangeSet zzare;
        final /* synthetic */ zzi zzarf;

        2(zzv com_google_android_gms_drive_internal_zzv, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, zzi com_google_android_gms_drive_zzi) {
            this.zzard = com_google_android_gms_drive_internal_zzv;
            this.zzare = metadataChangeSet;
            this.zzarf = com_google_android_gms_drive_zzi;
            super(googleApiClient);
        }

        protected void zza(zzu com_google_android_gms_drive_internal_zzu) throws RemoteException {
            this.zzare.zzsL().setContext(com_google_android_gms_drive_internal_zzu.getContext());
            com_google_android_gms_drive_internal_zzu.zzte().zza(new CloseContentsAndUpdateMetadataRequest(this.zzard.zzara.getDriveId(), this.zzare.zzsL(), this.zzard.zzara.getRequestId(), this.zzard.zzara.zzsv(), this.zzarf), new zzbu(this));
        }
    }

    class 3 implements ResultCallback<Status> {
        final /* synthetic */ zzv zzard;

        3(zzv com_google_android_gms_drive_internal_zzv) {
            this.zzard = com_google_android_gms_drive_internal_zzv;
        }

        public /* synthetic */ void onResult(Result result) {
            zzp((Status) result);
        }

        public void zzp(Status status) {
            if (status.isSuccess()) {
                zzz.zzy("DriveContentsImpl", "Contents discarded");
            } else {
                zzz.zzA("DriveContentsImpl", "Error discarding contents");
            }
        }
    }

    class 4 extends zza {
        final /* synthetic */ zzv zzard;

        4(zzv com_google_android_gms_drive_internal_zzv, GoogleApiClient googleApiClient) {
            this.zzard = com_google_android_gms_drive_internal_zzv;
            super(googleApiClient);
        }

        protected void zza(zzu com_google_android_gms_drive_internal_zzu) throws RemoteException {
            com_google_android_gms_drive_internal_zzu.zzte().zza(new CloseContentsRequest(this.zzard.zzara.getRequestId(), false), new zzbu(this));
        }
    }

    public zzv(Contents contents) {
        this.mClosed = false;
        this.zzarb = false;
        this.zzarc = false;
        this.zzara = (Contents) zzx.zzz(contents);
    }

    public PendingResult<Status> commit(GoogleApiClient apiClient, MetadataChangeSet changeSet) {
        return zza(apiClient, changeSet, null);
    }

    public PendingResult<Status> commit(GoogleApiClient apiClient, MetadataChangeSet changeSet, ExecutionOptions executionOptions) {
        return zza(apiClient, changeSet, executionOptions == null ? null : zzi.zzb(executionOptions));
    }

    public void discard(GoogleApiClient apiClient) {
        if (zzsz()) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        zzsy();
        ((4) apiClient.zzb(new 4(this, apiClient))).setResultCallback(new 3(this));
    }

    public DriveId getDriveId() {
        return this.zzara.getDriveId();
    }

    public InputStream getInputStream() {
        if (zzsz()) {
            throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
        } else if (this.zzara.getMode() != DriveFile.MODE_READ_ONLY) {
            throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
        } else if (this.zzarb) {
            throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
        } else {
            this.zzarb = true;
            return this.zzara.getInputStream();
        }
    }

    public int getMode() {
        return this.zzara.getMode();
    }

    public OutputStream getOutputStream() {
        if (zzsz()) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        } else if (this.zzara.getMode() != DriveFile.MODE_WRITE_ONLY) {
            throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
        } else if (this.zzarc) {
            throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
        } else {
            this.zzarc = true;
            return this.zzara.getOutputStream();
        }
    }

    public ParcelFileDescriptor getParcelFileDescriptor() {
        if (!zzsz()) {
            return this.zzara.getParcelFileDescriptor();
        }
        throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
    }

    public PendingResult<DriveContentsResult> reopenForWrite(GoogleApiClient apiClient) {
        if (zzsz()) {
            throw new IllegalStateException("DriveContents already closed.");
        } else if (this.zzara.getMode() != DriveFile.MODE_READ_ONLY) {
            throw new IllegalStateException("reopenForWrite can only be used with DriveContents opened with MODE_READ_ONLY.");
        } else {
            zzsy();
            return apiClient.zza(new 1(this, apiClient));
        }
    }

    public PendingResult<Status> zza(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, zzi com_google_android_gms_drive_zzi) {
        if (com_google_android_gms_drive_zzi == null) {
            com_google_android_gms_drive_zzi = new zzi.zza().zzsJ();
        }
        if (this.zzara.getMode() == DriveFile.MODE_READ_ONLY) {
            throw new IllegalStateException("Cannot commit contents opened with MODE_READ_ONLY");
        } else if (!ExecutionOptions.zzcv(com_google_android_gms_drive_zzi.zzsD()) || this.zzara.zzsv()) {
            com_google_android_gms_drive_zzi.zzg(googleApiClient);
            if (zzsz()) {
                throw new IllegalStateException("DriveContents already closed.");
            } else if (getDriveId() == null) {
                throw new IllegalStateException("Only DriveContents obtained through DriveFile.open can be committed.");
            } else {
                if (metadataChangeSet == null) {
                    metadataChangeSet = MetadataChangeSet.zzapd;
                }
                zzsy();
                return googleApiClient.zzb(new 2(this, googleApiClient, metadataChangeSet, com_google_android_gms_drive_zzi));
            }
        } else {
            throw new IllegalStateException("DriveContents must be valid for conflict detection.");
        }
    }

    public Contents zzsx() {
        return this.zzara;
    }

    public void zzsy() {
        zzna.zza(this.zzara.getParcelFileDescriptor());
        this.mClosed = true;
    }

    public boolean zzsz() {
        return this.mClosed;
    }
}
