package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import org.json.simple.parser.Yytoken;

public class PlayerLevelInfoCreator implements Creator<PlayerLevelInfo> {
    static void zza(PlayerLevelInfo playerLevelInfo, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zza(parcel, 1, playerLevelInfo.getCurrentXpTotal());
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, playerLevelInfo.getVersionCode());
        zzb.zza(parcel, 2, playerLevelInfo.getLastLevelUpTimestamp());
        zzb.zza(parcel, 3, playerLevelInfo.getCurrentLevel(), i, false);
        zzb.zza(parcel, 4, playerLevelInfo.getNextLevel(), i, false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzed(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgf(i);
    }

    public PlayerLevelInfo zzed(Parcel parcel) {
        long j = 0;
        PlayerLevel playerLevel = null;
        int zzau = zza.zzau(parcel);
        int i = 0;
        PlayerLevel playerLevel2 = null;
        long j2 = 0;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    j2 = zza.zzi(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    j = zza.zzi(parcel, zzat);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    playerLevel2 = (PlayerLevel) zza.zza(parcel, zzat, PlayerLevel.CREATOR);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    playerLevel = (PlayerLevel) zza.zza(parcel, zzat, PlayerLevel.CREATOR);
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
            return new PlayerLevelInfo(i, j2, j, playerLevel2, playerLevel);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public PlayerLevelInfo[] zzgf(int i) {
        return new PlayerLevelInfo[i];
    }
}
