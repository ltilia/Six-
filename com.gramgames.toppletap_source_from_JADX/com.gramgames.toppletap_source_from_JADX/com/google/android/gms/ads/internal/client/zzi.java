package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import org.json.simple.parser.Yytoken;

public class zzi implements Creator<AdSizeParcel> {
    static void zza(AdSizeParcel adSizeParcel, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, adSizeParcel.versionCode);
        zzb.zza(parcel, 2, adSizeParcel.zzuh, false);
        zzb.zzc(parcel, 3, adSizeParcel.height);
        zzb.zzc(parcel, 4, adSizeParcel.heightPixels);
        zzb.zza(parcel, 5, adSizeParcel.zzui);
        zzb.zzc(parcel, 6, adSizeParcel.width);
        zzb.zzc(parcel, 7, adSizeParcel.widthPixels);
        zzb.zza(parcel, 8, adSizeParcel.zzuj, i, false);
        zzb.zza(parcel, 9, adSizeParcel.zzuk);
        zzb.zza(parcel, 10, adSizeParcel.zzul);
        zzb.zza(parcel, 11, adSizeParcel.zzum);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzc(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzm(i);
    }

    public AdSizeParcel zzc(Parcel parcel) {
        AdSizeParcel[] adSizeParcelArr = null;
        boolean z = false;
        int zzau = zza.zzau(parcel);
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        int i2 = 0;
        boolean z4 = false;
        int i3 = 0;
        int i4 = 0;
        String str = null;
        int i5 = 0;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    i5 = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    i4 = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    i3 = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    z4 = zza.zzc(parcel, zzat);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    i2 = zza.zzg(parcel, zzat);
                    break;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    adSizeParcelArr = (AdSizeParcel[]) zza.zzb(parcel, zzat, AdSizeParcel.CREATOR);
                    break;
                case R.styleable.Toolbar_popupTheme /*9*/:
                    z3 = zza.zzc(parcel, zzat);
                    break;
                case R.styleable.Toolbar_titleTextAppearance /*10*/:
                    z2 = zza.zzc(parcel, zzat);
                    break;
                case R.styleable.Toolbar_subtitleTextAppearance /*11*/:
                    z = zza.zzc(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new AdSizeParcel(i5, str, i4, i3, z4, i2, i, adSizeParcelArr, z3, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public AdSizeParcel[] zzm(int i) {
        return new AdSizeParcel[i];
    }
}
