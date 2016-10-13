package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.nearby.connection.AppMetadata;
import com.google.android.gms.nearby.connection.Connections.ConnectionRequestListener;
import com.google.android.gms.nearby.connection.Connections.ConnectionResponseCallback;
import com.google.android.gms.nearby.connection.Connections.EndpointDiscoveryListener;
import com.google.android.gms.nearby.connection.Connections.MessageListener;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;

public final class zzqk extends zzj<zzqn> {
    private final long zzaEg;

    private static class zzb extends zzqj {
        private final zzq<MessageListener> zzbbb;

        class 1 implements com.google.android.gms.common.api.internal.zzq.zzb<MessageListener> {
            final /* synthetic */ byte[] zzaHk;
            final /* synthetic */ String zzbbc;
            final /* synthetic */ boolean zzbbd;
            final /* synthetic */ zzb zzbbe;

            1(zzb com_google_android_gms_internal_zzqk_zzb, String str, byte[] bArr, boolean z) {
                this.zzbbe = com_google_android_gms_internal_zzqk_zzb;
                this.zzbbc = str;
                this.zzaHk = bArr;
                this.zzbbd = z;
            }

            public void zza(MessageListener messageListener) {
                messageListener.onMessageReceived(this.zzbbc, this.zzaHk, this.zzbbd);
            }

            public void zzpr() {
            }

            public /* synthetic */ void zzt(Object obj) {
                zza((MessageListener) obj);
            }
        }

        class 2 implements com.google.android.gms.common.api.internal.zzq.zzb<MessageListener> {
            final /* synthetic */ String zzbbc;
            final /* synthetic */ zzb zzbbe;

            2(zzb com_google_android_gms_internal_zzqk_zzb, String str) {
                this.zzbbe = com_google_android_gms_internal_zzqk_zzb;
                this.zzbbc = str;
            }

            public void zza(MessageListener messageListener) {
                messageListener.onDisconnected(this.zzbbc);
            }

            public void zzpr() {
            }

            public /* synthetic */ void zzt(Object obj) {
                zza((MessageListener) obj);
            }
        }

        zzb(zzq<MessageListener> com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_MessageListener) {
            this.zzbbb = com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_MessageListener;
        }

        public void onDisconnected(String remoteEndpointId) throws RemoteException {
            this.zzbbb.zza(new 2(this, remoteEndpointId));
        }

        public void onMessageReceived(String remoteEndpointId, byte[] payload, boolean isReliable) throws RemoteException {
            this.zzbbb.zza(new 1(this, remoteEndpointId, payload, isReliable));
        }
    }

    private static final class zza extends zzb {
        private final com.google.android.gms.common.api.internal.zza.zzb<Status> zzamC;

        public zza(com.google.android.gms.common.api.internal.zza.zzb<Status> com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status, zzq<MessageListener> com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_MessageListener) {
            super(com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_MessageListener);
            this.zzamC = (com.google.android.gms.common.api.internal.zza.zzb) zzx.zzz(com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status);
        }

        public void zziZ(int i) throws RemoteException {
            this.zzamC.zzs(new Status(i));
        }
    }

    private static class zzc extends zzqj {
        private final com.google.android.gms.common.api.internal.zza.zzb<Status> zzbbf;

        zzc(com.google.android.gms.common.api.internal.zza.zzb<Status> com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status) {
            this.zzbbf = com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status;
        }

        public void zzja(int i) throws RemoteException {
            this.zzbbf.zzs(new Status(i));
        }
    }

    private static final class zzd extends zzb {
        private final com.google.android.gms.common.api.internal.zza.zzb<Status> zzamC;
        private final zzq<ConnectionResponseCallback> zzbbg;

        class 1 implements com.google.android.gms.common.api.internal.zzq.zzb<ConnectionResponseCallback> {
            final /* synthetic */ byte[] zzaHk;
            final /* synthetic */ int zzadW;
            final /* synthetic */ String zzbbc;
            final /* synthetic */ zzd zzbbh;

