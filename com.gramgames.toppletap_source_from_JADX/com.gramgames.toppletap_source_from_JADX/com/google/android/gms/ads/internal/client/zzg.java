package com.google.android.gms.ads.internal.client;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import java.util.List;
import org.json.simple.parser.Yytoken;

public class zzg implements Creator<AdRequestParcel> {
    static void zza(AdRequestParcel adRequestParcel, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, adRequestParcel.versionCode);
        zzb.zza(parcel, 2, adRequestParcel.zztC);
        zzb.zza(parcel, 3, adRequestParcel.extras, false);
        zzb.zzc(parcel, 4, adRequestParcel.zztD);
        zzb.zzb(parcel, 5, adRequestParcel.zztE, false);
        zzb.zza(parcel, 6, adRequestParcel.zztF);
        zzb.zzc(parcel, 7, adRequestParcel.zztG);
        zzb.zza(parcel, 8, adRequestParcel.zztH);
        zzb.zza(parcel, 9, adRequestParcel.zztI, false);
        zzb.zza(parcel, 10, adRequestParcel.zztJ, i, false);
        zzb.zza(parcel, 11, adRequestParcel.zztK, i, false);
        zzb.zza(parcel, 12, adRequestParcel.zztL, false);
        zzb.zza(parcel, 13, adRequestParcel.zztM, false);
        zzb.zza(parcel, 14, adRequestParcel.zztN, false);
        zzb.zzb(parcel, 15, adRequestParcel.zztO, false);
        zzb.zza(parcel, 17, adRequestParcel.zztQ, false);
        zzb.zza(parcel, 16, adRequestParcel.zztP, false);
        zzb.zza(parcel, 18, adRequestParcel.zztR);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzb(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzl(i);
    }

    public AdRequestParcel zzb(Parcel parcel) {
        int zzau = zza.zzau(parcel);
        int i = 0;
        long j = 0;
        Bundle bundle = null;
        int i2 = 0;
        List list = null;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        String str = null;
        SearchAdRequestParcel searchAdRequestParcel = null;
        Location location = null;
        String str2 = null;
        Bundle bundle2 = null;
        Bundle bundle3 = null;
        List list2 = null;
        String str3 = null;
        String str4 = null;
        boolean z3 = false;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    j = zza.zzi(parcel, zzat);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    bundle = zza.zzr(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    i2 = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    list = zza.zzD(parcel, zzat);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    z = zza.zzc(parcel, zzat);
                    break;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    i3 = zza.zzg(parcel, zzat);
                    break;
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    z2 = zza.zzc(parcel, zzat);
                    break;
                case R.styleable.Toolbar_popupTheme /*9*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case R.styleable.Toolbar_titleTextAppearance /*10*/:
                    searchAdRequestParcel = (SearchAdRequestParcel) zza.zza(parcel, zzat, SearchAdRequestParcel.CREATOR);
                    break;
                case R.styleable.Toolbar_subtitleTextAppearance /*11*/:
                    location = (Location) zza.zza(parcel, zzat, Location.CREATOR);
                    break;
                case R.styleable.Toolbar_titleMargins /*12*/:
                    str2 = zza.zzp(parcel, zzat);
                    break;
                case R.styleable.Toolbar_titleMarginStart /*13*/:
                    bundle2 = zza.zzr(parcel, zzat);
                    break;
                case R.styleable.Toolbar_titleMarginEnd /*14*/:
                    bundle3 = zza.zzr(parcel, zzat);
                    break;
                case R.styleable.Toolbar_titleMarginTop /*15*/:
                    list2 = zza.zzD(parcel, zzat);
                    break;
                case R.styleable.Toolbar_titleMarginBottom /*16*/:
                    str3 = zza.zzp(parcel, zzat);
                    break;
                case R.styleable.Toolbar_maxButtonHeight /*17*/:
                    str4 = zza.zzp(parcel, zzat);
                    break;
                case R.styleable.Toolbar_collapseIcon /*18*/:
                    z3 = zza.zzc(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new AdRequestParcel(i, j, bundle, i2, list, z, i3, z2, str, searchAdRequestParcel, location, str2, bundle2, bundle3, list2, str3, str4, z3);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public AdRequestParcel[] zzl(int i) {
        return new AdRequestParcel[i];
    }
}
