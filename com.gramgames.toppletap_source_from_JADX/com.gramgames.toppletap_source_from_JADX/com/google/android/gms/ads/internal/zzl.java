package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import org.json.simple.parser.Yytoken;

public class zzl implements Creator<InterstitialAdParameterParcel> {
    static void zza(InterstitialAdParameterParcel interstitialAdParameterParcel, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, interstitialAdParameterParcel.versionCode);
        zzb.zza(parcel, 2, interstitialAdParameterParcel.zzql);
        zzb.zza(parcel, 3, interstitialAdParameterParcel.zzqm);
        zzb.zza(parcel, 4, interstitialAdParameterParcel.zzqn, false);
        zzb.zza(parcel, 5, interstitialAdParameterParcel.zzqo);
        zzb.zza(parcel, 6, interstitialAdParameterParcel.zzqp);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zza(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzg(i);
    }

    public InterstitialAdParameterParcel zza(Parcel parcel) {
        boolean z = false;
        int zzau = zza.zzau(parcel);
        String str = null;
        float f = 0.0f;
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    z3 = zza.zzc(parcel, zzat);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    z2 = zza.zzc(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    z = zza.zzc(parcel, zzat);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    f = zza.zzl(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new InterstitialAdParameterParcel(i, z3, z2, str, z, f);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public InterstitialAdParameterParcel[] zzg(int i) {
        return new InterstitialAdParameterParcel[i];
    }
}
