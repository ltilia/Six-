package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmr;
import java.util.Set;
import java.util.regex.Pattern;

public class DriveSpace implements SafeParcelable {
    public static final Creator<DriveSpace> CREATOR;
    public static final DriveSpace zzaoP;
    public static final DriveSpace zzaoQ;
    public static final DriveSpace zzaoR;
    public static final Set<DriveSpace> zzaoS;
    public static final String zzaoT;
    private static final Pattern zzaoU;
    private final String mName;
    final int mVersionCode;

    static {
        CREATOR = new zzg();
        zzaoP = new DriveSpace("DRIVE");
        zzaoQ = new DriveSpace("APP_DATA_FOLDER");
        zzaoR = new DriveSpace(ShareConstants.PHOTOS);
        zzaoS = zzmr.zza(zzaoP, zzaoQ, zzaoR);
        zzaoT = TextUtils.join(",", zzaoS.toArray());
        zzaoU = Pattern.compile("[A-Z0-9_]*");
    }

    DriveSpace(int versionCode, String name) {
        this.mVersionCode = versionCode;
        this.mName = (String) zzx.zzz(name);
    }

    private DriveSpace(String name) {
        this(1, name);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return (o == null || o.getClass() != DriveSpace.class) ? false : this.mName.equals(((DriveSpace) o).mName);
    }

    public String getName() {
        return this.mName;
    }

    public int hashCode() {
        return 1247068382 ^ this.mName.hashCode();
    }

    public String toString() {
        return this.mName;
    }

    public void writeToParcel(Parcel out, int flags) {
        zzg.zza(this, out, flags);
    }
}
