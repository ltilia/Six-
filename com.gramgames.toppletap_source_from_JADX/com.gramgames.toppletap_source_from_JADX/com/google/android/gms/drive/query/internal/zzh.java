package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import org.json.simple.parser.Yytoken;

public class zzh implements Creator<FullTextSearchFilter> {
    static void zza(FullTextSearchFilter fullTextSearchFilter, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, fullTextSearchFilter.mVersionCode);
        zzb.zza(parcel, 1, fullTextSearchFilter.mValue, false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzcp(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzek(i);
    }

    public FullTextSearchFilter zzcp(Parcel parcel) {
        int zzau = zza.zzau(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    str = zza.zzp(parcel, zzat);
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
            return new FullTextSearchFilter(i, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public FullTextSearchFilter[] zzek(int i) {
        return new FullTextSearchFilter[i];
    }
}
