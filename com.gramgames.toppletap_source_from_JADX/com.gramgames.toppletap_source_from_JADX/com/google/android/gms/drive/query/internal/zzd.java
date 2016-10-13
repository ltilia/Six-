package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.ads.AdError;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import org.json.simple.parser.Yytoken;

public class zzd implements Creator<FilterHolder> {
    static void zza(FilterHolder filterHolder, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zza(parcel, 1, filterHolder.zzauk, i, false);
        zzb.zzc(parcel, AdError.NETWORK_ERROR_CODE, filterHolder.mVersionCode);
        zzb.zza(parcel, 2, filterHolder.zzaul, i, false);
        zzb.zza(parcel, 3, filterHolder.zzaum, i, false);
        zzb.zza(parcel, 4, filterHolder.zzaun, i, false);
        zzb.zza(parcel, 5, filterHolder.zzauo, i, false);
        zzb.zza(parcel, 6, filterHolder.zzaup, i, false);
        zzb.zza(parcel, 7, filterHolder.zzauq, i, false);
        zzb.zza(parcel, 8, filterHolder.zzaur, i, false);
        zzb.zza(parcel, 9, filterHolder.zzaus, i, false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzco(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzej(i);
    }

    public FilterHolder zzco(Parcel parcel) {
        OwnedByMeFilter ownedByMeFilter = null;
        int zzau = zza.zzau(parcel);
        int i = 0;
        FullTextSearchFilter fullTextSearchFilter = null;
        HasFilter hasFilter = null;
        MatchAllFilter matchAllFilter = null;
        InFilter inFilter = null;
        NotFilter notFilter = null;
        LogicalFilter logicalFilter = null;
        FieldOnlyFilter fieldOnlyFilter = null;
        ComparisonFilter comparisonFilter = null;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    comparisonFilter = (ComparisonFilter) zza.zza(parcel, zzat, ComparisonFilter.CREATOR);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    fieldOnlyFilter = (FieldOnlyFilter) zza.zza(parcel, zzat, FieldOnlyFilter.CREATOR);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    logicalFilter = (LogicalFilter) zza.zza(parcel, zzat, LogicalFilter.CREATOR);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    notFilter = (NotFilter) zza.zza(parcel, zzat, NotFilter.CREATOR);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    inFilter = (InFilter) zza.zza(parcel, zzat, InFilter.CREATOR);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    matchAllFilter = (MatchAllFilter) zza.zza(parcel, zzat, MatchAllFilter.CREATOR);
                    break;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    hasFilter = (HasFilter) zza.zza(parcel, zzat, HasFilter.CREATOR);
                    break;
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    fullTextSearchFilter = (FullTextSearchFilter) zza.zza(parcel, zzat, FullTextSearchFilter.CREATOR);
                    break;
                case R.styleable.Toolbar_popupTheme /*9*/:
                    ownedByMeFilter = (OwnedByMeFilter) zza.zza(parcel, zzat, OwnedByMeFilter.CREATOR);
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
            return new FilterHolder(i, comparisonFilter, fieldOnlyFilter, logicalFilter, notFilter, inFilter, matchAllFilter, hasFilter, fullTextSearchFilter, ownedByMeFilter);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public FilterHolder[] zzej(int i) {
        return new FilterHolder[i];
    }
}