            1(zzd com_google_android_gms_internal_zzqk_zzd, String str, int i, byte[] bArr) {
                this.zzbbh = com_google_android_gms_internal_zzqk_zzd;
                this.zzbbc = str;
                this.zzadW = i;
                this.zzaHk = bArr;
            }

            public void zza(ConnectionResponseCallback connectionResponseCallback) {
                connectionResponseCallback.onConnectionResponse(this.zzbbc, new Status(this.zzadW), this.zzaHk);
            }

            public void zzpr() {
            }

            public /* synthetic */ void zzt(Object obj) {
                zza((ConnectionResponseCallback) obj);
            }
        }

        public zzd(com.google.android.gms.common.api.internal.zza.zzb<Status> com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status, zzq<ConnectionResponseCallback> com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_ConnectionResponseCallback, zzq<MessageListener> com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_MessageListener) {
            super(com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_MessageListener);
            this.zzamC = (com.google.android.gms.common.api.internal.zza.zzb) zzx.zzz(com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status);
            this.zzbbg = (zzq) zzx.zzz(com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_ConnectionResponseCallback);
        }

        public void zza(String str, int i, byte[] bArr) throws RemoteException {
            this.zzbbg.zza(new 1(this, str, i, bArr));
        }

        public void zziY(int i) throws RemoteException {
            this.zzamC.zzs(new Status(i));
        }
    }

    private static final class zze extends zzqj {
        private final com.google.android.gms.common.api.internal.zza.zzb<StartAdvertisingResult> zzamC;
        private final zzq<ConnectionRequestListener> zzbbi;

        class 1 implements com.google.android.gms.common.api.internal.zzq.zzb<ConnectionRequestListener> {
            final /* synthetic */ byte[] zzaHk;
            final /* synthetic */ String zzbbc;
            final /* synthetic */ String zzbbj;
            final /* synthetic */ String zzbbk;
            final /* synthetic */ zze zzbbl;

            1(zze com_google_android_gms_internal_zzqk_zze, String str, String str2, String str3, byte[] bArr) {
                this.zzbbl = com_google_android_gms_internal_zzqk_zze;
                this.zzbbc = str;
                this.zzbbj = str2;
                this.zzbbk = str3;
                this.zzaHk = bArr;
            }

            public void zza(ConnectionRequestListener connectionRequestListener) {
                connectionRequestListener.onConnectionRequest(this.zzbbc, this.zzbbj, this.zzbbk, this.zzaHk);
            }

            public void zzpr() {
            }

            public /* synthetic */ void zzt(Object obj) {
                zza((ConnectionRequestListener) obj);
            }
        }

        zze(com.google.android.gms.common.api.internal.zza.zzb<StartAdvertisingResult> com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_nearby_connection_Connections_StartAdvertisingResult, zzq<ConnectionRequestListener> com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_ConnectionRequestListener) {
            this.zzamC = (com.google.android.gms.common.api.internal.zza.zzb) zzx.zzz(com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_nearby_connection_Connections_StartAdvertisingResult);
            this.zzbbi = (zzq) zzx.zzz(com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_ConnectionRequestListener);
        }

        public void onConnectionRequest(String remoteEndpointId, String remoteDeviceId, String remoteEndpointName, byte[] payload) throws RemoteException {
            this.zzbbi.zza(new 1(this, remoteEndpointId, remoteDeviceId, remoteEndpointName, payload));
        }

        public void zzm(int i, String str) throws RemoteException {
            this.zzamC.zzs(new zzf(new Status(i), str));
        }
    }

    private static final class zzf implements StartAdvertisingResult {
        private final Status zzUX;
        private final String zzbbm;

        zzf(Status status, String str) {
            this.zzUX = status;
            this.zzbbm = str;
        }

