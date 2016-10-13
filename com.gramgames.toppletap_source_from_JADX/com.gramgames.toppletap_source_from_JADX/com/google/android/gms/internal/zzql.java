package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AppMetadata;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.connection.Connections.ConnectionRequestListener;
import com.google.android.gms.nearby.connection.Connections.ConnectionResponseCallback;
import com.google.android.gms.nearby.connection.Connections.EndpointDiscoveryListener;
import com.google.android.gms.nearby.connection.Connections.MessageListener;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;
import java.util.List;

public final class zzql implements Connections {
    public static final com.google.android.gms.common.api.Api.zzc<zzqk> zzUI;
    public static final com.google.android.gms.common.api.Api.zza<zzqk, NoOptions> zzUJ;

    static class 1 extends com.google.android.gms.common.api.Api.zza<zzqk, NoOptions> {
        1() {
        }

        public /* synthetic */ com.google.android.gms.common.api.Api.zzb zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zzp(context, looper, com_google_android_gms_common_internal_zzf, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzqk zzp(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzqk(context, looper, com_google_android_gms_common_internal_zzf, connectionCallbacks, onConnectionFailedListener);
        }
    }

    private static abstract class zza<R extends Result> extends com.google.android.gms.common.api.internal.zza.zza<R, zzqk> {
        public zza(GoogleApiClient googleApiClient) {
            super(zzql.zzUI, googleApiClient);
        }
    }

    private static abstract class zzb extends zza<StartAdvertisingResult> {

        class 1 implements StartAdvertisingResult {
            final /* synthetic */ Status zzZR;
            final /* synthetic */ zzb zzbby;

            1(zzb com_google_android_gms_internal_zzql_zzb, Status status) {
                this.zzbby = com_google_android_gms_internal_zzql_zzb;
                this.zzZR = status;
            }

            public String getLocalEndpointName() {
                return null;
            }

            public Status getStatus() {
                return this.zzZR;
            }
        }

        private zzb(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public StartAdvertisingResult zzbc(Status status) {
            return new 1(this, status);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzbc(status);
        }
    }

    class 2 extends zzb {
        final /* synthetic */ String val$name;
        final /* synthetic */ AppMetadata zzbbr;
        final /* synthetic */ long zzbbs;
        final /* synthetic */ zzq zzbbt;
        final /* synthetic */ zzql zzbbu;

        2(zzql com_google_android_gms_internal_zzql, GoogleApiClient googleApiClient, String str, AppMetadata appMetadata, long j, zzq com_google_android_gms_common_api_internal_zzq) {
            this.zzbbu = com_google_android_gms_internal_zzql;
            this.val$name = str;
            this.zzbbr = appMetadata;
            this.zzbbs = j;
            this.zzbbt = com_google_android_gms_common_api_internal_zzq;
            super(null);
        }

        protected void zza(zzqk com_google_android_gms_internal_zzqk) throws RemoteException {
            com_google_android_gms_internal_zzqk.zza(this, this.val$name, this.zzbbr, this.zzbbs, this.zzbbt);
        }
    }

    private static abstract class zzc extends zza<Status> {
        private zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public Status zzb(Status status) {
            return status;
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzb(status);
        }
    }

    class 3 extends zzc {
        final /* synthetic */ String zzbbp;
        final /* synthetic */ long zzbbs;
        final /* synthetic */ zzql zzbbu;
        final /* synthetic */ zzq zzbbv;

        3(zzql com_google_android_gms_internal_zzql, GoogleApiClient googleApiClient, String str, long j, zzq com_google_android_gms_common_api_internal_zzq) {
            this.zzbbu = com_google_android_gms_internal_zzql;
            this.zzbbp = str;
            this.zzbbs = j;
            this.zzbbv = com_google_android_gms_common_api_internal_zzq;
            super(null);
        }

        protected void zza(zzqk com_google_android_gms_internal_zzqk) throws RemoteException {
            com_google_android_gms_internal_zzqk.zza((com.google.android.gms.common.api.internal.zza.zzb) this, this.zzbbp, this.zzbbs, this.zzbbv);
        }
    }

    class 4 extends zzc {
        final /* synthetic */ String val$name;
        final /* synthetic */ byte[] zzaHk;
        final /* synthetic */ String zzbbc;
        final /* synthetic */ zzql zzbbu;
        final /* synthetic */ zzq zzbbw;
        final /* synthetic */ zzq zzbbx;

        4(zzql com_google_android_gms_internal_zzql, GoogleApiClient googleApiClient, String str, String str2, byte[] bArr, zzq com_google_android_gms_common_api_internal_zzq, zzq com_google_android_gms_common_api_internal_zzq2) {
            this.zzbbu = com_google_android_gms_internal_zzql;
            this.val$name = str;
            this.zzbbc = str2;
            this.zzaHk = bArr;
            this.zzbbw = com_google_android_gms_common_api_internal_zzq;
            this.zzbbx = com_google_android_gms_common_api_internal_zzq2;
            super(null);
        }

        protected void zza(zzqk com_google_android_gms_internal_zzqk) throws RemoteException {
            com_google_android_gms_internal_zzqk.zza(this, this.val$name, this.zzbbc, this.zzaHk, this.zzbbw, this.zzbbx);
        }
    }

    class 5 extends zzc {
        final /* synthetic */ byte[] zzaHk;
        final /* synthetic */ String zzbbc;
        final /* synthetic */ zzql zzbbu;
        final /* synthetic */ zzq zzbbx;

