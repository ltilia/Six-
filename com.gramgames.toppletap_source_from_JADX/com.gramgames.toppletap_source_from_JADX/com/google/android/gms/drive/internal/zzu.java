package com.google.android.gms.drive.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.zze;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.DriveEventService;
import com.google.android.gms.drive.events.zzc;
import com.google.android.gms.drive.events.zzg;
import com.google.android.gms.drive.events.zzi;
import com.google.android.gms.drive.internal.zzt.zza;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.Yytoken;

public class zzu extends zzj<zzam> {
    private final String zzUW;
    final ConnectionCallbacks zzalF;
    private final Bundle zzaqK;
    private final boolean zzaqL;
    private volatile DriveId zzaqM;
    private volatile DriveId zzaqN;
    private volatile boolean zzaqO;
    final Map<DriveId, Map<ChangeListener, zzae>> zzaqP;
    final Map<zzc, zzae> zzaqQ;
    final Map<DriveId, Map<zzi, zzae>> zzaqR;
    final Map<DriveId, Map<zzi, zzae>> zzaqS;

    class 1 extends zza {
        final /* synthetic */ AddEventListenerRequest zzaqT;
        final /* synthetic */ zzae zzaqU;
        final /* synthetic */ zzu zzaqV;

        1(zzu com_google_android_gms_drive_internal_zzu, GoogleApiClient googleApiClient, AddEventListenerRequest addEventListenerRequest, zzae com_google_android_gms_drive_internal_zzae) {
            this.zzaqV = com_google_android_gms_drive_internal_zzu;
            this.zzaqT = addEventListenerRequest;
            this.zzaqU = com_google_android_gms_drive_internal_zzae;
            super(googleApiClient);
        }

        protected void zza(zzu com_google_android_gms_drive_internal_zzu) throws RemoteException {
            com_google_android_gms_drive_internal_zzu.zzte().zza(this.zzaqT, this.zzaqU, null, new zzbu(this));
        }
    }

    class 2 extends zza {
        final /* synthetic */ zzae zzaqU;
        final /* synthetic */ zzu zzaqV;
        final /* synthetic */ RemoveEventListenerRequest zzaqW;

        2(zzu com_google_android_gms_drive_internal_zzu, GoogleApiClient googleApiClient, RemoveEventListenerRequest removeEventListenerRequest, zzae com_google_android_gms_drive_internal_zzae) {
            this.zzaqV = com_google_android_gms_drive_internal_zzu;
            this.zzaqW = removeEventListenerRequest;
            this.zzaqU = com_google_android_gms_drive_internal_zzae;
            super(googleApiClient);
        }

        protected void zza(zzu com_google_android_gms_drive_internal_zzu) throws RemoteException {
            com_google_android_gms_drive_internal_zzu.zzte().zza(this.zzaqW, this.zzaqU, null, new zzbu(this));
        }
    }

    class 3 extends zza {
        final /* synthetic */ AddEventListenerRequest zzaqT;
        final /* synthetic */ zzu zzaqV;

        3(zzu com_google_android_gms_drive_internal_zzu, GoogleApiClient googleApiClient, AddEventListenerRequest addEventListenerRequest) {
            this.zzaqV = com_google_android_gms_drive_internal_zzu;
            this.zzaqT = addEventListenerRequest;
            super(googleApiClient);
        }

        protected void zza(zzu com_google_android_gms_drive_internal_zzu) throws RemoteException {
            com_google_android_gms_drive_internal_zzu.zzte().zza(this.zzaqT, null, null, new zzbu(this));
        }
    }

    class 4 extends zza {
        final /* synthetic */ zzu zzaqV;
        final /* synthetic */ DriveId zzaqX;
        final /* synthetic */ int zzaqY;

        4(zzu com_google_android_gms_drive_internal_zzu, GoogleApiClient googleApiClient, DriveId driveId, int i) {
            this.zzaqV = com_google_android_gms_drive_internal_zzu;
            this.zzaqX = driveId;
            this.zzaqY = i;
            super(googleApiClient);
        }

        protected void zza(zzu com_google_android_gms_drive_internal_zzu) throws RemoteException {
            com_google_android_gms_drive_internal_zzu.zzte().zza(new RemoveEventListenerRequest(this.zzaqX, this.zzaqY), null, null, new zzbu(this));
        }
    }

    class 5 extends zza {
        final /* synthetic */ zzu zzaqV;
        final /* synthetic */ List zzaqZ;