        public String getLocalEndpointName() {
            return this.zzbbm;
        }

        public Status getStatus() {
            return this.zzUX;
        }
    }

    private static final class zzg extends zzqj {
        private final com.google.android.gms.common.api.internal.zza.zzb<Status> zzamC;
        private final zzq<EndpointDiscoveryListener> zzbbi;

        class 1 implements com.google.android.gms.common.api.internal.zzq.zzb<EndpointDiscoveryListener> {
            final /* synthetic */ String val$name;
            final /* synthetic */ String zzbbn;
            final /* synthetic */ String zzbbo;
            final /* synthetic */ String zzbbp;
            final /* synthetic */ zzg zzbbq;

            1(zzg com_google_android_gms_internal_zzqk_zzg, String str, String str2, String str3, String str4) {
                this.zzbbq = com_google_android_gms_internal_zzqk_zzg;
                this.zzbbn = str;
                this.zzbbo = str2;
                this.zzbbp = str3;
                this.val$name = str4;
            }

            public void zza(EndpointDiscoveryListener endpointDiscoveryListener) {
                endpointDiscoveryListener.onEndpointFound(this.zzbbn, this.zzbbo, this.zzbbp, this.val$name);
            }

            public void zzpr() {
            }

            public /* synthetic */ void zzt(Object obj) {
                zza((EndpointDiscoveryListener) obj);
            }
        }

        class 2 implements com.google.android.gms.common.api.internal.zzq.zzb<EndpointDiscoveryListener> {
            final /* synthetic */ String zzbbn;
            final /* synthetic */ zzg zzbbq;

            2(zzg com_google_android_gms_internal_zzqk_zzg, String str) {
                this.zzbbq = com_google_android_gms_internal_zzqk_zzg;
                this.zzbbn = str;
            }

            public void zza(EndpointDiscoveryListener endpointDiscoveryListener) {
                endpointDiscoveryListener.onEndpointLost(this.zzbbn);
            }

            public void zzpr() {
            }

            public /* synthetic */ void zzt(Object obj) {
                zza((EndpointDiscoveryListener) obj);
            }
        }

        zzg(com.google.android.gms.common.api.internal.zza.zzb<Status> com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status, zzq<EndpointDiscoveryListener> com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_EndpointDiscoveryListener) {
            this.zzamC = (com.google.android.gms.common.api.internal.zza.zzb) zzx.zzz(com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status);
            this.zzbbi = (zzq) zzx.zzz(com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_EndpointDiscoveryListener);
        }

        public void onEndpointFound(String endpointId, String deviceId, String serviceId, String name) throws RemoteException {
            this.zzbbi.zza(new 1(this, endpointId, deviceId, serviceId, name));
        }

        public void onEndpointLost(String endpointId) throws RemoteException {
            this.zzbbi.zza(new 2(this, endpointId));
        }

        public void zziW(int i) throws RemoteException {
            this.zzamC.zzs(new Status(i));
        }
    }

