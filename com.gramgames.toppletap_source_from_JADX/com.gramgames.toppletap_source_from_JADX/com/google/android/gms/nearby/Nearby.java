package com.google.android.gms.nearby;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.internal.zzqd;
import com.google.android.gms.internal.zzql;
import com.google.android.gms.nearby.bootstrap.zza;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.messages.Messages;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.internal.zzn;
import com.google.android.gms.nearby.messages.internal.zzo;
import com.google.android.gms.nearby.messages.zzc;
import com.google.android.gms.nearby.sharing.internal.zzh;
import com.google.android.gms.nearby.sharing.internal.zzi;
import com.google.android.gms.nearby.sharing.zzd;
import com.google.android.gms.nearby.sharing.zze;

public final class Nearby {
    public static final Api<NoOptions> CONNECTIONS_API;
    public static final Connections Connections;
    public static final Api<MessagesOptions> MESSAGES_API;
    public static final Messages Messages;
    public static final zzc zzbaK;
    public static final Api<NoOptions> zzbaL;
    public static final zzd zzbaM;
    public static final zze zzbaN;
    public static final Api<NoOptions> zzbaO;
    public static final zza zzbaP;

    static {
        CONNECTIONS_API = new Api("Nearby.CONNECTIONS_API", zzql.zzUJ, zzql.zzUI);
        Connections = new zzql();
        MESSAGES_API = new Api("Nearby.MESSAGES_API", zzn.zzUJ, zzn.zzUI);
        Messages = new zzn();
        zzbaK = new zzo();
        zzbaL = new Api("Nearby.SHARING_API", zzh.zzUJ, zzh.zzUI);
        zzbaM = new zzh();
        zzbaN = new zzi();
        zzbaO = new Api("Nearby.BOOTSTRAP_API", zzqd.zzUJ, zzqd.zzUI);
        zzbaP = new zzqd();
    }

    private Nearby() {
    }
}
