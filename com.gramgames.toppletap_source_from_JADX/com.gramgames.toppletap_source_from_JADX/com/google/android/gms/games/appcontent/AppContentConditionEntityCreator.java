package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import org.json.simple.parser.Yytoken;

public class AppContentConditionEntityCreator implements Creator<AppContentConditionEntity> {
    static void zza(AppContentConditionEntity appContentConditionEntity, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zza(parcel, 1, appContentConditionEntity.zzwf(), false);
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, appContentConditionEntity.getVersionCode());
        zzb.zza(parcel, 2, appContentConditionEntity.zzwg(), false);
        zzb.zza(parcel, 3, appContentConditionEntity.zzwh(), false);
        zzb.zza(parcel, 4, appContentConditionEntity.zzwi(), false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzei(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgk(i);
    }

    public AppContentConditionEntity zzei(Parcel parcel) {
        Bundle bundle = null;
        int zzau = zza.zzau(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
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
                    bundle = zza.zzr(parcel, zzat);
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
            return new AppContentConditionEntity(i, str3, str2, str, bundle);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public AppContentConditionEntity[] zzgk(int i) {
        return new AppContentConditionEntity[i];
    }
}
