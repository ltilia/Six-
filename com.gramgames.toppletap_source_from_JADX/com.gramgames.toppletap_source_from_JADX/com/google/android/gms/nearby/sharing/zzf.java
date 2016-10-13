package com.google.android.gms.nearby.sharing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import org.json.simple.parser.Yytoken;

public class zzf implements Creator<ViewableItem> {
    static void zza(ViewableItem viewableItem, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, viewableItem.getVersionCode());
        zzb.zza(parcel, 2, viewableItem.zzEN(), false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzgt(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzjz(i);
    }

    public ViewableItem zzgt(Parcel parcel) {
        int zzau = zza.zzau(parcel);
        int i = 0;
        String[] strArr = null;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    strArr = zza.zzB(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new ViewableItem(i, strArr);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public ViewableItem[] zzjz(int i) {
        return new ViewableItem[i];
    }
}