        5(zzu com_google_android_gms_drive_internal_zzu, GoogleApiClient googleApiClient, List list) {
            this.zzaqV = com_google_android_gms_drive_internal_zzu;
            this.zzaqZ = list;
            super(googleApiClient);
        }

        protected void zza(zzu com_google_android_gms_drive_internal_zzu) throws RemoteException {
            com_google_android_gms_drive_internal_zzu.zzte().zza(new CancelPendingActionsRequest(this.zzaqZ), new zzbu(this));
        }
    }

    public zzu(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, Bundle bundle) {
        super(context, looper, 11, com_google_android_gms_common_internal_zzf, connectionCallbacks, onConnectionFailedListener);
        this.zzaqO = false;
        this.zzaqP = new HashMap();
        this.zzaqQ = new HashMap();
        this.zzaqR = new HashMap();
        this.zzaqS = new HashMap();
        this.zzUW = com_google_android_gms_common_internal_zzf.zzqv();
        this.zzalF = connectionCallbacks;
        this.zzaqK = bundle;
        Intent intent = new Intent(DriveEventService.ACTION_HANDLE_EVENT);
        intent.setPackage(context.getPackageName());
        List queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        switch (queryIntentServices.size()) {
            case Yylex.YYINITIAL /*0*/:
                this.zzaqL = false;
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                ServiceInfo serviceInfo = ((ResolveInfo) queryIntentServices.get(0)).serviceInfo;
                if (serviceInfo.exported) {
                    this.zzaqL = true;
                    return;
                }
                throw new IllegalStateException("Drive event service " + serviceInfo.name + " must be exported in AndroidManifest.xml");
            default:
                throw new IllegalStateException("AndroidManifest.xml can only define one service that handles the " + intent.getAction() + " action");
        }
    }

    private PendingResult<Status> zza(GoogleApiClient googleApiClient, int i, DriveId driveId) {
        zzx.zzac(zzg.zza(i, driveId));
        zzx.zza(isConnected(), (Object) "Client must be connected");
        return googleApiClient.zzb(new 4(this, googleApiClient, driveId, i));
    }

    private PendingResult<Status> zza(GoogleApiClient googleApiClient, AddEventListenerRequest addEventListenerRequest) {
        zzx.zzac(zzg.zza(addEventListenerRequest.getEventType(), addEventListenerRequest.getDriveId()));
        zzx.zza(isConnected(), (Object) "Client must be connected");
        if (this.zzaqL) {
            return googleApiClient.zzb(new 3(this, googleApiClient, addEventListenerRequest));
        }
        throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
    }

    private PendingResult<Status> zza(GoogleApiClient googleApiClient, AddEventListenerRequest addEventListenerRequest, zzae com_google_android_gms_drive_internal_zzae) {
        return googleApiClient.zzb(new 1(this, googleApiClient, addEventListenerRequest, com_google_android_gms_drive_internal_zzae));
    }

    private PendingResult<Status> zza(GoogleApiClient googleApiClient, RemoveEventListenerRequest removeEventListenerRequest, zzae com_google_android_gms_drive_internal_zzae) {
        return googleApiClient.zzb(new 2(this, googleApiClient, removeEventListenerRequest, com_google_android_gms_drive_internal_zzae));
    }

