package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import org.json.simple.parser.Yytoken;

public class SnapshotEntityCreator implements Creator<SnapshotEntity> {
    static void zza(SnapshotEntity snapshotEntity, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zza(parcel, 1, snapshotEntity.getMetadata(), i, false);
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, snapshotEntity.getVersionCode());
        zzb.zza(parcel, 3, snapshotEntity.getSnapshotContents(), i, false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeB(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgS(i);
    }

    public SnapshotEntity zzeB(Parcel parcel) {
        SnapshotContentsEntity snapshotContentsEntity = null;
        int zzau = zza.zzau(parcel);
        int i = 0;
        SnapshotMetadata snapshotMetadata = null;
        while (parcel.dataPosition() < zzau) {
            int i2;
            SnapshotContentsEntity snapshotContentsEntity2;
            SnapshotMetadata snapshotMetadata2;
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    i2 = i;
                    SnapshotMetadataEntity snapshotMetadataEntity = (SnapshotMetadataEntity) zza.zza(parcel, zzat, SnapshotMetadataEntity.CREATOR);
                    snapshotContentsEntity2 = snapshotContentsEntity;
                    Object obj = snapshotMetadataEntity;
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    snapshotContentsEntity2 = (SnapshotContentsEntity) zza.zza(parcel, zzat, SnapshotContentsEntity.CREATOR);
                    snapshotMetadata2 = snapshotMetadata;
                    i2 = i;
                    break;
                case AdError.NETWORK_ERROR_CODE /*1000*/:
                    SnapshotContentsEntity snapshotContentsEntity3 = snapshotContentsEntity;
                    snapshotMetadata2 = snapshotMetadata;
                    i2 = zza.zzg(parcel, zzat);
                    snapshotContentsEntity2 = snapshotContentsEntity3;
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    snapshotContentsEntity2 = snapshotContentsEntity;
                    snapshotMetadata2 = snapshotMetadata;
                    i2 = i;
                    break;
            }
            i = i2;
            snapshotMetadata = snapshotMetadata2;
            snapshotContentsEntity = snapshotContentsEntity2;
        }
        if (parcel.dataPosition() == zzau) {
            return new SnapshotEntity(i, snapshotMetadata, snapshotContentsEntity);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public SnapshotEntity[] zzgS(int i) {
        return new SnapshotEntity[i];
    }
}