        5(zzql com_google_android_gms_internal_zzql, GoogleApiClient googleApiClient, String str, byte[] bArr, zzq com_google_android_gms_common_api_internal_zzq) {
            this.zzbbu = com_google_android_gms_internal_zzql;
            this.zzbbc = str;
            this.zzaHk = bArr;
            this.zzbbx = com_google_android_gms_common_api_internal_zzq;
            super(null);
        }

        protected void zza(zzqk com_google_android_gms_internal_zzqk) throws RemoteException {
            com_google_android_gms_internal_zzqk.zza((com.google.android.gms.common.api.internal.zza.zzb) this, this.zzbbc, this.zzaHk, this.zzbbx);
        }
    }

    class 6 extends zzc {
        final /* synthetic */ String zzbbc;
        final /* synthetic */ zzql zzbbu;

        6(zzql com_google_android_gms_internal_zzql, GoogleApiClient googleApiClient, String str) {
            this.zzbbu = com_google_android_gms_internal_zzql;
            this.zzbbc = str;
            super(null);
        }

        protected void zza(zzqk com_google_android_gms_internal_zzqk) throws RemoteException {
            com_google_android_gms_internal_zzqk.zzp(this, this.zzbbc);
        }
    }

    static {
        zzUI = new com.google.android.gms.common.api.Api.zzc();
        zzUJ = new 1();
    }

    public static zzqk zzd(GoogleApiClient googleApiClient, boolean z) {
        zzx.zzb(googleApiClient != null, (Object) "GoogleApiClient parameter is required.");
        zzx.zza(googleApiClient.isConnected(), (Object) "GoogleApiClient must be connected.");
        return zze(googleApiClient, z);
    }

    public static zzqk zze(GoogleApiClient googleApiClient, boolean z) {
        zzx.zza(googleApiClient.zza(Nearby.CONNECTIONS_API), (Object) "GoogleApiClient is not configured to use the Nearby Connections Api. Pass Nearby.CONNECTIONS_API into GoogleApiClient.Builder#addApi() to use this feature.");
        boolean hasConnectedApi = googleApiClient.hasConnectedApi(Nearby.CONNECTIONS_API);
        if (!z || hasConnectedApi) {
            return hasConnectedApi ? (zzqk) googleApiClient.zza(zzUI) : null;
        } else {
            throw new IllegalStateException("GoogleApiClient has an optional Nearby.CONNECTIONS_API and is not connected to Nearby Connections. Use GoogleApiClient.hasConnectedApi(Nearby.CONNECTIONS_API) to guard this call.");
        }
    }

    public PendingResult<Status> acceptConnectionRequest(GoogleApiClient apiClient, String remoteEndpointId, byte[] payload, MessageListener messageListener) {
        return apiClient.zzb(new 5(this, apiClient, remoteEndpointId, payload, apiClient.zzr(messageListener)));
    }

    public void disconnectFromEndpoint(GoogleApiClient apiClient, String remoteEndpointId) {
        zzd(apiClient, false).zzfA(remoteEndpointId);
    }

    public String getLocalDeviceId(GoogleApiClient apiClient) {
        return zzd(apiClient, true).zzEk();
    }

    public String getLocalEndpointId(GoogleApiClient apiClient) {
        return zzd(apiClient, true).zzEj();
    }

    public PendingResult<Status> rejectConnectionRequest(GoogleApiClient apiClient, String remoteEndpointId) {
        return apiClient.zzb(new 6(this, apiClient, remoteEndpointId));
    }

    public PendingResult<Status> sendConnectionRequest(GoogleApiClient apiClient, String name, String remoteEndpointId, byte[] payload, ConnectionResponseCallback connectionResponseCallback, MessageListener messageListener) {
        return apiClient.zzb(new 4(this, apiClient, name, remoteEndpointId, payload, apiClient.zzr(connectionResponseCallback), apiClient.zzr(messageListener)));
    }

    public void sendReliableMessage(GoogleApiClient apiClient, String remoteEndpointId, byte[] payload) {
        zzd(apiClient, false).zza(new String[]{remoteEndpointId}, payload);
    }

    public void sendReliableMessage(GoogleApiClient apiClient, List<String> remoteEndpointIds, byte[] payload) {
        zzd(apiClient, false).zza((String[]) remoteEndpointIds.toArray(new String[remoteEndpointIds.size()]), payload);
    }

    public void sendUnreliableMessage(GoogleApiClient apiClient, String remoteEndpointId, byte[] payload) {
        zzd(apiClient, false).zzb(new String[]{remoteEndpointId}, payload);
    }

    public void sendUnreliableMessage(GoogleApiClient apiClient, List<String> remoteEndpointIds, byte[] payload) {
        zzd(apiClient, false).zzb((String[]) remoteEndpointIds.toArray(new String[remoteEndpointIds.size()]), payload);
    }

    public PendingResult<StartAdvertisingResult> startAdvertising(GoogleApiClient apiClient, String name, AppMetadata appMetadata, long durationMillis, ConnectionRequestListener connectionRequestListener) {
        return apiClient.zzb(new 2(this, apiClient, name, appMetadata, durationMillis, apiClient.zzr(connectionRequestListener)));
    }

    public PendingResult<Status> startDiscovery(GoogleApiClient apiClient, String serviceId, long durationMillis, EndpointDiscoveryListener listener) {
        return apiClient.zzb(new 3(this, apiClient, serviceId, durationMillis, apiClient.zzr(listener)));
    }

    public void stopAdvertising(GoogleApiClient apiClient) {
        zzd(apiClient, false).zzEl();
    }

    public void stopAllEndpoints(GoogleApiClient apiClient) {
        zzd(apiClient, false).zzEm();
    }

    public void stopDiscovery(GoogleApiClient apiClient, String serviceId) {
        zzd(apiClient, false).zzfz(serviceId);
    }
}