    public zzqk(Context context, Looper looper, com.google.android.gms.common.internal.zzf com_google_android_gms_common_internal_zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 54, com_google_android_gms_common_internal_zzf, connectionCallbacks, onConnectionFailedListener);
        this.zzaEg = (long) hashCode();
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                ((zzqn) zzqJ()).zzF(this.zzaEg);
            } catch (Throwable e) {
                Log.w("NearbyConnectionsClient", "Failed to notify client disconnect.", e);
            }
        }
        super.disconnect();
    }

    public String zzEj() {
        try {
            return ((zzqn) zzqJ()).zzaj(this.zzaEg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public String zzEk() {
        try {
            return ((zzqn) zzqJ()).zzEk();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void zzEl() {
        try {
            ((zzqn) zzqJ()).zzag(this.zzaEg);
        } catch (Throwable e) {
            Log.w("NearbyConnectionsClient", "Couldn't stop advertising", e);
        }
    }

    public void zzEm() {
        try {
            ((zzqn) zzqJ()).zzai(this.zzaEg);
        } catch (Throwable e) {
            Log.w("NearbyConnectionsClient", "Couldn't stop all endpoints", e);
        }
    }

    protected /* synthetic */ IInterface zzW(IBinder iBinder) {
        return zzdv(iBinder);
    }

    public void zza(com.google.android.gms.common.api.internal.zza.zzb<Status> com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status, String str, long j, zzq<EndpointDiscoveryListener> com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_EndpointDiscoveryListener) throws RemoteException {
        ((zzqn) zzqJ()).zza(new zzg(com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status, com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_EndpointDiscoveryListener), str, j, this.zzaEg);
    }

    public void zza(com.google.android.gms.common.api.internal.zza.zzb<StartAdvertisingResult> com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_nearby_connection_Connections_StartAdvertisingResult, String str, AppMetadata appMetadata, long j, zzq<ConnectionRequestListener> com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_ConnectionRequestListener) throws RemoteException {
        ((zzqn) zzqJ()).zza(new zze(com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_nearby_connection_Connections_StartAdvertisingResult, com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_ConnectionRequestListener), str, appMetadata, j, this.zzaEg);
    }

    public void zza(com.google.android.gms.common.api.internal.zza.zzb<Status> com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status, String str, String str2, byte[] bArr, zzq<ConnectionResponseCallback> com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_ConnectionResponseCallback, zzq<MessageListener> com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_MessageListener) throws RemoteException {
        ((zzqn) zzqJ()).zza(new zzd(com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status, com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_ConnectionResponseCallback, com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_MessageListener), str, str2, bArr, this.zzaEg);
    }

    public void zza(com.google.android.gms.common.api.internal.zza.zzb<Status> com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status, String str, byte[] bArr, zzq<MessageListener> com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_MessageListener) throws RemoteException {
        ((zzqn) zzqJ()).zza(new zza(com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status, com_google_android_gms_common_api_internal_zzq_com_google_android_gms_nearby_connection_Connections_MessageListener), str, bArr, this.zzaEg);
    }

    public void zza(String[] strArr, byte[] bArr) {
        try {
            ((zzqn) zzqJ()).zza(strArr, bArr, this.zzaEg);
        } catch (Throwable e) {
            Log.w("NearbyConnectionsClient", "Couldn't send reliable message", e);
        }
    }

    public void zzb(String[] strArr, byte[] bArr) {
        try {
            ((zzqn) zzqJ()).zzb(strArr, bArr, this.zzaEg);
        } catch (Throwable e) {
            Log.w("NearbyConnectionsClient", "Couldn't send unreliable message", e);
        }
    }

    protected zzqn zzdv(IBinder iBinder) {
        return com.google.android.gms.internal.zzqn.zza.zzdx(iBinder);
    }

    public void zzfA(String str) {
        try {
            ((zzqn) zzqJ()).zzi(str, this.zzaEg);
        } catch (Throwable e) {
            Log.w("NearbyConnectionsClient", "Couldn't disconnect from endpoint", e);
        }
    }

    public void zzfz(String str) {
        try {
            ((zzqn) zzqJ()).zzh(str, this.zzaEg);
        } catch (Throwable e) {
            Log.w("NearbyConnectionsClient", "Couldn't stop discovery", e);
        }
    }

    protected String zzgu() {
        return "com.google.android.gms.nearby.connection.service.START";
    }

    protected String zzgv() {
        return "com.google.android.gms.nearby.internal.connection.INearbyConnectionService";
    }

    public void zzp(com.google.android.gms.common.api.internal.zza.zzb<Status> com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status, String str) throws RemoteException {
        ((zzqn) zzqJ()).zza(new zzc(com_google_android_gms_common_api_internal_zza_zzb_com_google_android_gms_common_api_Status), str, this.zzaEg);
    }
}
