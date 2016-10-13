package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageFilter;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.Messages;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.PublishOptions.Builder;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.Strategy;
import com.google.android.gms.nearby.messages.SubscribeOptions;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import java.util.List;

public class zzn implements Messages {
    public static final zzc<zzm> zzUI;
    public static final com.google.android.gms.common.api.Api.zza<zzm, MessagesOptions> zzUJ;

    static abstract class zza extends com.google.android.gms.common.api.internal.zza.zza<Status, zzm> {
        public zza(GoogleApiClient googleApiClient) {
            super(zzn.zzUI, googleApiClient);
        }

        public Status zzb(Status status) {
            return status;
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzb(status);
        }
    }

    class 10 extends zza {
        final /* synthetic */ zzq zzbcL;
        final /* synthetic */ StatusCallback zzbcM;
        final /* synthetic */ zzn zzbcN;

        10(zzn com_google_android_gms_nearby_messages_internal_zzn, GoogleApiClient googleApiClient, zzq com_google_android_gms_common_api_internal_zzq, StatusCallback statusCallback) {
            this.zzbcN = com_google_android_gms_nearby_messages_internal_zzn;
            this.zzbcL = com_google_android_gms_common_api_internal_zzq;
            this.zzbcM = statusCallback;
            super(googleApiClient);
        }

        protected void zza(zzm com_google_android_gms_nearby_messages_internal_zzm) throws RemoteException {
            com_google_android_gms_nearby_messages_internal_zzm.zza((zzb) this, this.zzbcL, this.zzbcM);
        }
    }

    static class 1 extends com.google.android.gms.common.api.Api.zza<zzm, MessagesOptions> {
        1() {
        }

        public int getPriority() {
            return MoPubClientPositioning.NO_REPEAT;
        }

