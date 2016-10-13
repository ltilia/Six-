package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class SetPinnedDownloadPreferencesRequest implements SafeParcelable {
    public static final Creator<SetPinnedDownloadPreferencesRequest> CREATOR;
    final int mVersionCode;
    final ParcelableTransferPreferences zzasu;

    static {
        CREATOR = new zzbs();
    }

    SetPinnedDownloadPreferencesRequest(int versionCode, ParcelableTransferPreferences prefs) {
        this.mVersionCode = versionCode;
        this.zzasu = prefs;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        zzbs.zza(this, dest, flags);
    }
}
