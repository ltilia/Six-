package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.GameEntity;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import java.util.ArrayList;
import org.json.simple.parser.Yytoken;

public class InvitationEntityCreator implements Creator<InvitationEntity> {
    static void zza(InvitationEntity invitationEntity, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zza(parcel, 1, invitationEntity.getGame(), i, false);
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, invitationEntity.getVersionCode());
        zzb.zza(parcel, 2, invitationEntity.getInvitationId(), false);
        zzb.zza(parcel, 3, invitationEntity.getCreationTimestamp());
        zzb.zzc(parcel, 4, invitationEntity.getInvitationType());
        zzb.zza(parcel, 5, invitationEntity.getInviter(), i, false);
        zzb.zzc(parcel, 6, invitationEntity.getParticipants(), false);
        zzb.zzc(parcel, 7, invitationEntity.getVariant());
        zzb.zzc(parcel, 8, invitationEntity.getAvailableAutoMatchSlots());
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzer(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgH(i);
    }

    public InvitationEntity zzer(Parcel parcel) {
        ArrayList arrayList = null;
        int i = 0;
        int zzau = zza.zzau(parcel);
        long j = 0;
        int i2 = 0;
        ParticipantEntity participantEntity = null;
        int i3 = 0;
        String str = null;
        GameEntity gameEntity = null;
        int i4 = 0;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    gameEntity = (GameEntity) zza.zza(parcel, zzat, GameEntity.CREATOR);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    j = zza.zzi(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    i3 = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    participantEntity = (ParticipantEntity) zza.zza(parcel, zzat, ParticipantEntity.CREATOR);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    arrayList = zza.zzc(parcel, zzat, ParticipantEntity.CREATOR);
                    break;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    i2 = zza.zzg(parcel, zzat);
                    break;
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case AdError.NETWORK_ERROR_CODE /*1000*/:
                    i4 = zza.zzg(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new InvitationEntity(i4, gameEntity, str, j, i3, participantEntity, arrayList, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public InvitationEntity[] zzgH(int i) {
        return new InvitationEntity[i];
    }
}
