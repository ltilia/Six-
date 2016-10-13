package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class GetPermissionsRequest implements SafeParcelable {
    public static final Creator<GetPermissionsRequest> CREATOR;
    final int mVersionCode;
    final DriveId zzaoz;

    static {
        CREATOR = new zzak();
    }

    GetPermissionsRequest(int versionCode, DriveId driveId) {
        this.mVersionCode = versionCode;
        this.zzaoz = driveId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        zzak.zza(this, dest, flags);
    }
}
