package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.nearby.messages.Message;
import org.json.simple.parser.Yytoken;

public class zzl implements Creator<MessageWrapper> {
    static void zza(MessageWrapper messageWrapper, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zza(parcel, 1, messageWrapper.zzbcu, i, false);
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, messageWrapper.mVersionCode);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzgk(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzjp(i);
    }

    public MessageWrapper zzgk(Parcel parcel) {
        int zzau = zza.zzau(parcel);
        int i = 0;
        Message message = null;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    message = (Message) zza.zza(parcel, zzat, Message.CREATOR);
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
            return new MessageWrapper(i, message);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public MessageWrapper[] zzjp(int i) {
        return new MessageWrapper[i];
    }
}