        public zzm zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, MessagesOptions messagesOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzm(context, looper, connectionCallbacks, onConnectionFailedListener, com_google_android_gms_common_internal_zzf, messagesOptions);
        }
    }

    class 2 extends zza {
        final /* synthetic */ zzq zzbcL;
        final /* synthetic */ StatusCallback zzbcM;
        final /* synthetic */ zzn zzbcN;

        2(zzn com_google_android_gms_nearby_messages_internal_zzn, GoogleApiClient googleApiClient, zzq com_google_android_gms_common_api_internal_zzq, StatusCallback statusCallback) {
            this.zzbcN = com_google_android_gms_nearby_messages_internal_zzn;
            this.zzbcL = com_google_android_gms_common_api_internal_zzq;
            this.zzbcM = statusCallback;
            super(googleApiClient);
        }

        protected void zza(zzm com_google_android_gms_nearby_messages_internal_zzm) throws RemoteException {
            com_google_android_gms_nearby_messages_internal_zzm.zzb(this, this.zzbcL, this.zzbcM);
        }
    }

    class 3 extends zza {
        final /* synthetic */ zzn zzbcN;
        final /* synthetic */ Message zzbcO;
        final /* synthetic */ PublishOptions zzbcP;

        3(zzn com_google_android_gms_nearby_messages_internal_zzn, GoogleApiClient googleApiClient, Message message, PublishOptions publishOptions) {
            this.zzbcN = com_google_android_gms_nearby_messages_internal_zzn;
            this.zzbcO = message;
            this.zzbcP = publishOptions;
            super(googleApiClient);
        }

        protected void zza(zzm com_google_android_gms_nearby_messages_internal_zzm) throws RemoteException {
            com_google_android_gms_nearby_messages_internal_zzm.zza((zzb) this, MessageWrapper.zzb(this.zzbcO), this.zzbcP);
        }
    }

    class 4 extends zza {
        final /* synthetic */ zzn zzbcN;
        final /* synthetic */ Message zzbcO;

        4(zzn com_google_android_gms_nearby_messages_internal_zzn, GoogleApiClient googleApiClient, Message message) {
            this.zzbcN = com_google_android_gms_nearby_messages_internal_zzn;
            this.zzbcO = message;
            super(googleApiClient);
        }

        protected void zza(zzm com_google_android_gms_nearby_messages_internal_zzm) throws RemoteException {
            com_google_android_gms_nearby_messages_internal_zzm.zza((zzb) this, MessageWrapper.zzb(this.zzbcO));
        }
    }

    class 5 extends zza {
        final /* synthetic */ zzq zzbbv;
        final /* synthetic */ zzn zzbcN;
        final /* synthetic */ MessageListener zzbcQ;
        final /* synthetic */ SubscribeOptions zzbcR;

        5(zzn com_google_android_gms_nearby_messages_internal_zzn, GoogleApiClient googleApiClient, zzq com_google_android_gms_common_api_internal_zzq, MessageListener messageListener, SubscribeOptions subscribeOptions) {
            this.zzbcN = com_google_android_gms_nearby_messages_internal_zzn;
            this.zzbbv = com_google_android_gms_common_api_internal_zzq;
            this.zzbcQ = messageListener;
            this.zzbcR = subscribeOptions;
            super(googleApiClient);
        }

        protected void zza(zzm com_google_android_gms_nearby_messages_internal_zzm) throws RemoteException {
            com_google_android_gms_nearby_messages_internal_zzm.zza(this, this.zzbbv, this.zzbcQ, this.zzbcR, null);
        }
    }

    class 6 extends zza {
        final /* synthetic */ PendingIntent zzaAp;
        final /* synthetic */ zzn zzbcN;
        final /* synthetic */ SubscribeOptions zzbcR;

        6(zzn com_google_android_gms_nearby_messages_internal_zzn, GoogleApiClient googleApiClient, PendingIntent pendingIntent, SubscribeOptions subscribeOptions) {
            this.zzbcN = com_google_android_gms_nearby_messages_internal_zzn;
            this.zzaAp = pendingIntent;
            this.zzbcR = subscribeOptions;
            super(googleApiClient);
        }

        protected void zza(zzm com_google_android_gms_nearby_messages_internal_zzm) throws RemoteException {
            com_google_android_gms_nearby_messages_internal_zzm.zza((zzb) this, this.zzaAp, this.zzbcR);
        }
    }

    class 7 extends zza {
        final /* synthetic */ zzq zzbbv;
        final /* synthetic */ zzn zzbcN;
        final /* synthetic */ MessageListener zzbcQ;

        7(zzn com_google_android_gms_nearby_messages_internal_zzn, GoogleApiClient googleApiClient, zzq com_google_android_gms_common_api_internal_zzq, MessageListener messageListener) {
            this.zzbcN = com_google_android_gms_nearby_messages_internal_zzn;
            this.zzbbv = com_google_android_gms_common_api_internal_zzq;
            this.zzbcQ = messageListener;
            super(googleApiClient);
        }

        protected void zza(zzm com_google_android_gms_nearby_messages_internal_zzm) throws RemoteException {
            com_google_android_gms_nearby_messages_internal_zzm.zza((zzb) this, this.zzbbv, this.zzbcQ);
        }
    }

    class 8 extends zza {
        final /* synthetic */ PendingIntent zzaAp;
        final /* synthetic */ zzn zzbcN;

        8(zzn com_google_android_gms_nearby_messages_internal_zzn, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
            this.zzbcN = com_google_android_gms_nearby_messages_internal_zzn;
            this.zzaAp = pendingIntent;
            super(googleApiClient);
        }

        protected void zza(zzm com_google_android_gms_nearby_messages_internal_zzm) throws RemoteException {
            com_google_android_gms_nearby_messages_internal_zzm.zza((zzb) this, this.zzaAp);
        }
    }

    class 9 extends zza {
        final /* synthetic */ zzn zzbcN;

        9(zzn com_google_android_gms_nearby_messages_internal_zzn, GoogleApiClient googleApiClient) {
            this.zzbcN = com_google_android_gms_nearby_messages_internal_zzn;
            super(googleApiClient);
        }

        protected void zza(zzm com_google_android_gms_nearby_messages_internal_zzm) throws RemoteException {
            com_google_android_gms_nearby_messages_internal_zzm.zzm(this);
        }
    }

    static {
        zzUI = new zzc();
        zzUJ = new 1();
    }

    @Nullable
    private static Message zzA(Intent intent) {
        return (Message) zzj.zzc(intent, "com.google.android.gms.nearby.messages.MESSAGES");
    }

    @Nullable
    private static Message zzB(Intent intent) {
        return (Message) zzj.zzc(intent, "com.google.android.gms.nearby.messages.LOST_MESSAGE");
    }

    private static List<Message> zzC(Intent intent) {
        return zzj.zzd(intent, "com.google.android.gms.nearby.messages.UPDATED_MESSAGES");
    }

    public PendingResult<Status> getPermissionStatus(GoogleApiClient client) {
        return client.zzb(new 9(this, client));
    }

    public void handleIntent(Intent intent, MessageListener messageListener) {
        Message zzA = zzA(intent);
        if (zzA != null) {
            messageListener.onFound(zzA);
        }
        zzA = zzB(intent);
        if (zzA != null) {
            messageListener.onLost(zzA);
        }
        for (Message zzA2 : zzC(intent)) {
            messageListener.zza(zzA2);
        }
    }

    public PendingResult<Status> publish(GoogleApiClient client, Message message) {
        return publish(client, message, PublishOptions.DEFAULT);
    }

    public PendingResult<Status> publish(GoogleApiClient client, Message message, PublishOptions options) {
        zzx.zzz(message);
        zzx.zzz(options);
        return client.zzb(new 3(this, client, message, options));
    }

    @Deprecated
    public PendingResult<Status> publish(GoogleApiClient client, Message message, Strategy strategy) {
        return publish(client, message, new Builder().setStrategy(strategy).build());
    }

    public PendingResult<Status> registerStatusCallback(GoogleApiClient client, StatusCallback statusCallback) {
        zzx.zzz(statusCallback);
        return client.zzb(new 10(this, client, ((zzm) client.zza(zzUI)).zza(client, statusCallback), statusCallback));
    }

    public PendingResult<Status> subscribe(GoogleApiClient client, PendingIntent pendingIntent) {
        return subscribe(client, pendingIntent, SubscribeOptions.DEFAULT);
    }

    public PendingResult<Status> subscribe(GoogleApiClient client, PendingIntent pendingIntent, SubscribeOptions options) {
        zzx.zzz(pendingIntent);
        zzx.zzz(options);
        return client.zzb(new 6(this, client, pendingIntent, options));
    }

    @Deprecated
    public PendingResult<Status> subscribe(GoogleApiClient client, MessageListener listener) {
        return subscribe(client, listener, SubscribeOptions.DEFAULT);
    }

    @Deprecated
    public PendingResult<Status> subscribe(GoogleApiClient client, MessageListener listener, Strategy strategy) {
        return subscribe(client, listener, new SubscribeOptions.Builder().setStrategy(strategy).build());
    }

    @Deprecated
    public PendingResult<Status> subscribe(GoogleApiClient client, MessageListener listener, Strategy strategy, MessageFilter filter) {
        return subscribe(client, listener, new SubscribeOptions.Builder().setStrategy(strategy).setFilter(filter).build());
    }

    public PendingResult<Status> subscribe(GoogleApiClient client, MessageListener listener, SubscribeOptions options) {
        zzx.zzz(listener);
        zzx.zzz(options);
        return client.zzb(new 5(this, client, ((zzm) client.zza(zzUI)).zza(client, listener), listener, options));
    }

    public PendingResult<Status> unpublish(GoogleApiClient client, Message message) {
        zzx.zzz(message);
        return client.zzb(new 4(this, client, message));
    }

    public PendingResult<Status> unregisterStatusCallback(GoogleApiClient client, StatusCallback statusCallback) {
        return client.zzb(new 2(this, client, ((zzm) client.zza(zzUI)).zza(client, statusCallback), statusCallback));
    }

    public PendingResult<Status> unsubscribe(GoogleApiClient client, PendingIntent pendingIntent) {
        zzx.zzz(pendingIntent);
        return client.zzb(new 8(this, client, pendingIntent));
    }

    public PendingResult<Status> unsubscribe(GoogleApiClient client, MessageListener listener) {
        zzx.zzz(listener);
        return client.zzb(new 7(this, client, ((zzm) client.zza(zzUI)).zza(client, listener), listener));
    }
}
