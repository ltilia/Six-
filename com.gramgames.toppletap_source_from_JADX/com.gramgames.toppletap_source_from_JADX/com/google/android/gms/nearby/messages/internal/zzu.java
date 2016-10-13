package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import org.json.simple.parser.Yytoken;

public class zzu implements Creator<UnsubscribeRequest> {
    static void zza(UnsubscribeRequest unsubscribeRequest, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, unsubscribeRequest.mVersionCode);
        zzb.zza(parcel, 2, unsubscribeRequest.zzEH(), false);
        zzb.zza(parcel, 3, unsubscribeRequest.zzED(), false);
        zzb.zza(parcel, 4, unsubscribeRequest.zzbda, i, false);
        zzb.zzc(parcel, 5, unsubscribeRequest.zzbdb);
        zzb.zza(parcel, 6, unsubscribeRequest.zzbbF, false);
        zzb.zza(parcel, 7, unsubscribeRequest.zzbco, false);
        zzb.zza(parcel, 8, unsubscribeRequest.zzbbH);
        zzb.zza(parcel, 9, unsubscribeRequest.zzbcs, i, false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzgp(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzjv(i);
    }

    public UnsubscribeRequest zzgp(Parcel parcel) {
        boolean z = false;
        ClientAppContext clientAppContext = null;
        int zzau = zza.zzau(parcel);
        String str = null;
        String str2 = null;
        int i = 0;
        PendingIntent pendingIntent = null;
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    i2 = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    iBinder2 = zza.zzq(parcel, zzat);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    iBinder = zza.zzq(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    pendingIntent = (PendingIntent) zza.zza(parcel, zzat, PendingIntent.CREATOR);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    str2 = zza.zzp(parcel, zzat);
                    break;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    z = zza.zzc(parcel, zzat);
                    break;
                case R.styleable.Toolbar_popupTheme /*9*/:
                    clientAppContext = (ClientAppContext) zza.zza(parcel, zzat, ClientAppContext.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new UnsubscribeRequest(i2, iBinder2, iBinder, pendingIntent, i, str2, str, z, clientAppContext);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public UnsubscribeRequest[] zzjv(int i) {
        return new UnsubscribeRequest[i];
    }
}
