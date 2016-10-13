package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import org.json.simple.parser.Yytoken;

public class GameBadgeEntityCreator implements Creator<GameBadgeEntity> {
    static void zza(GameBadgeEntity gameBadgeEntity, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, gameBadgeEntity.getType());
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, gameBadgeEntity.getVersionCode());
        zzb.zza(parcel, 2, gameBadgeEntity.getTitle(), false);
        zzb.zza(parcel, 3, gameBadgeEntity.getDescription(), false);
        zzb.zza(parcel, 4, gameBadgeEntity.getIconImageUri(), i, false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzen(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgz(i);
    }

    public GameBadgeEntity zzen(Parcel parcel) {
        int i = 0;
        Uri uri = null;
        int zzau = zza.zzau(parcel);
        String str = null;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    str2 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    uri = (Uri) zza.zza(parcel, zzat, Uri.CREATOR);
                    break;
                case AdError.NETWORK_ERROR_CODE /*1000*/:
                    i2 = zza.zzg(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new GameBadgeEntity(i2, i, str2, str, uri);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public GameBadgeEntity[] zzgz(int i) {
        return new GameBadgeEntity[i];
    }
}
