package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import org.json.simple.parser.Yytoken;

public class zzb implements Creator<FieldOnlyFilter> {
    static void zza(FieldOnlyFilter fieldOnlyFilter, Parcel parcel, int i) {
        int zzav = com.google.android.gms.common.internal.safeparcel.zzb.zzav(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, fieldOnlyFilter.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, fieldOnlyFilter.zzauh, i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzcm(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzeh(i);
    }

    public FieldOnlyFilter zzcm(Parcel parcel) {
        int zzau = zza.zzau(parcel);
        int i = 0;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    metadataBundle = (MetadataBundle) zza.zza(parcel, zzat, MetadataBundle.CREATOR);
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
            return new FieldOnlyFilter(i, metadataBundle);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public FieldOnlyFilter[] zzeh(int i) {
        return new FieldOnlyFilter[i];
    }
}
