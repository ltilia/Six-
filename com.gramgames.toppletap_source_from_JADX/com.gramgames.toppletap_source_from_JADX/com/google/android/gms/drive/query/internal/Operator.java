package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Operator implements SafeParcelable {
    public static final Creator<Operator> CREATOR;
    public static final Operator zzauA;
    public static final Operator zzauB;
    public static final Operator zzauC;
    public static final Operator zzauD;
    public static final Operator zzauE;
    public static final Operator zzauF;
    public static final Operator zzaux;
    public static final Operator zzauy;
    public static final Operator zzauz;
    final String mTag;
    final int mVersionCode;

    static {
        CREATOR = new zzn();
        zzaux = new Operator("=");
        zzauy = new Operator("<");
        zzauz = new Operator("<=");
        zzauA = new Operator(">");
        zzauB = new Operator(">=");
        zzauC = new Operator("and");
        zzauD = new Operator("or");
        zzauE = new Operator("not");
        zzauF = new Operator("contains");
    }

    Operator(int versionCode, String tag) {
        this.mVersionCode = versionCode;
        this.mTag = tag;
    }

    private Operator(String tag) {
        this(1, tag);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Operator operator = (Operator) obj;
        return this.mTag == null ? operator.mTag == null : this.mTag.equals(operator.mTag);
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return (this.mTag == null ? 0 : this.mTag.hashCode()) + 31;
    }

    public void writeToParcel(Parcel out, int flags) {
        zzn.zza(this, out, flags);
    }
}
