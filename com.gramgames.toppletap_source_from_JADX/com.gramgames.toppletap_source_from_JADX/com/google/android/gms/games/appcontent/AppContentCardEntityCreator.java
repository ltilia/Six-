package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.unity3d.ads.android.R;
import java.util.ArrayList;
import org.json.simple.parser.Yytoken;

public class AppContentCardEntityCreator implements Creator<AppContentCardEntity> {
    static void zza(AppContentCardEntity appContentCardEntity, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, appContentCardEntity.getActions(), false);
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, appContentCardEntity.getVersionCode());
        zzb.zzc(parcel, 2, appContentCardEntity.zzwa(), false);
        zzb.zzc(parcel, 3, appContentCardEntity.zzvP(), false);
        zzb.zza(parcel, 4, appContentCardEntity.zzvQ(), false);
        zzb.zzc(parcel, 5, appContentCardEntity.zzwb());
        zzb.zza(parcel, 6, appContentCardEntity.getDescription(), false);
        zzb.zza(parcel, 7, appContentCardEntity.getExtras(), false);
        zzb.zza(parcel, 10, appContentCardEntity.zzwc(), false);
        zzb.zza(parcel, 11, appContentCardEntity.getTitle(), false);
        zzb.zzc(parcel, 12, appContentCardEntity.zzwd());
        zzb.zza(parcel, 13, appContentCardEntity.getType(), false);
        zzb.zza(parcel, 14, appContentCardEntity.getId(), false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeh(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgj(i);
    }

    public AppContentCardEntity zzeh(Parcel parcel) {
        int zzau = zza.zzau(parcel);
        int i = 0;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        ArrayList arrayList3 = null;
        String str = null;
        int i2 = 0;
        String str2 = null;
        Bundle bundle = null;
        String str3 = null;
        String str4 = null;
        int i3 = 0;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    arrayList = zza.zzc(parcel, zzat, AppContentActionEntity.CREATOR);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    arrayList2 = zza.zzc(parcel, zzat, AppContentAnnotationEntity.CREATOR);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    arrayList3 = zza.zzc(parcel, zzat, AppContentConditionEntity.CREATOR);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    i2 = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    str2 = zza.zzp(parcel, zzat);
                    break;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    bundle = zza.zzr(parcel, zzat);
                    break;
                case R.styleable.Toolbar_titleTextAppearance /*10*/:
                    str3 = zza.zzp(parcel, zzat);
                    break;
                case R.styleable.Toolbar_subtitleTextAppearance /*11*/:
                    str4 = zza.zzp(parcel, zzat);
                    break;
                case R.styleable.Toolbar_titleMargins /*12*/:
                    i3 = zza.zzg(parcel, zzat);
                    break;
                case R.styleable.Toolbar_titleMarginStart /*13*/:
                    str5 = zza.zzp(parcel, zzat);
                    break;
                case R.styleable.Toolbar_titleMarginEnd /*14*/:
                    str6 = zza.zzp(parcel, zzat);
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
            return new AppContentCardEntity(i, arrayList, arrayList2, arrayList3, str, i2, str2, bundle, str3, str4, i3, str5, str6);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public AppContentCardEntity[] zzgj(int i) {
        return new AppContentCardEntity[i];
    }
}
