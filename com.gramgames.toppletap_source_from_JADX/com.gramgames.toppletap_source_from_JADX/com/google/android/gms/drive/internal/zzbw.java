package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;
import org.json.simple.parser.Yytoken;

public class zzbw implements Creator<StringListResponse> {
    static void zza(StringListResponse stringListResponse, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, stringListResponse.getVersionCode());
        zzb.zzb(parcel, 2, stringListResponse.zztx(), false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzbX(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzdS(i);
    }

    public StringListResponse zzbX(Parcel parcel) {
        int zzau = zza.zzau(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    list = zza.zzD(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new StringListResponse(i, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public StringListResponse[] zzdS(int i) {
        return new StringListResponse[i];
    }
}
