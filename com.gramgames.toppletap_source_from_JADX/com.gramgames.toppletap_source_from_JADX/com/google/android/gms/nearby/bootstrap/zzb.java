package com.google.android.gms.nearby.bootstrap;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import org.json.simple.parser.Yytoken;

public class zzb implements Creator<Device> {
    static void zza(Device device, Parcel parcel, int i) {
        int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, device.getName(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, device.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, device.getDescription(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, device.getDeviceId(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, device.zzEb());
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfP(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zziL(i);
    }

    public Device zzfP(Parcel parcel) {
        byte b = (byte) 0;
        String str = null;
        int zzau = zza.zzau(parcel);
        String str2 = null;
        String str3 = null;
        int i = 0;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    str3 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    str2 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
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
            return new Device(i, str3, str2, str, b);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public Device[] zziL(int i) {
        return new Device[i];
    }
}
