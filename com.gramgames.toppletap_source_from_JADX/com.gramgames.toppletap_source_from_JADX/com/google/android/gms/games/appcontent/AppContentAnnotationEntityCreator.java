package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import org.json.simple.parser.Yytoken;

public class AppContentAnnotationEntityCreator implements Creator<AppContentAnnotationEntity> {
    static void zza(AppContentAnnotationEntity appContentAnnotationEntity, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zza(parcel, 1, appContentAnnotationEntity.getDescription(), false);
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, appContentAnnotationEntity.getVersionCode());
        zzb.zza(parcel, 2, appContentAnnotationEntity.zzvV(), i, false);
        zzb.zza(parcel, 3, appContentAnnotationEntity.getTitle(), false);
        zzb.zza(parcel, 5, appContentAnnotationEntity.getId(), false);
        zzb.zza(parcel, 6, appContentAnnotationEntity.zzvY(), false);
        zzb.zza(parcel, 7, appContentAnnotationEntity.zzvT(), false);
        zzb.zzc(parcel, 8, appContentAnnotationEntity.zzvU());
        zzb.zzc(parcel, 9, appContentAnnotationEntity.zzvX());
        zzb.zza(parcel, 10, appContentAnnotationEntity.zzvW(), false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeg(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgi(i);
    }

    public AppContentAnnotationEntity zzeg(Parcel parcel) {
        int i = 0;
        Bundle bundle = null;
        int zzau = zza.zzau(parcel);
        int i2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        String str5 = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    str5 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    uri = (Uri) zza.zza(parcel, zzat, Uri.CREATOR);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    str4 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    str3 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    str2 = zza.zzp(parcel, zzat);
                    break;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    i2 = zza.zzg(parcel, zzat);
                    break;
                case R.styleable.Toolbar_popupTheme /*9*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case R.styleable.Toolbar_titleTextAppearance /*10*/:
                    bundle = zza.zzr(parcel, zzat);
                    break;
                case AdError.NETWORK_ERROR_CODE /*1000*/:
                    i3 = zza.zzg(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new AppContentAnnotationEntity(i3, str5, uri, str4, str3, str2, str, i2, i, bundle);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public AppContentAnnotationEntity[] zzgi(int i) {
        return new AppContentAnnotationEntity[i];
    }
}
