package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import gs.gram.mopub.BuildConfig;
import org.json.simple.parser.Yytoken;

public class zze implements Creator<SignInAccount> {
    static void zza(SignInAccount signInAccount, Parcel parcel, int i) {
        int zzav = zzb.zzav(parcel);
        zzb.zzc(parcel, 1, signInAccount.versionCode);
        zzb.zza(parcel, 2, signInAccount.zzmT(), false);
        zzb.zza(parcel, 3, signInAccount.getIdToken(), false);
        zzb.zza(parcel, 4, signInAccount.getEmail(), false);
        zzb.zza(parcel, 5, signInAccount.getDisplayName(), false);
        zzb.zza(parcel, 6, signInAccount.getPhotoUrl(), i, false);
        zzb.zza(parcel, 7, signInAccount.zzmV(), i, false);
        zzb.zza(parcel, 8, signInAccount.getUserId(), false);
        zzb.zza(parcel, 9, signInAccount.zzmW(), false);
        zzb.zzI(parcel, zzav);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzT(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzaO(i);
    }

    public SignInAccount zzT(Parcel parcel) {
        String str = null;
        int zzau = zza.zzau(parcel);
        int i = 0;
        String str2 = BuildConfig.FLAVOR;
        GoogleSignInAccount googleSignInAccount = null;
        Uri uri = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < zzau) {
            int zzat = zza.zzat(parcel);
            switch (zza.zzca(zzat)) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    i = zza.zzg(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    str6 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    str5 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    str4 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    str3 = zza.zzp(parcel, zzat);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    uri = (Uri) zza.zza(parcel, zzat, Uri.CREATOR);
                    break;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    googleSignInAccount = (GoogleSignInAccount) zza.zza(parcel, zzat, GoogleSignInAccount.CREATOR);
                    break;
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    str2 = zza.zzp(parcel, zzat);
                    break;
                case R.styleable.Toolbar_popupTheme /*9*/:
                    str = zza.zzp(parcel, zzat);
                    break;
                default:
                    zza.zzb(parcel, zzat);
                    break;
            }
        }
        if (parcel.dataPosition() == zzau) {
            return new SignInAccount(i, str6, str5, str4, str3, uri, googleSignInAccount, str2, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzau, parcel);
    }

    public SignInAccount[] zzaO(int i) {
        return new SignInAccount[i];
    }
}