    PendingResult<Status> cancelPendingActions(GoogleApiClient apiClient, List<String> pendingTags) {
        boolean z = true;
        zzx.zzac(pendingTags != null);
        if (pendingTags.isEmpty()) {
            z = false;
        }
        zzx.zzac(z);
        zzx.zza(isConnected(), (Object) "Client must be connected");
        return apiClient.zzb(new 5(this, apiClient, pendingTags));
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                ((zzam) zzqJ()).zza(new DisconnectRequest());
            } catch (RemoteException e) {
            }
        }
        super.disconnect();
        synchronized (this.zzaqP) {
            this.zzaqP.clear();
        }
        synchronized (this.zzaqQ) {
            this.zzaqQ.clear();
        }
        synchronized (this.zzaqR) {
            this.zzaqR.clear();
        }
        synchronized (this.zzaqS) {
            this.zzaqS.clear();
        }
    }

    protected /* synthetic */ IInterface zzW(IBinder iBinder) {
        return zzaZ(iBinder);
    }

    PendingResult<Status> zza(GoogleApiClient googleApiClient, DriveId driveId) {
        return zza(googleApiClient, new AddEventListenerRequest(1, driveId));
    }

    PendingResult<Status> zza(GoogleApiClient googleApiClient, DriveId driveId, ChangeListener changeListener) {
        PendingResult<Status> com_google_android_gms_drive_internal_zzs_zzj;
        zzx.zzac(zzg.zza(1, driveId));
        zzx.zzb((Object) changeListener, (Object) "listener");
        zzx.zza(isConnected(), (Object) "Client must be connected");
        synchronized (this.zzaqP) {
            Map map;
            Map map2 = (Map) this.zzaqP.get(driveId);
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                this.zzaqP.put(driveId, hashMap);
                map = hashMap;
            } else {
                map = map2;
            }
            zzae com_google_android_gms_drive_internal_zzae = (zzae) map.get(changeListener);
            if (com_google_android_gms_drive_internal_zzae == null) {
                com_google_android_gms_drive_internal_zzae = new zzae(getLooper(), getContext(), 1, changeListener);
                map.put(changeListener, com_google_android_gms_drive_internal_zzae);
            } else if (com_google_android_gms_drive_internal_zzae.zzdh(1)) {
                com_google_android_gms_drive_internal_zzs_zzj = new zzj(googleApiClient, Status.zzagC);
            }
            com_google_android_gms_drive_internal_zzae.zzdg(1);
            com_google_android_gms_drive_internal_zzs_zzj = zza(googleApiClient, new AddEventListenerRequest(1, driveId), com_google_android_gms_drive_internal_zzae);
        }
        return com_google_android_gms_drive_internal_zzs_zzj;
    }

    protected void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        if (bundle != null) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.zzaqM = (DriveId) bundle.getParcelable("com.google.android.gms.drive.root_id");
            this.zzaqN = (DriveId) bundle.getParcelable("com.google.android.gms.drive.appdata_id");
            this.zzaqO = true;
        }
        super.zza(i, iBinder, bundle, i2);
    }

    protected zzam zzaZ(IBinder iBinder) {
        return zzam.zza.zzba(iBinder);
    }

    PendingResult<Status> zzb(GoogleApiClient googleApiClient, DriveId driveId) {
        return zza(googleApiClient, 1, driveId);
    }

    PendingResult<Status> zzb(GoogleApiClient googleApiClient, DriveId driveId, ChangeListener changeListener) {
        PendingResult<Status> com_google_android_gms_drive_internal_zzs_zzj;
        zzx.zzac(zzg.zza(1, driveId));
        zzx.zza(isConnected(), (Object) "Client must be connected");
        zzx.zzb((Object) changeListener, (Object) "listener");
        synchronized (this.zzaqP) {
            Map map = (Map) this.zzaqP.get(driveId);
            if (map == null) {
                com_google_android_gms_drive_internal_zzs_zzj = new zzj(googleApiClient, Status.zzagC);
            } else {
                zzae com_google_android_gms_drive_internal_zzae = (zzae) map.remove(changeListener);
                if (com_google_android_gms_drive_internal_zzae == null) {
                    com_google_android_gms_drive_internal_zzs_zzj = new zzj(googleApiClient, Status.zzagC);
                } else {
                    if (map.isEmpty()) {
                        this.zzaqP.remove(driveId);
                    }
                    com_google_android_gms_drive_internal_zzs_zzj = zza(googleApiClient, new RemoveEventListenerRequest(driveId, 1), com_google_android_gms_drive_internal_zzae);
                }
            }
        }
        return com_google_android_gms_drive_internal_zzs_zzj;
    }

    protected String zzgu() {
        return "com.google.android.gms.drive.ApiService.START";
    }

    protected String zzgv() {
        return "com.google.android.gms.drive.internal.IDriveService";
    }

    public boolean zzmE() {
        return (getContext().getPackageName().equals(this.zzUW) && zztd()) ? false : true;
    }

    protected Bundle zzml() {
        String packageName = getContext().getPackageName();
        zzx.zzz(packageName);
        zzx.zzab(!zzqH().zzqt().isEmpty());
        Bundle bundle = new Bundle();
        if (!packageName.equals(this.zzUW)) {
            bundle.putString("proxy_package_name", this.zzUW);
        }
        bundle.putAll(this.zzaqK);
        return bundle;
    }

    public boolean zzqK() {
        return true;
    }

    boolean zztd() {
        return zze.zzf(getContext(), Process.myUid());
    }

    public zzam zzte() throws DeadObjectException {
        return (zzam) zzqJ();
    }

    public DriveId zztf() {
        return this.zzaqM;
    }

    public DriveId zztg() {
        return this.zzaqN;
    }

    public boolean zzth() {
        return this.zzaqO;
    }

    public boolean zzti() {
        return this.zzaqL;
    }
}
