package com.google.android.gms.nearby.bootstrap.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import org.json.simple.parser.Yytoken;

public class zze implements Creator<EnableTargetRequest> {
    static void zza(EnableTargetRequest enableTargetRequest, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zza(parcel, 1, enableTargetRequest.getName(), false);
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, enableTargetRequest.versionCode);
        zzb.zza(parcel, 2, enableTargetRequest.getDescription(), false);
        zzb.zza(parcel, 3, enableTargetRequest.zzEg(), false);
        zzb.zza(parcel, 4, enableTargetRequest.zzEh(), false);
        zzb.zza(parcel, 5, enableTargetRequest.getCallbackBinder(), false);
        zzb.zza(parcel, 6, enableTargetRequest.zzEb());
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfU(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zziQ(i);
    }

    public EnableTargetRequest zzfU(Parcel parcel) {
        byte b = (byte) 0;
        IBinder iBinder = null;
        int zzau = zza.zzau(parcel);
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        String str = null;
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    str2 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    iBinder3 = zza.zzq(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    iBinder2 = zza.zzq(parcel, zzat);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    iBinder = zza.zzq(parcel, zzat);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    b = zza.zze(parcel, zzat);
                    break;
                case AdError.NETWORK_ERROR_CODE /*1000*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new EnableTargetRequest(i, str2, str, b, iBinder3, iBinder2, iBinder);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public EnableTargetRequest[] zziQ(int i) {
        return new EnableTargetRequest[i];
    }
}
