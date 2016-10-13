package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import org.json.simple.parser.Yytoken;

public class zzq implements Creator<ParcelableCollaborator> {
    static void zza(ParcelableCollaborator parcelableCollaborator, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, parcelableCollaborator.mVersionCode);
        zzb.zza(parcel, 2, parcelableCollaborator.zzauI);
        zzb.zza(parcel, 3, parcelableCollaborator.zzaeW);
        zzb.zza(parcel, 4, parcelableCollaborator.zzLq, false);
        zzb.zza(parcel, 5, parcelableCollaborator.zzrG, false);
        zzb.zza(parcel, 6, parcelableCollaborator.zzWQ, false);
        zzb.zza(parcel, 7, parcelableCollaborator.zzauJ, false);
        zzb.zza(parcel, 8, parcelableCollaborator.zzauK, false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzcA(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzew(i);
    }

    public ParcelableCollaborator zzcA(Parcel parcel) {
        boolean z = false;
        String str = null;
        int zzau = zza.zzau(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    z2 = zza.zzc(parcel, zzat);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    z = zza.zzc(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    str5 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    str4 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    str3 = zza.zzp(parcel, zzat);
                    break;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    str2 = zza.zzp(parcel, zzat);
                    break;
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new ParcelableCollaborator(i, z2, z, str5, str4, str3, str2, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public ParcelableCollaborator[] zzew(int i) {
        return new ParcelableCollaborator[i];
    }
}
